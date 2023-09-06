import java.util.HashMap;
import User.java;
public class Auth {
    private HashMap<String, User> auth;

    public Auth(){
    this.auth = new HashMap<String, User>();
    }

    public boolean checkUserName(String username){
        return auth.containsKey(username);
    }
    public boolean checkPassword(String username, String Userpassword){
        return auth.get(username).getPassword().equals(Userpassword);
    }

    public void addUser(String username, User Userpassword){
        auth.put(username, Userpassword);
    }
    public User getUser(String userName){
        return this.auth.get(userName);
    }
    

}