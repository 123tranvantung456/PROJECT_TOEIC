package com.javaweb.toeic.config;

import com.javaweb.toeic.service.UserService;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class AppConfig {
    // final để k bị sửa đổi
    private final UserService userService;
    private final PreFilter preFilter;

    private final String[] WHITE_LIST = {
            "/auth/**", "/api/chapter/{chapterId}"
    };
    // tạo bean để mã hóa password
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // khởi tạo provider: truy cập đến DAO thông qua service detail, DaoAuthenticationProvider là 1 class implement AuthenticationProvider
    @Bean
    public AuthenticationProvider provider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder()); // vì bean là singleton nên chỉ có 1 phiên bản của passwordEncoder nên đã đc khởi tạo trước
        provider.setUserDetailsService(userService.getUserDetailsService());
        return provider;
    }

    // thiết lập url trên giao diện trình duyệt
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return webSecurity -> {
            webSecurity.ignoring().requestMatchers(
                    "/",
                    "/web/**",
                    "/admin/**",
                    "/courses",
                    "/courses/{id}/introduction",
                    "/login",
                    "/chapters/**",
                    "/api/vocabulary/**",
                    "/**"
            );

        };
    }

    // quản lí role và user khi truy cap vào hệ thống, thường trả về instance của ProviderManager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain configure(@NotNull HttpSecurity http) throws Exception {
//        // Tắt CSRF : đây là cơ chế bảo mật đối với những web dùng session
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests.requestMatchers(WHITE_LIST).permitAll()

                        .anyRequest().authenticated())
        .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(provider()).addFilterBefore(preFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}

//FilterChain
//    -> preFilter (nếu có)
//    -> UsernamePasswordAuthenticationFilter
//    -> AuthenticationManager
//    -> AuthenticationProvider (thực hiện xác thực)

// STATELESS : sever ko lưu session, STATEFUL : sever lưu session
// SecurityFilterChain :  form login mặc định của spring security sẽ ko xuất hiện
// @Autowired Tiêm phụ thuộc sau khi khởi tạo đối tượng
// final yêu cầu khởi tạo ngay khi đối tượng được tạo => @RequiredArgsConstructor
// permitAll() : cho phép tất cả đều truy cập vào được, nghĩa là api ko yêu cầu token
// authenticationProvider(provider()) : Spring Security sẽ sử dụng AuthenticationProvider này để xác thực thông tin đăng nhập của người dùng.
//

// AuthenticationManager.authenticate(...) => spring tự động dùng DaoAuthenticationProvider để xác thực username và password
// khi bấm run(debug) thì spring boot sẽ duyệt qua all để tạo bean
//AuthenticationConfiguration : bean có sẳn