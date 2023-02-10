package domainobjects;
import java.util.ArrayList;
import java.util.Iterator;
 
public class UserList {
    private static UserList userListInstance = null;
    private ArrayList<User> userlist = null;

    private UserList(){
        userlist = new ArrayList<User>();
    }

    public static UserList getInstance(){
        if(userListInstance == null){
            userListInstance = new UserList();
        }
        return userListInstance;
    }

    public ArrayList<User> getUserlist() {
        return userlist;
    }

    public void setUserlist(ArrayList<User> userlist) {
        this.userlist = userlist;
    }

    public void addUser(User user){
        userlist.add(user);
    }

    public boolean userlogin(String email,String password){
        boolean login = false;
        for (Iterator<User> i = userlist.iterator();
             i.hasNext(); ) {
            User user = i.next();
            if (user.getEmailid().matches(email) || user.getPassword().matches(password)){
                login = true;
            }

            }
        return login;
    }
}
