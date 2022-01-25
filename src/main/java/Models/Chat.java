package Models;

import java.util.ArrayList;
import java.util.Date;

public class Chat {
    public ArrayList<User> users = new ArrayList<>();

    public ArrayList<Message> messages = new ArrayList<>();

    public static ArrayList<Chat> chats = new ArrayList<>();

    public Date createdAt;
    public String title = null;
    public User admin;

    public Chat(User admin, ArrayList<User> users, String title) {
        this.title = title;
        this.users = users;
        this.createdAt = new Date();
        this.admin = admin;
    }

    public Chat(User admin, ArrayList<User> users) {
        this.title = null;
        this.users = users;
        this.createdAt = new Date();
        this.admin = admin;
    }

    public String getTitle() {
        if (title != null) return title;
        for (User user : users) {
            if (user != User.mainUser)
                return user.fullName;
        }

        return "Error";
    }

    public void addMessage(User user, String message) {
        messages.add(new Message(this, message, user));
    }

    public void listMessages(User user) {
        // TODO: add later
    }
}
