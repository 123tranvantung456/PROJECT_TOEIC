package com.javaweb.toeic.enums;

import java.util.Map;
import java.util.TreeMap;

public enum StructureOfPartSeven {
    MOT_DOAN("Một đoạn"),
    NHIEU_DOAN("Nhiều đoạn");
    private final String description;

    StructureOfPartSeven(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
    public static Map<String, String> getAllTypes() {
        Map<String, String> typeMap = new TreeMap<>();
        for (StructureOfPartSeven type : StructureOfPartSeven.values()) {
            typeMap.put(type.name(), type.getDescription());
        }
        return typeMap;
    }
}
