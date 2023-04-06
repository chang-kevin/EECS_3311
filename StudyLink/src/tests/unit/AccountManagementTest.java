package tests.unit;

import controller.AccountManagement;
import helpers.UserRole;
import model.User.User;
import model.User.UserDAO;
import model.User.UserSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
//integration test of accountmanagement page with database and user class
public class AccountManagementTest {
    private AccountManagement accountManagement;

    private User stubuser;


    @BeforeEach
    public void setup() throws SQLException {
        stubuser = new User.UserBuilder("stubname", "stubpassword")
                .setFirstName("stub")
                .setLastName("user")
                .setRole(UserRole.STUDENT)
                .build();
        UserSession.setCurrentUser(stubuser);
        UserDAO.add(stubuser);
        accountManagement = new AccountManagement();
        accountManagement.dispose();
    }
    @AfterEach
    public void teardown() throws SQLException {
        UserDAO.delete(stubuser.getUsername());
    }
    // Test to verify that user details of logged user is visible
    @Test
    public void userDetails(){
        assertEquals("stubname", accountManagement.getUsernameText());
        assertEquals("stub", accountManagement.getFirstNameText());
        assertEquals("user", accountManagement.getLastNameText());
        assertEquals("student", accountManagement.getRoleTextField());

    }
    // Test Text fields are working
    @Test
    public void verifyTextFields(){
        assertTrue(accountManagement.getFirstNameJText().isEnabled());
        assertTrue(accountManagement.getLastNameJText().isEnabled());
    }
    // test to verify changes are updated to database integration with database
    @Test
    public void update() throws SQLException {
        accountManagement.setFirstNameText("newstubname");
        assertEquals("newstubname", accountManagement.getFirstNameText());
        stubuser = new User.UserBuilder("stubname", "stubpassword")
                .setFirstName(accountManagement.getFirstNameText())
                .setLastName(accountManagement.getLastNameText())
                .setRole(accountManagement.getRoleTextField())
                .build();
        UserDAO.updateUserInfo(stubuser);
        assertEquals("newstubname", UserDAO.getUser(stubuser.getUsername()).getFirstName());
        // Updated in database
    }
}
