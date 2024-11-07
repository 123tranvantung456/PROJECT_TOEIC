package com.javaweb.toeic.enums;

import java.util.Map;
import java.util.TreeMap;

public enum PartSixQuestionType {
    QUESTION_PART_OF_SPEECH("Câu hỏi từ loại"),
    QUESTION_GRAMMAR("Câu hỏi ngữ pháp"),
    QUESTION_VOCABULARY("Câu hỏi từ vựng"),
    QUESTION_FILL_IN_THE_BLANK("Câu hỏi điền câu vào đoạn văn");

    // Trường để lưu mô tả của loại câu hỏi
    private final String description;

    // Constructor để khởi tạo giá trị của trường description
    PartSixQuestionType(String description) {
        this.description = description;
    }

    // Phương thức để lấy mô tả của loại câu hỏi
    public String getDescription() {
        return description;
    }

    // Phương thức tĩnh để lấy tất cả các loại câu hỏi dưới dạng Map
    public static Map<String, String> getAllTypes() {
        Map<String, String> typeMap = new TreeMap<>();
        for (PartSixQuestionType type : PartSixQuestionType.values()) {
            typeMap.put(type.name(), type.getDescription());
        }
        return typeMap;
    }
}
