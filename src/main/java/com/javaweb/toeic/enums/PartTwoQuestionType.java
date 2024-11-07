package com.javaweb.toeic.enums;

import java.util.Map;
import java.util.TreeMap;

public enum PartTwoQuestionType {
    QUESTION_WHO("Câu hỏi WHO - Ai"),
    QUESTION_WHAT("Câu hỏi WHAT - Gì"),
    QUESTION_WHERE("Câu hỏi WHERE - Ở đâu"),
    QUESTION_WHEN("Câu hỏi WHEN - Khi nào"),
    QUESTION_WHY("Câu hỏi WHY - Tại sao"),
    QUESTION_HOW("Câu hỏi HOW - Như thế nào"),
    QUESTION_YES_NO("Câu hỏi YES/NO - Có/Không"),
    QUESTION_CHOICE("Câu hỏi lựa chọn"),
    QUESTION_TAG("Câu hỏi đuôi"),
    QUESTION_REQUEST("Câu đề nghị, yêu cầu"),
    STATEMENT("Câu trần thuật");

    // Trường để lưu mô tả của loại câu hỏi
    private final String description;

    // Constructor để khởi tạo giá trị của trường description
    PartTwoQuestionType(String description) {
        this.description = description;
    }

    // Phương thức để lấy mô tả của loại câu hỏi
    public String getDescription() {
        return description;
    }

    // Phương thức tĩnh để lấy tất cả các loại câu hỏi dưới dạng Map
    public static Map<String, String> getAllTypes() {
        Map<String, String> typeMap = new TreeMap<>();
        for (PartTwoQuestionType type : PartTwoQuestionType.values()) {
            typeMap.put(type.name(), type.getDescription());
        }
        return typeMap;
    }
}
