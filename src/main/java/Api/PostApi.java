package Api;


import Components.Authenticate;
import Models.Follow;
import Models.Post;
import Models.User;
import io.javalin.core.util.FileUtil;
import io.javalin.http.Handler;

import java.util.ArrayList;
import java.util.Objects;


public class PostApi {
    public static String publicPath = "D:\\Program\\instagram-\\dist";

    public static class PostResponse {
        public String title;
        public String description;
        public String image;
        public int likes;
        public int comments;

        public PostResponse(Post post) {
            this.title = post.title;
            this.description = post.description;
            this.image = post.image;
            this.likes = post.likes.size();
            this.comments = post.comments.size();
        }
    }

    public static Handler createPost = ctx -> {

        User user = Authenticate.findUser(ctx.formParam("token"));

        String path = publicPath + "\\profile-images\\" + Objects.requireNonNull(ctx.uploadedFile("image")).getFilename();

        FileUtil.streamToFile(
                Objects.requireNonNull(ctx.uploadedFile("image")).getContent(),
                path
        );

        String title = ctx.formParam("title");
        String description = ctx.formParam("description");

        Post post = new Post(title, description, "/profile-images/" + Objects.requireNonNull(ctx.uploadedFile("image")).getFilename());

        user.page.posts.add(post);

        ctx.json(new PostResponse(post));
    };

    public static class DashboardResponse {
        public String username;
        public int id;
        public String title;
        public String description;
        public String image;
        public boolean isLiked;

        public DashboardResponse(User user, Post post, String username, int id) {
            this.id = id;
            this.username = username;
            this.title = post.title;
            this.description = post.description;
            this.image = post.image;

            for (User user1: post.likes)
            {
                if (user1.username.equals(user.username))
                {
                    this.isLiked = true;
                    break;
                }
            }
        }
    }


    public static Handler Dashboard = ctx -> {
        User user = Authenticate.findUser(ctx.cookie("token"));

        ArrayList<DashboardResponse> response = new ArrayList<>();

        for (Follow follow : Follow.all) {
            if (!follow.from.username.equals(user.username))
                continue;

            for (int i = 0; i < 3 && i < follow.to.page.posts.size(); i++) {
                response.add(new DashboardResponse(user,
                        follow.to.page.posts.get(i), follow.to.username, i
                ));
            }

            ctx.json(response);

//            follow.to.page.posts;
        }
    };

    public static Handler likePost = ctx -> {
        User user = Authenticate.findUser(ctx.cookie("token"));

        User user1 = Authenticate.findUserByUsername(ctx.queryParam("username"));

        int i = Integer.parseInt(Objects.requireNonNull(ctx.queryParam("id")));

        Post post = user1.page.posts.get(i);
        post.like(user);
        ctx.result("ok");
    };

    public static Handler unlikePost = ctx -> {
        User user = Authenticate.findUser(ctx.cookie("token"));

        User user1 = Authenticate.findUserByUsername(ctx.queryParam("username"));

        int i = Integer.parseInt(Objects.requireNonNull(ctx.queryParam("id")));

        Post post = user1.page.posts.get(i);

        post.likes.removeIf((likePost) -> likePost.username.equals(user.username));

        ctx.result("ok");
    };
}
