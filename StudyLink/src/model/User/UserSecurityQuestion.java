package model.User;

import model.SecurityQuestion.SecurityQuestion;

public class UserSecurityQuestion {
    private String username;
    private SecurityQuestion securityQuestion;
    private String securityQuestionAnswer;

    public UserSecurityQuestion(SecurityQuestion securityQuestion, String securityQuestionAnswer, String username) {
        this.username = username;
        this.securityQuestion = securityQuestion;
        this.securityQuestionAnswer = securityQuestionAnswer;
    }

    public SecurityQuestion getSecurityQuestion() {
        return securityQuestion;
    }

    public String getSecurityQuestionAnswer() {
        return securityQuestionAnswer;
    }

    public String getUserName() {
        return username;
    }
}
