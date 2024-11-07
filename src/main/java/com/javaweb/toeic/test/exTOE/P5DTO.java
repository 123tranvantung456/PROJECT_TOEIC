package com.javaweb.toeic.test.exTOE;
import java.io.Serializable;

public class P5DTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id; // ID tự động tăng (auto-increment)
    private String contentAnswerA;
    private String contentAnswerB;
    private String contentAnswerC;
    private String contentAnswerD;
    private PartFiveQuestionType partFiveQuestionType;
    private String questionContent;
    private String answerExplanation; // Giải thích đáp án
    private Character correctAnswer; // Đáp án đúng (A, B, C, hoặc D)

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContentAnswerA() {
        return contentAnswerA;
    }

    public void setContentAnswerA(String contentAnswerA) {
        this.contentAnswerA = contentAnswerA;
    }

    public String getContentAnswerB() {
        return contentAnswerB;
    }

    public void setContentAnswerB(String contentAnswerB) {
        this.contentAnswerB = contentAnswerB;
    }

    public String getContentAnswerC() {
        return contentAnswerC;
    }

    public void setContentAnswerC(String contentAnswerC) {
        this.contentAnswerC = contentAnswerC;
    }

    public String getContentAnswerD() {
        return contentAnswerD;
    }

    public void setContentAnswerD(String contentAnswerD) {
        this.contentAnswerD = contentAnswerD;
    }

    public PartFiveQuestionType getPartFiveQuestionType() {
        return partFiveQuestionType;
    }

    public void setPartFiveQuestionType(PartFiveQuestionType partFiveQuestionType) {
        this.partFiveQuestionType = partFiveQuestionType;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getAnswerExplanation() {
        return answerExplanation;
    }

    public void setAnswerExplanation(String answerExplanation) {
        this.answerExplanation = answerExplanation;
    }

    public Character getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Character correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    // Enum for question type
    public enum PartFiveQuestionType {
        QUESTION_GRAMMAR,
        QUESTION_PART_OF_SPEECH,
        QUESTION_VOCABULARY
    }
}
