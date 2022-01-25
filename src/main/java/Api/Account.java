package Api;


import Components.Authenticate;
import Models.User;
import io.javalin.http.Handler;

public class Account {

    public static class SearchResponse {

    }

    public static Handler getProfile = (ctx) -> {
        String token = ctx.formParam("token");

        User user = Authenticate.findUser(token);

        Auth.UserResponse userResponse = new Auth.UserResponse(user);

        ctx.json(userResponse);
    };

    public static Handler updateProfile = (ctx) -> {
        String token = ctx.formParam("token");

        String fullName = ctx.formParam("fullName");
        String username = ctx.formParam("username");

        User user = Authenticate.findUser(token);

        user.username = username;
        user.fullName = fullName;


        Auth.UserResponse userResponse = new Auth.UserResponse(user);

        ctx.json(userResponse);

    };

    public static Handler SearchUsers = (ctx) -> {
        String query = ctx.queryParam("q");

        for (User user : User.users) {

        }

    };
}
