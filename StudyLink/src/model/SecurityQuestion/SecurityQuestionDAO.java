package model.SecurityQuestion;

import model.Database.DatabaseConnection;
import model.User.UserSecurityQuestion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SecurityQuestionDAO {
    static Connection connection = DatabaseConnection.getConnection();

    public static UserSecurityQuestion getUserSecurityQuestion(String username) throws SQLException {
        String query = "Select user_security_questions.question_id, question_text, answer from Security_Questions join User_Security_Questions on User_Security_Questions.question_id = Security_Questions.question_id where User_Security_Questions.username =?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();

        UserSecurityQuestion usq = null;
        SecurityQuestion sq = null;
        while (rs.next()) {
            String securityQuestionId = rs.getString("question_id");
            String securityQuestion = rs.getString("question_text");
            String securityQuestionAnswer = rs.getString("answer");
            sq = new SecurityQuestion(securityQuestionId, securityQuestion);
            usq = new UserSecurityQuestion(sq, securityQuestionAnswer, username);
        }
        return usq;
    }

    public static int addSecurityQNA(String username, String questionId, String questionAnswer) throws SQLException {
        String query = "insert into User_Security_Questions (username,question_id,answer) values (?,?,?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, username);
        ps.setString(2, questionId);
        ps.setString(3, questionAnswer);
        int n = ps.executeUpdate();
        return n;
    }

    public  static  String getQuestionById(String questionId) throws SQLException {
        String x = null;
        String query = "Select question_text from Security_Questions where question_id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, questionId);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            x =  rs.getString("question_text");
        }
        return x;
    }
}
