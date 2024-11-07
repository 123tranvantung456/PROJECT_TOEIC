package com.javaweb.toeic.enums;

import java.util.Map;
import java.util.TreeMap;

public enum PartSevenQuestionType {
    QUESTION_TOPIC_AND_PURPOSE("Câu hỏi về chủ đề, mục đích"),
    QUESTION_INFORMATION_SEARCH("Câu hỏi tìm thông tin"),
    QUESTION_INFERENCE("Câu hỏi suy luận"),
    QUESTION_SYNONYMS("Câu hỏi tìm từ đồng nghĩa"),
    QUESTION_IMPLICATION("Câu hỏi về hàm ý câu nói"),
    QUESTION_FIND_INCORRECT_DETAIL("Câu hỏi tìm chi tiết sai"),
    QUESTION_FILL_IN_THE_BLANK("Câu hỏi điền câu");

    // Trường để lưu mô tả của loại câu hỏi
    private final String description;

    // Constructor để khởi tạo giá trị của trường description
    PartSevenQuestionType(String description) {
        this.description = description;
    }

    // Phương thức để lấy mô tả của loại câu hỏi
    public String getDescription() {
        return description;
    }

    // Phương thức tĩnh để lấy tất cả các loại câu hỏi dưới dạng Map
    public static Map<String, String> getAllTypes() {
        Map<String, String> typeMap = new TreeMap<>();
        for (PartSevenQuestionType type : PartSevenQuestionType.values()) {
            typeMap.put(type.name(), type.getDescription());
        }
        return typeMap;
    }
}
