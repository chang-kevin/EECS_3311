package tests.unit;

import controller.AccountManagement;
import model.User.User;
import model.User.UserDAO;
import helpers.UserRole;
import model.User.UserSession;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.sql.SQLException;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AccountManagementTest {
    private AccountManagement accountManagement;
    private UserDAO mockUserDAO;
    private User mockUser;
    private UserSession mockUserSession;

    @BeforeEach
    public void setup() throws SQLException {
        mockUserDAO = Mockito.mock(UserDAO.class);
        mockUser = Mockito.mock(User.class);
        mockUserSession = Mockito.mock(UserSession.class);

        when(mockUserSession.getCurrentUser()).thenReturn(mockUser);
        when(mockUserDAO.getUser(Mockito.anyString())).thenReturn(mockUser);
        when(mockUser.getUsername()).thenReturn("testuser");
        when(mockUser.getPassword()).thenReturn("testpassword");
        when(mockUser.getFirstName()).thenReturn("Test");
        when(mockUser.getLastName()).thenReturn("User");
        when(mockUser.getRole()).thenReturn(UserRole.STUDENT);

        accountManagement = new AccountManagement();
    }
}
