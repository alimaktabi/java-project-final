package Api;


import Components.Authenticate;
import Models.Follow;
import Models.Post;
import Models.User;
import io.javalin.http.Handler;

import java.util.ArrayList;
import java.util.Objects;

public class Account {

    public static class UserPageResponse {
        public String username;
        public String fullName;
        public int followers = 0;
        public int following = 0;
        public ArrayList<Post> posts;

        public boolean isFollowing = false;
        public boolean followed = false;

        public UserPageResponse(User user) {
            for (Follow follow : Follow.all) {
                if (follow.from.username.equals(user.username)) {
                    this.following++;
                } else if (follow.to.username.equals(user.username)) {
                    this.followers++;
                }
            }

            this.username = user.username;
            this.fullName = user.fullName;
            this.posts = user.page.posts;
        }

        public UserPageResponse(User user, User auth) {

            for (Follow follow : Follow.all) {
                if (
                        follow.from.username.equals(auth.username) &&
                        follow.to.username.equals(user.username)
                ) {
                    this.isFollowing = true;
                }
                if (
                        follow.from.username.equals(user.username) &&
                        follow.to.username.equals(auth.username)
                ) {
                    this.followed = true;
                }
                if (follow.from.username.equals(user.username)) {
                    this.following++;
                } else if (follow.to.username.equals(user.username)) {
                    this.followers++;
                }
            }

            this.username = user.username;
            this.fullName = user.fullName;
            this.posts = user.page.posts;
        }
    }

    public static Handler follow = ctx -> {
        User user = Authenticate.findUser(ctx.cookie("token"));

        User to = Authenticate.findUserByUsername(ctx.formParam("username"));

        Follow follow1 = new Follow(user, to);

        Follow.all.add(follow1);

        ctx.result("ok");
    };

    public static Handler unfollow = ctx -> {
        User user = Authenticate.findUser(ctx.cookie("token"));

        User to = Authenticate.findUserByUsername(ctx.formParam("username"));

        Follow.all.removeIf(follow1 -> follow1.from.username.equals(user.username) && follow1.to.username.equals(to.username));

        ctx.result("ok");
    };

    public static Handler getPage = (ctx) -> {
        String username = ctx.queryParam("username");

        User user = Authenticate.findUserByUsername(username);

        User authUser = Authenticate.findUser(ctx.cookie("token"));

        ctx.json(new UserPageResponse(user, authUser));
    };

    public static Handler getProfile = (ctx) -> {
        String token = ctx.cookie("token");

        User user = Authenticate.findUser(token);

        Auth.UserResponse userResponse = new Auth.UserResponse(user);

        ctx.json(userResponse);
    };

    public static Handler updateProfile = (ctx) -> {
        String token = ctx.cookie("token");

        String fullName = ctx.formParam("fullName");
        String username = ctx.formParam("username");

        User user = Authenticate.findUser(token);

        user.username = username;
        user.fullName = fullName;

        Auth.UserResponse userResponse = new Auth.UserResponse(user);

        ctx.json(userResponse);

    };

    public static Handler SearchUsers = (ctx) -> {
        String query = Objects.requireNonNull(ctx.queryParam("q")).toLowerCase();

        User auth = Authenticate.findUser(ctx.cookie("token"));

        ArrayList<Auth.UserResponse> response = new ArrayList<>();

        for (User user : User.users) {
            if (auth.username.equals(user.username))
                continue;

            if (user.username.contains(query) || user.fullName.contains(query))
                response.add(new Auth.UserResponse(user));
        }

        ctx.json(response);
    };

    public static Handler Dashboard = (ctx) -> {
        User user = Authenticate.findUser(ctx.cookie("token"));

        ctx.json(new UserPageResponse(user));
    };

    public static Handler getPost = ctx -> {
        User user = Authenticate.findUser(ctx.cookie("token"));

        User user1 = Authenticate.findUserByUsername(ctx.queryParam("username"));

        Post post = user1.page.posts.get(Integer.parseInt(Objects.requireNonNull(ctx.queryParam("id"))));

        ctx.json(post);
    };
}
