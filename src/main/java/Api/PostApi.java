package Api;


import Components.Authenticate;
import Models.Post;
import Models.User;
import io.javalin.core.util.FileUtil;
import io.javalin.http.Handler;

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

}
