package Models;

import java.util.ArrayList;
import java.util.UUID;

import Helpers.Hash;

public class User {

    public String fullName;
    public String username;
    public String password;
    public Page page;

    public String token = "";

    public static ArrayList<User> users = new ArrayList<User>() {
        {
            add(new User("Sina amini", "09331600113", "123456"));
        }
    };

    public static User mainUser;

    public User(String fullName, String username, String password) {
        this.fullName = fullName;
        this.username = username;
        this.password = Hash.sha256(password);
        this.page = new Page(this);
    }

    public boolean compare(String password) {
        return Hash.sha256(password).equals(this.password);
    }

    public String login() {
        this.token = UUID.randomUUID().toString();
        return token;
    }

}
