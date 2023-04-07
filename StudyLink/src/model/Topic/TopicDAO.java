package model.Topic;

import helpers.HyperlinkReg;
import model.Database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Database.DatabaseConnection;
import model.User.User;
import model.User.UserSession;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TopicDAO {
    static Connection connection = DatabaseConnection.getConnection();

    public List<String> getTopicList(int courseCode) throws SQLException{
        String query = "select t.topic_name from (course_topics AS ct join topics AS t on ct.topic_id = t.topic_id) " +
                "join courses AS c on c.course_id = ct.course_id where c.course_id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, courseCode);
        ResultSet rs = ps.executeQuery();
        List<String> topics = new ArrayList<>();

        while (rs.next()){
            topics.add(rs.getString("topic_name"));
        }
        return topics;
    }

    public int getTopicId(String topicName) throws SQLException{
        int courseNum = 0;
        String query = "select topic_id from topics where topics.topic_name=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, topicName);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            courseNum = rs.getInt(1);
        }
        return courseNum;
    }

    public int getCountOfStudyMaterials() throws SQLException {
        String query = "select count(*) from study_materials_urls";
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        System.out.println(rs);
        return count;
    }

    public int insertIntoStudyMaterials(String type, String topicName) throws SQLException {
        int count = getCountOfStudyMaterials();
        int topicNum = getTopicId(topicName);
        String query = "insert into study_materials(material_id, material_name, topic_id, username) values(?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, count+1 );
        ps.setString(2, type);
        ps.setInt(3, topicNum);
        ps.setString(4, UserSession.getInstance().getCurrentUser().getUsername());
        int result = ps.executeUpdate();
        return result;
    }

    public int insertIntoURL(String url) throws SQLException{
        int count = getCountOfStudyMaterials();
        String query = "insert into study_materials_urls (url_id, material_id, url) values (?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, count +1);
        ps.setInt(2, count );
        ps.setString(3, url);
        int result = ps.executeUpdate();
        return result;
    }

    public int insertIntoCourseMaterials(int course) throws SQLException{
        int count = getCountOfStudyMaterials();
        String query = "insert into course_study_materials(course_id, study_material_id) values (?, ?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, course);
        ps.setInt(2, count);
        int rs = ps.executeUpdate();
        return rs;
    }
    public int getStudyMaterialId(String topicName) throws SQLException {
        int id = 0;
        int topicNum = getTopicId(topicName);
        String query = "SELECT material_id FROM study_materials WHERE topic_id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1,topicNum);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            id = rs.getInt(1);
        }
        return id;
    }
    public static ArrayList<String> getUrls(int id) throws SQLException {

        String query = " SELECT study_materials_urls.url FROM study_materials_urls \n" +
                "JOIN study_materials ON study_materials.material_id = study_materials_urls.material_id\n" +
                "JOIN course_study_materials ON course_study_materials.study_material_id = study_materials.material_id\n" +
                "WHERE course_study_materials.course_id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        ArrayList<String> urls = new ArrayList<>();
        while(rs.next()){
            urls.add(rs.getString(1));
        }
        return urls;


    }

    }
