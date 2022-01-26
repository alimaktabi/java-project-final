package Components;

import Models.User;

public class Authenticate {
    public static User findUser(String token) throws Exception {
        for (User user: User.users){
            if (user.token.equals(token))
                return user;
        }
        throw new Exception("Authentication required");
    }

    public static User findUserByUsername(String username) throws Exception {
        for (User user: User.users)
            if (user.username.equals(username))
                return user;

        throw new Exception("User not found");
    }
}
