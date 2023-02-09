package components;

import java.util.ArrayList;

public class UserList {
    public static final UserList instance = new UserList();

    public static ArrayList<String> emails = new ArrayList<>();
    int x;
    int y;

    public static ArrayList<String> passwords = new ArrayList<>();

    private  UserList(){};




    public ArrayList<String> getEmail() {
        return emails;
    }

    public void setEmail(ArrayList<String> emails) {
        this.emails = emails;

    }

    public ArrayList<String> getPassword() {
        return passwords;
    }

    public void setPassword(String password) {
        this.passwords.add(password);
    }
    public boolean authemail(String text){
        boolean login = false;
        for(int i =0;i<emails.size();i++){
            if(text.matches(emails.get(i))){
                login = true;
                instance.x = i;
            }


        }


        return login;


    }
    public boolean authpass(String text){
        boolean login = false;
        for(int j =0;j<passwords.size();j++){
            if(text.matches(passwords.get(j))) {
                login = true;
                instance.y = j;
            }


        }


        return login;

    }

}
