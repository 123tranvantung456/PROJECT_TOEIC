package com.javaweb.toeic.enums;

import java.util.Map;
import java.util.TreeMap;

public enum OrderNumberPart {
    PART_1("Part 1: Photographs - Nghe tranh"),
    PART_2("Part 2: Question - Response - Hỏi - đáp"),
    PART_3("Part 3: Conversations - Nghe hiểu đối thoại"),
    PART_4("Part 4: Talks - Nghe hiểu bài nói"),
    PART_5("Part 5: Incomplete Sentences - Điền từ vào câu"),
    PART_6("Part 6: Text Completion - Điền từ vào đoạn văn"),
    PART_7("Part 7: Reading Comprehension - Đọc hiểu văn bản");

    private final String partName;

    OrderNumberPart(String partName) {
        this.partName = partName;
    }

    public String getPartName() {
        return partName;
    }

    public static Map<String, String> getAllParts() {
        Map<String, String> partMap = new TreeMap<>();
        for (OrderNumberPart part : OrderNumberPart.values()) {
            partMap.put(part.name(), part.getPartName());
        }
        return partMap;
    }
}
