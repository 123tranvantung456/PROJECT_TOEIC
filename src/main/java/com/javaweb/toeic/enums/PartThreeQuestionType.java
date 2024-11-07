package com.javaweb.toeic.enums;

import java.util.Map;
import java.util.TreeMap;

public enum PartThreeQuestionType {
    QUESTION_TOPIC("Câu hỏi về chủ đề, mục đích"),
    QUESTION_LOCATION("Câu hỏi về địa điểm hội thoại"),
    QUESTION_SPEAKER_IDENTITY("Câu hỏi về danh tính người nói"),
    QUESTION_CONVERSATION_DETAIL("Câu hỏi về chi tiết cuộc hội thoại"),
    QUESTION_FUTURE_ACTION("Câu hỏi về hành động tương lai"),
    QUESTION_REQUEST_SUGGESTION("Câu hỏi về yêu cầu, gợi ý"),
    QUESTION_IMPLICATION("Câu hỏi về hàm ý câu nói"),
    QUESTION_TABLE_COMBINATION("Câu hỏi kết hợp bảng biểu");

    // Trường để lưu mô tả của loại câu hỏi
    private final String description;

    // Constructor để khởi tạo giá trị của trường description
    PartThreeQuestionType(String description) {
        this.description = description;
    }

    // Phương thức để lấy mô tả của loại câu hỏi
    public String getDescription() {
        return description;
    }

    // Phương thức tĩnh để lấy tất cả các loại câu hỏi dưới dạng Map
    public static Map<String, String> getAllTypes() {
        Map<String, String> typeMap = new TreeMap<>();
        for (PartThreeQuestionType type : PartThreeQuestionType.values()) {
            typeMap.put(type.name(), type.getDescription());
        }
        return typeMap;
    }
}
