package api;

import lombok.Data;
import pojo.User;

@Data
public class UserCredentials {

    private String email;
    private String password;

    public UserCredentials(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    public static UserCredentials from(User user) {
        return new UserCredentials(user);
    }
}