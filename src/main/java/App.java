import Api.Account;
import Api.Auth;
import Api.PostApi;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.javalin.http.staticfiles.Location;

import java.io.File;
import java.util.Scanner;


public class App {
    private static String htmlFilePath = "D:\\Program\\instagram-\\index.html";

    public static Handler index = (ctx) -> {
        File index = new File(htmlFilePath);

        Scanner scanner = new Scanner(index);

        StringBuilder res = new StringBuilder();

        while (scanner.hasNextLine())
            res.append(scanner.nextLine());


        ctx.html(res.toString());
    };


    public static void main(String[] args) {
        Javalin app = Javalin.create().start(3000);


        app.get("/", App.index);

        app.post("/login", Auth.Login);

        app.post("/register", Auth.Register);

        app.get("/search", (Handler) Account.SearchUsers);

        app.post("/get-profile", (Handler) Account.getProfile);

        app.post("/update-profile", (Handler) Account.updateProfile);

        app.post("/create-post", (Handler) PostApi.createPost);

        app.post("/dashboard", (Handler) Account.Dashboard);

        app.get("/get-page", (Handler) Account.getPage);

        app.post("/follow", (Handler) Account.follow);

        app.post("/unfollow", (Handler) Account.unfollow);

        app.config.addStaticFiles(PostApi.publicPath, Location.EXTERNAL);

    }
}
