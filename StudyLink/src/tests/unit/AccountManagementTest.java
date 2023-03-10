package tests.unit;

import controller.AccountManagement;
import model.User.User;
import model.User.UserDAO;
import model.User.UserRole;
import model.User.UserSession;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import view.dashboard.Dashboard;
import javax.swing.*;
import java.sql.SQLException;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AccountManagementTest {
    private AccountManagement accountManagement;
    private UserDAO mockUserDAO;
    private User mockUser;
    private UserSession mockUserSession;
    private Dashboard mockDashboard;

    @BeforeEach
    public void setup() throws SQLException {
        mockUserDAO = Mockito.mock(UserDAO.class);
        mockUser = Mockito.mock(User.class);
        mockUserSession = Mockito.mock(UserSession.class);
        mockDashboard = Mockito.mock(Dashboard.class);

        when(mockUserSession.getCurrentUser()).thenReturn(mockUser);
        when(mockUserDAO.getUser(Mockito.anyString())).thenReturn(mockUser);
        when(mockUser.getUsername()).thenReturn("testuser");
        when(mockUser.getPassword()).thenReturn("testpassword");
        when(mockUser.getFirstName()).thenReturn("Test");
        when(mockUser.getLastName()).thenReturn("User");
        when(mockUser.getRole()).thenReturn(UserRole.STUDENT);

        accountManagement = new AccountManagement();
    }

    @AfterEach
    public void tearDown() {

    }

    @Test
    public void testSaveBtnActionPerformed() throws SQLException {
//        JButton saveButton = (JButton) TestUtils.getChildNamed(accountManagement, "saveButton");
//        JTextField firstNameText = (JTextField) TestUtils.getChildNamed(accountManagement, "firstNameText");
//        JTextField lastNameText = (JTextField) TestUtils.getChildNamed(accountManagement, "lastNameText");
//        JTextField roleTextField = (JTextField) TestUtils.getChildNamed(accountManagement, "roleTextField");
//
//        firstNameText.setText("Updated");
//        lastNameText.setText("User");
//        roleTextField.setText(UserRole.ADMIN.toString());
//
//        saveButton.doClick();

//        User updatedUser = new User.UserBuilder(mockUser.getUsername(), mockUser.getPassword())
//                .setFirstName(firstNameText.getText())
//                .setLastName(lastNameText.getText())
//                .setRole(UserRole.ADMIN)
//                .build();
//        verify(mockUserDAO).update(updatedUser);
//        verify(mockDashboard).setVisible(true);
//        verify(mockDashboard).dispose();
    }
}
