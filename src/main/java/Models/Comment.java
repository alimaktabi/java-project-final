package Models;

public class Comment {
    public String content;
    public User user;

    public Comment(String content, User user) {
        this.content = content;
        this.user = user;
    }
}
