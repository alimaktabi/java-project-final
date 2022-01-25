package Models;

import java.util.ArrayList;

public class Follow {
    public User from;
    public User to;

    public static ArrayList<Follow> all = new ArrayList<>();

    public Follow(User from, User to) {
        this.from = from;
        this.to = to;
    }
}
