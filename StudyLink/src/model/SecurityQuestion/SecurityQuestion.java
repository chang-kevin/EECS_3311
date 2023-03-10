package model.SecurityQuestion;

public class SecurityQuestion {
    private String questionId;
    private String questionText;

    public SecurityQuestion(String questionId, String questionText) {
        this.questionId = questionId;
        this.questionText = questionText;
    }

    public String getQuestionId() {
        return questionId;
    }

    public String getQuestionText() {
        return questionText;
    }
}
