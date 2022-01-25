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
}
