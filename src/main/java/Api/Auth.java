package Api;

import Models.User;
import io.javalin.http.Handler;

public class Auth {

    static class UserResponse {
        public String fullName;
        public String username;
        public String token;

        public UserResponse(User user) {
            this.username = user.username;
            this.fullName = user.fullName;
            token = user.token;
        }
    }

    public static Handler Login = (ctx) -> {
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");

        User user = null;

        for (User user1 : User.users) {
            if (user1.username.equals(username) && user1.compare(password))
            {
                user = user1;
                break;
            }
        }

        if (user == null)
        {
            ctx.status(401);
            ctx.result("Not Found");
            return;
        }

        user.login();

        UserResponse userResponse = new UserResponse(user);

        ctx.json(userResponse);
    };

}
