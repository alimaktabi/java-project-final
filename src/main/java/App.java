import Api.Auth;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.javalin.http.staticfiles.Location;

import java.io.File;
import java.util.Scanner;


public class App {
    private static String publicPath = "D:\\Program\\instagram-\\dist";
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


        app.config.addStaticFiles(publicPath, Location.EXTERNAL);

    }
}
