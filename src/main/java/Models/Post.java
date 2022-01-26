package Models;

import java.util.ArrayList;

public class Post {
    public String title;

    public String description;
    public String image;

    public ArrayList<User> likes = new ArrayList<>();

    public ArrayList<Comment> comments = new ArrayList<>();

    public Post(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Post(String title, String description, String image) {
        this.title = title;
        this.description = description;
        this.image = image;
    }

    public void like(User user) {
        this.likes.add(user);
    }

    public void comment(String comment) {
        this.comments.add(new Comment(comment, User.mainUser));
    }

}
