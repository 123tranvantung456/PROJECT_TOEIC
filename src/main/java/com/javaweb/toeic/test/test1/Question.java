package com.javaweb.toeic.test.test1;


import com.javaweb.toeic.enums.OrderNumberPart;

public class Question {

        private Long id;


        private String content;
        private OrderNumberPart part;

        // Getter và Setter
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public OrderNumberPart getPart() {
            return part;
        }

        public void setPart(OrderNumberPart part) {
            this.part = part;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        // Các getter và setter khác...
    }
