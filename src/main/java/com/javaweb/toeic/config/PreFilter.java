package com.javaweb.toeic.config;

import com.javaweb.toeic.enums.TokenType;
import com.javaweb.toeic.service.JwtService;
import com.javaweb.toeic.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class PreFilter extends OncePerRequestFilter {
    private final UserService userService;
    private final JwtService jwtService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       log.info("------------------PreFilter------------------");
       final String authorization = request.getHeader("Authorization");
       log.info("authorization :  {}", authorization);
       // những api ko cần yêu cầu xác thực sẽ đi qua đây
       if (StringUtils.isBlank(authorization) || !authorization.startsWith("Bearer ")) {
        filterChain.doFilter(request, response);
        return;
       }

       final String token = authorization.substring("Bearer ".length());
       final String userName = jwtService.extractUsername(token, TokenType.ACCESS_TOKEN);
       log.info("userName : {}", userName);
       if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
           UserDetails userDetails = userService.getUserDetailsService().loadUserByUsername(userName);
           if(jwtService.isValid(token, TokenType.ACCESS_TOKEN, userDetails)) {
               SecurityContext context = SecurityContextHolder.getContext();
               UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken
                       (userDetails, null, userDetails.getAuthorities());
               authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
               context.setAuthentication(authentication);
               SecurityContextHolder.setContext(context);
           }
       }
       filterChain.doFilter(request, response);
    }
}


// cái này sẽ hứng các request trước khi chuyển đến các bước tiếp theo
//  private static final Logger logger = LoggerFactory.getLogger(MyClass.class);
// logger.info("Logging with logger");
// logger dùng để ghi log
// nếu dùng @Slf4j của lombok thì ko cần phải khởi tạo thủ công như logger


//Đoạn mã SecurityContextHolder.getContext().getAuthentication() == null dùng để kiểm tra xem người dùng đã xác thực hay chưa.
//Nếu trả về null, có nghĩa là người dùng chưa đăng nhập hoặc chưa được xác thực.
//Nếu không phải null, tức là người dùng đã đăng nhập và được xác thực.

// final String userName = jwtService.extractUsername(token); : extract token ra
// if(jwtService.isValid(token, userDetails)) : validate token

//Token không được lưu trên server, nhưng thông tin xác thực của người dùng (dựa trên token hợp lệ) được lưu trong
// SecurityContext để sử dụng cho các request tiếp theo.