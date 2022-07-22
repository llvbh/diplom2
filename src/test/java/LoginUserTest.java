import api.UserClient;
import api.UserCredentials;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import pojo.User;

public class LoginUserTest {

    private UserClient userClient;
    //private int userId;

    @Before
    public void setUp() {
        userClient = new UserClient();
    }

    @Test
   // @DisplayName("Check courier Login method")
    public void checkLoginCourier(){
        User user = new User("Nazym505e11@yandex.ru", "qwerty12", "Altynsarin");
        UserCredentials cred = UserCredentials.from(user);
        ValidatableResponse userId = userClient.loginUser(cred, 200, "id");
        System.out.println(userId);
        //assertNotEquals(0, userId);
    }
}
