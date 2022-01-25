package Models;

import java.util.Date;

public class Message {
    public User from;

    public String message;

    public Chat chat;

    public Date date;

    public Message(Chat chat, String message, User user) {
        this.from = user;
        this.message = message;
        this.chat = chat;
        this.date = new Date();
    }

}
