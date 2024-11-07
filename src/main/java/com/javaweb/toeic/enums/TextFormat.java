package com.javaweb.toeic.enums;

import java.util.Map;
import java.util.TreeMap;

public enum TextFormat {
    EMAIL_LETTER("Email/ Letter - Thư điện tử/ thư tay"),
    ARTICLE_REVIEW("Article/ Review - Bài báo"),
    ADVERTISEMENT("Advertisement - Quảng cáo"),
    NOTICE_ANNOUNCEMENT_INFORMATION("Notice/ Announcement Information - Thông báo/ văn bản hướng dẫn"),
    MEMO("Memo - Thông báo nội bộ");

    // Trường để lưu mô tả của hình thức văn bản
    private final String description;

    // Constructor để khởi tạo giá trị của trường description
    TextFormat(String description) {
        this.description = description;
    }

    // Phương thức để lấy mô tả của hình thức văn bản
    public String getDescription() {
        return description;
    }

    // Phương thức tĩnh để lấy tất cả các hình thức văn bản dưới dạng Map
    public static Map<String, String> getAllFormats() {
        Map<String, String> formatMap = new TreeMap<>();
        for (TextFormat format : TextFormat.values()) {
            formatMap.put(format.name(), format.getDescription());
        }
        return formatMap;
    }
}
