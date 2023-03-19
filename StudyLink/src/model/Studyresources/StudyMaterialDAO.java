package model.Studyresources;

import model.Database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudyMaterialDAO {
    static Connection connection = DatabaseConnection.getConnection();

    public static void setMaterialRating(String username, String material_id, int rating) throws SQLException {
        if (rating < 1 || rating > 5){
            return;
        }
            String query = "INSERT INTO Study_materials_ratings (material_id, username, rating) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE rating = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, material_id);
            ps.setString(2, username);
            ps.setInt(3, rating);
            ps.setInt(4, rating);
            ps.executeUpdate();
    }

    public static double getRating(String material_id) throws SQLException {
        double averageRating = 0;
        String query = "SELECT AVG(rating) AS AverageRating FROM Study_materials_ratings WHERE material_id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, material_id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            averageRating = rs.getDouble("AverageRating");
        }
        return averageRating;
    }

    public static StudyMaterial getStudyMaterial(String topicId) throws SQLException {
        StudyMaterial studymaterial = null;
        List<String> url = new ArrayList<>();
        String query = "select study_materials.material_id,study_materials_urls.url from study_materials JOIN  study_materials_urls ON study_materials.material_id = study_materials_urls.material_id  WHERE study_materials.topic_id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1,topicId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            url.add(rs.getString("url"));
             studymaterial = new StudyMaterial(rs.getString("material_id"),url,getRating(rs.getString("material_id")));
        }
        return studymaterial;
    }
}
