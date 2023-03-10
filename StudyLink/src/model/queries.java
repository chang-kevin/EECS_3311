package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class queries {
    static Connection connection = DatabaseConnection.getConnection();

    public queries() throws SQLException {
    }

    public static List securityQuestion(String username) throws SQLException {
        String query = "Select Security_Questions.question_text,User_Security_Questions.answer from Security_Questions join User_Security_Questions on User_Security_Questions.question_id = Security_Questions.question_id where User_Security_Questions.username =?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1,username);
        ResultSet rs = ps.executeQuery();
        List x = new ArrayList();
        while (rs.next()) {
             x.add(rs.getString("question_text"));
           x.add(rs.getString("answer"));
        }
        return x;
    }
    public static int addSecurityQNA(String username,String x,String text) throws SQLException {
        String query = "insert into User_Security_Questions (username,question_id,answer) values (?,?,?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1,username);
        ps.setString(2,x);
        ps.setString(3,text);
        int n = ps.executeUpdate();
        return n;



    }
    public  static  String getQuestion(String id) throws SQLException {
        String x = null;
        String query = "Select question_text from Security_Questions where question_id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1,id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            x =  rs.getString("question_text");
        }

        return x;
    }
}
