package model.Topic;

import model.Database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TopicDAO {
    static Connection connection = DatabaseConnection.getConnection();

    public List<String> getTopicList() throws SQLException{
        String query = "select * from Topics";
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<String> topics = new ArrayList<>();

        while (rs.next()){
            topics.add(rs.getString("topic_id") + ": " + rs.getString("topic_name"));
        }
        return topics;
    }
}
