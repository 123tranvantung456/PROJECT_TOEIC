package com.javaweb.toeic.enums;

import java.util.Map;
import java.util.TreeMap;

public enum PartOneQuestionType {
    DESCRIPTION_OBJECT("Tranh tả vật"),
    DESCRIPTION_PERSON("Tranh tả người"),
    DESCRIPTION_PERSON_AND_OBJECT("Tranh tả người và vật");

    // Field to store the description of the question type
    private final String description;

    // Constructor to initialize the description field
    PartOneQuestionType(String description) {
        this.description = description;
    }

    // Method to get the description of the question type
    public String getDescription() {
        return description;
    }

    // Static method to get all question types as a Map
    public static Map<String, String> getAllTypes() {
        Map<String, String> typeMap = new TreeMap<>();
        for (PartOneQuestionType type : PartOneQuestionType.values()) {
            typeMap.put(type.name(), type.getDescription());
        }
        return typeMap;
    }
}
