package domainobjects;
import java.util.ArrayList;
import java.util.Iterator;

public class userList {
    private ArrayList<User> userlist = new ArrayList<User>();
    public userList(ArrayList<User> userlist) {
        this.userlist = userlist;
    }

    public userList(){
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
