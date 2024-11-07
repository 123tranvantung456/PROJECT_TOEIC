package com.javaweb.toeic.enums;

import java.util.Map;
import java.util.TreeMap;

public enum PartSevenQuestionClusterType {
    ARTICLE_REVIEW("Article/ Review - Bài báo/ Bài đánh giá"),
    EMAIL_LETTER("Email/ Letter - Thư điện tử/ Thư tay"),
    ADVERTISEMENT("Advertisement - Quảng cáo"),
    FORM("Form - Biểu mẫu"),
    TEXT_MESSAGE_CHAIN("Text message chain - Chuỗi tin nhắn");

    // Trường để lưu mô tả của dạng bài
    private final String description;

    // Constructor để khởi tạo giá trị của trường description
    PartSevenQuestionClusterType(String description) {
        this.description = description;
    }

    // Phương thức để lấy mô tả của dạng bài
    public String getDescription() {
        return description;
    }

    // Phương thức tĩnh để lấy tất cả các dạng bài dưới dạng Map
    public static Map<String, String> getAllTypes() {
        Map<String, String> typeMap = new TreeMap<>();
        for (PartSevenQuestionClusterType type : PartSevenQuestionClusterType.values()) {
            typeMap.put(type.name(), type.getDescription());
        }
        return typeMap;
    }
}
