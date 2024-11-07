package com.javaweb.toeic.enums;

import lombok.Getter;

import java.util.Map;
import java.util.TreeMap;

@Getter
public enum PartFourQuestionClusterType {
    TELEPHONE_MESSAGE("Telephone message - Tin nhắn thoại"),
    ADVERTISEMENT("Advertisement - Quảng cáo"),
    ANNOUNCEMENT("Announcement - Thông báo"),
    TALK("Talk - Bài phát biểu, diễn văn"),
    NEWS_REPORT_BROADCAST("News report, Broadcast - Bản tin"),
    EXCERPT_FROM_MEETING("Excerpt from a meeting - Trích dẫn từ buổi họp");

    // Phương thức để lấy mô tả của dạng bài
    // Trường để lưu mô tả của dạng bài
    private final String description;

    // Constructor để khởi tạo giá trị của trường description
    PartFourQuestionClusterType(String description) {
        this.description = description;
    }

    // Phương thức tĩnh để lấy tất cả các dạng bài dưới dạng Map
    public static Map<String, String> getAllTypes() {
        Map<String, String> typeMap = new TreeMap<>();
        for (PartFourQuestionType type : PartFourQuestionType.values()) {
            typeMap.put(type.name(), type.getDescription());
        }
        return typeMap;
    }
}
