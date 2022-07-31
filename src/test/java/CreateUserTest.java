import api.UserClient;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import pojo.ResponseUser;
import pojo.ResponseUserWithError;
import pojo.User;

public class CreateUserTest {

    private UserClient userClient;
    private User user;
    private static String accessToken;

    @Before
    public void setUp() {
        userClient = new UserClient();
        user = new User("Thisismy00000@gmail.com", "dfsfdsf", "Email3333");
    }

    @AfterClass
    public static void deleteUser() {
        UserClient.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Check create user method")
    public void checkCreateNewUser() throws InterruptedException {
        ResponseUser createUser = userClient.createUser(user, 200, "success");
        Assert.assertTrue(createUser.getSuccess());

        accessToken = createUser.getAccessToken().substring("Bearer".length() + 1);

        Thread.sleep(2000);
    }

    @Test
    @DisplayName("Check user add method with duplicate args")
    public void checkCreateUserWithSameValues() throws InterruptedException {
        ResponseUserWithError createUser = userClient.doNotCreateUser(user, 403, "success");
        Assert.assertEquals("User already exists", createUser.getMessage());
        Thread.sleep(2000);
    }

    @Test
    @DisplayName("Check use add method With No Param")
    public void checkCreateUserWithNoEmail() throws InterruptedException {
        user = new User(null, "qwerty12", "Altynsarin");
        ResponseUserWithError createUser = userClient.doNotCreateUser(user, 401, "success");
        Assert.assertEquals("Email, password and name are required fields", createUser.getMessage());
        Thread.sleep(2000);
    }
}
