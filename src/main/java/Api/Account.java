package Api;


import Models.User;
import io.javalin.http.Handler;

public class Account {

    public static class SearchResponse {

    }

    public static Handler SearchUsers = (ctx) -> {
        String query = ctx.queryParam("q");

        for (User user : User.users) {

        }

    };
}
