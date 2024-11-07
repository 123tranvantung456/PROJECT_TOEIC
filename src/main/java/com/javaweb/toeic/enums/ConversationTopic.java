package com.javaweb.toeic.enums;

import java.util.Map;
import java.util.TreeMap;

public enum ConversationTopic {
    COMPANY_GENERAL_OFFICE_WORK("Company - General Office Work"),
    COMPANY_PERSONNEL("Company - Personnel"),
    COMPANY_BUSINESS_MARKETING("Company - Business, Marketing"),
    COMPANY_EVENT_PROJECT("Company - Event, Project"),
    COMPANY_FACILITY("Company - Facility"),
    SHOPPING_SERVICE("Shopping, Service"),
    ORDER_DELIVERY("Order, Delivery"),
    HOUSING("Housing");

    // Trường để lưu mô tả của chủ đề cuộc đối thoại
    private final String description;

    // Constructor để khởi tạo giá trị của trường description
    ConversationTopic(String description) {
        this.description = description;
    }

    // Phương thức để lấy mô tả của chủ đề cuộc đối thoại
    public String getDescription() {
        return description;
    }

    // Phương thức tĩnh để lấy tất cả các chủ đề dưới dạng Map
    public static Map<String, String> getAllTopics() {
        Map<String, String> topicMap = new TreeMap<>();
        for (ConversationTopic topic : ConversationTopic.values()) {
            topicMap.put(topic.name(), topic.getDescription());
        }
        return topicMap;
    }
}
