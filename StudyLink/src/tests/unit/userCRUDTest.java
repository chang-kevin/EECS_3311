package tests.unit;


// Authenticator test is in helpers package
import helpers.Authenticator.Authenticator;
import helpers.UserRole;

import model.SecurityQuestion.SecurityQuestionDAO;
import model.User.User;
import model.User.UserDAO;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.function.Try;

import javax.swing.*;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
public class userCRUDTest {
    String email1 = "johndoe@my.yorku.ca";
    String email2 = "foobar@my.yorku.ca";
    String password1 = "12345";
    String password2 = "67890";

    User userJohn = new User.UserBuilder(email1, password1)
            .setFirstName("John")
            .setLastName("Doe")
            .setRole(UserRole.STUDENT)
            .build();
    User userSean = new User.UserBuilder(email1,password2)
            .setFirstName("Sean")
            .setLastName("Harper")
            .setRole(UserRole.STUDENT)
            .build();

    @Test
    public void addUser() throws SQLException {
         // User is not added yet
        assertFalse(Authenticator.authenticateUser(email1,password1));
        UserDAO.add(userJohn);
        // User is added now
        assertTrue(Authenticator.authenticateUser(email1, password1));
        UserDAO.delete(userJohn.getUsername());


    }
    // edge case for adding user a new user with already existing username should not be added
    @Test
    public void whenExceptionThrown_thenAssertionSucceeds() throws SQLException {
        String message = "Duplicate entry 'johndoe@my.yorku.ca' for key 'users.PRIMARY'";
        String e = null;
        try {
            UserDAO.add(userJohn);
            // Cant add user with already exisitng username will have exception
            UserDAO.add(userSean);
            UserDAO.delete(userJohn.getUsername());




        } catch (Exception ex) {
            e = ex.getMessage();
            UserDAO.delete(userJohn.getUsername());

        }
        assertTrue(message.equals(e));
    }
    @Test
    public void updateUser() throws SQLException {
        // User is not added yet
        assertFalse(Authenticator.authenticateUser(email1,password1));
        UserDAO.add(userJohn);
        // User is added now
        assertTrue(Authenticator.authenticateUser(email1, password1));
        assertFalse(Authenticator.authenticateUser(email1,password2));
        userJohn.setPassword(password2);
        UserDAO.update(userJohn);// this update database now user is updated in database
        assertTrue(Authenticator.authenticateUser(email1,password2));
        assertFalse(Authenticator.authenticateUser(email1,password1));
        UserDAO.delete(userJohn.getUsername());

    }
    //Edge case of deleting that is when user doesnot exists in database
    @Test
    public void deleteUser() throws SQLException{
        // User is not added yet
        assertFalse(Authenticator.authenticateUser(email1,password1));
        UserDAO.add(userJohn);
        // User is added now
        assertTrue(Authenticator.authenticateUser(email1, password1));
        assertFalse(UserDAO.getUser(userJohn.getUsername()) == null);
        UserDAO.delete(userJohn.getUsername());// User removed
        assertTrue(UserDAO.getUser(userJohn.getUsername()) == null);
        assertFalse(Authenticator.authenticateUser(email1,password1));

    }
    @Test
    public void deleteUser2() throws SQLException{
        //if user doesnt exist it returns zero if its exists and deleted from database it returns 1
        assertEquals(0,UserDAO.delete(userSean.getUsername()));
        assertNotEquals(1,UserDAO.delete(userSean.getUsername()));

        UserDAO.add(userSean);// now user is added to databse

        assertEquals(1,UserDAO.delete(userSean.getUsername()));
        assertTrue(UserDAO.getUser(userSean.getUsername()) == null);




    }


}
