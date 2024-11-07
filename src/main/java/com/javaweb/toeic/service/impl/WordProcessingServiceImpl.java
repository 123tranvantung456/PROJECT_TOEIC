package com.javaweb.toeic.service.impl;

import com.javaweb.toeic.service.WordProcessingService;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class WordProcessingServiceImpl implements WordProcessingService {
    @Override
    public String readWordFile(Resource resource) throws IOException {
        StringBuilder htmlContent = new StringBuilder();

        try (InputStream inputStream = resource.getInputStream();
             XWPFDocument document = new XWPFDocument(inputStream)) {

            for (XWPFParagraph paragraph : document.getParagraphs()) {
                htmlContent.append("<p>");

                for (XWPFRun run : paragraph.getRuns()) {
                    String text = run.text();
                    StringBuilder styledText = new StringBuilder();

                    // Xử lý kích thước font chữ
                    if (run.getFontSize() != -1) {
                        styledText.append("<span style='font-size:")
                                .append(run.getFontSize())
                                .append("pt;'>");
                    }

                    if (run.isBold()) {
                        styledText.append("<b>");
                    }
                    if (run.isItalic()) {
                        styledText.append("<i>");
                    }
                    if (run.getUnderline() != null) {
                        styledText.append("<u>");
                    }
                    if (run.getColor() != null) {
                        styledText.append("<span style='color:#")
                                .append(run.getColor())
                                .append(";'>");
                    }

                    // Thêm nội dung văn bản
                    styledText.append(text);

                    // Đóng các tag HTML
                    if (run.getColor() != null) {
                        styledText.append("</span>");
                    }
                    if (run.getUnderline() != null) {
                        styledText.append("</u>");
                    }
                    if (run.isItalic()) {
                        styledText.append("</i>");
                    }
                    if (run.isBold()) {
                        styledText.append("</b>");
                    }
                    if (run.getFontSize() != -1) {
                        styledText.append("</span>");
                    }

                    htmlContent.append(styledText);

                    // Xử lý xuống dòng
                    if (text.contains("\n")) {
                        htmlContent.append("<br>");
                    }
                }

                htmlContent.append("</p>");
            }
        }

        return htmlContent.toString();
    }
}
