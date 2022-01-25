package Models;

import java.util.ArrayList;

public class Page {
    public boolean isPrivate;

    public User user;

    public ArrayList<Post> posts = new ArrayList<>();

    public void addPost(Post post) {
        this.posts.add(post);
    }

    public Page(User user) {
        this.user = user;
    }

}
