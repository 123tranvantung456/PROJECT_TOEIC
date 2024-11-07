package com.javaweb.toeic.enums;

import lombok.Getter;

import java.util.Map;
import java.util.TreeMap;

@Getter
public enum PartFourQuestionType {
    QUESTION_TOPIC("Câu hỏi về chủ đề, mục đích"),
    QUESTION_IDENTITY_LOCATION("Câu hỏi về danh tính, địa điểm"),
    QUESTION_DETAIL("Câu hỏi về chi tiết"),
    QUESTION_REQUEST_SUGGESTION("Câu hỏi về yêu cầu, gợi ý"),
    QUESTION_FUTURE_ACTION("Câu hỏi về hành động tương lai"),
    QUESTION_IMPLICATION("Câu hỏi về hàm ý câu nói"),
    QUESTION_TABLE_COMBINATION("Câu hỏi kết hợp bảng biểu");

    // Phương thức để lấy mô tả của loại câu hỏi
    // Trường để lưu mô tả của loại câu hỏi
    private final String description;

    // Constructor để khởi tạo giá trị của trường description
    PartFourQuestionType(String description) {
        this.description = description;
    }

    // Phương thức tĩnh để lấy tất cả các loại câu hỏi dưới dạng Map
    public static Map<String, String> getAllTypes() {
        Map<String, String> typeMap = new TreeMap<>();
        for (PartFourQuestionType type : PartFourQuestionType.values()) {
            typeMap.put(type.name(), type.getDescription());
        }
        return typeMap;
    }
}
