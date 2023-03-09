package model.Files;
import controller.globalVariable;
import model.DatabaseConnection;
import model.User.User;
import model.User.UserDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FileImplementation {
    static Connection connection = DatabaseConnection.getConnection();
    public int add (Files file) throws SQLException{
        User user = UserDAO.getUser(globalVariable.s);
        String query = "insert into User_Files (file_id, username, course_id, topic_id, file_name, file_type, file_size, file_data, file_notes " +
                "VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, file.getFile_id());
        //Username
        ps.setString(2, user.getUsername());
        //Course_id
//        ps.setString(3, );
        //Topic
//        ps.setString(4, );
//        File Name
//        ps.setString(5, file.getFile_name());
        //File type
//        ps.setString

        return 0;
    }
}
