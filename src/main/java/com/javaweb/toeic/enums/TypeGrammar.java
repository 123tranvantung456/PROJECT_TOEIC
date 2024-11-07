package com.javaweb.toeic.enums;

import java.util.Map;
import java.util.TreeMap;

public enum TypeGrammar {
    NOUN("Danh từ"),
    VOICE("Thể"),
    VERB("Động từ"),
    PRONOUN("Đại từ"),
    ADJECTIVE("Tính từ"),
    TENSE("Thì"),
    INFINITIVE_VERB("Động từ nguyên mẫu"),
    IMPERATIVE_SENTENCE("Câu cầu khiến"),
    TO_INF_VERB("Động từ nguyên mẫu có 'to'"),
    GERUND("Danh động từ"),
    PARTICIPLE_AND_PARTICIPLE_CONSTRUCTIONS("Phân từ và cấu trúc phân từ"),
    ADVERB("Trạng từ"),
    PREPOSITION("Giới từ"),
    CONJUNCTION("Liên từ"),
    RELATIVE_CLAUSE("Mệnh đề quan hệ"),
    CONDITIONAL_SENTENCE("Câu điều kiện"),
    PARTICIPLE_CONSTRUCTION("Cấu trúc phân từ"),
    COMPARATIVE_STRUCTURE("Cấu trúc so sánh");

    // Trường để lưu mô tả của loại ngữ pháp
    private final String description;

    // Constructor để khởi tạo giá trị của trường description
    TypeGrammar(String description) {
        this.description = description;
    }

    // Phương thức để lấy mô tả của loại ngữ pháp
    public String getDescription() {
        return description;
    }

    // Phương thức tĩnh để lấy tất cả các loại ngữ pháp dưới dạng Map
    public static Map<String, String> getAllTypes() {
        Map<String, String> typeMap = new TreeMap<>();
        for (TypeGrammar type : TypeGrammar.values()) {
            typeMap.put(type.name(), type.getDescription());
        }
        return typeMap;
    }
}
