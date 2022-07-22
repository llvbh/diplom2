package api;
import lombok.Data;
import pojo.User;
@Data
public class UserCredentials {
    private String name;
    private String password;

    public UserCredentials(User user) {
        this.name = user.getName();
        this.password = user.getPassword();
    }

    public static UserCredentials from(User user) {
        return new UserCredentials(user);
    }
}