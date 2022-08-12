import api.UserClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.*;
import pojo.ResponseUser;
import pojo.User;

public class CreateUserTest {
    private final UserClient userClient = new UserClient();
    private User user;
    ResponseUser createUser;
    private static String accessToken;

    @Before
    public void setUp() {
        user = new User("nazym_s@apple.com", "nazym", "Email3333");
        createUser = userClient.createUser(user, 200);
        accessToken = createUser.getAccessToken();
}
    @After
    public void after() {
        if (accessToken != null ) {
            UserClient.deleteUser(accessToken);
        }
    }
    @Test
    @DisplayName("Check create user method")
    public void checkCreateNewUser() {
        Assert.assertTrue(createUser.getUser().getEmail().equals("nazym_s@apple.com"));
    }

    @Test
    @DisplayName("Check user add method with duplicate args")
    public void checkCreateUserWithSameValues() {
        Response dublicateUser = userClient.doNotCreateUser(user, 403);
        System.out.println(dublicateUser);
        Assert.assertEquals(403, dublicateUser.getStatusCode());
    }

    @Test
    @DisplayName("Check use add method With No Param")
    public void checkCreateUserWithNoEmail(){
        user = new User("", "qwer8ty12", "hffhfhe44");
        Response createUser = userClient.doNotCreateUser(user, 403);
        Assert.assertEquals("Email, password and name are required fields", 403, createUser.getStatusCode());
    }
}
