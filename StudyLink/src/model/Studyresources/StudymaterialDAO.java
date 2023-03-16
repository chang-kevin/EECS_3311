package model.Studyresources;

import model.Database.DatabaseConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudymaterialDAO {
    static Connection connection = DatabaseConnection.getConnection();
    public void setmaterialRating(String username,String Study_material_id,int rating) throws SQLException {
        if( rating >= 1 && rating <= 5) {
            String query = "insert into course_ratings (Study_material_id, username, rating) values (?, ?, ?) ON DUPLICATE KEY UPDATE rating = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, Study_material_id);
            ps.setString(2, username);
            ps.setInt(3, rating);
            ps.setInt(4, rating);
            ps.executeUpdate();
        }
    }
    public static double getRating(String Study_material_id) throws SQLException{
        double averageRating = 0;
      String query =  "SELECT AVG(rating) AS AverageRating FROM Study_materials_ratings where Study_material_id = ?" ;
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, Study_material_id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {

             averageRating = rs.getDouble("AverageRating");
        }
        return averageRating;
    }
}
