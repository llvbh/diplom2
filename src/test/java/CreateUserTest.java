import api.UserClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.*;
import pojo.ResponseUser;
import pojo.User;

public class CreateUserTest {
    private final UserClient userClient = new UserClient();
    private User user;
    private static String accessToken;

    @Before
    public void setUp() {
        user = new User("post26748@apple.com", "newName2uu", "Email3333");
    }

    @After
    public void after() throws InterruptedException {
        Thread.sleep(2000);
    }

    @AfterClass
    public static void deleteUser() {
        if (accessToken != null ) {
           UserClient.deleteUser(accessToken);
        }
    }

    @Test
    @DisplayName("Check create user method")
    public void checkCreateNewUser() {
        ResponseUser createUser = userClient.createUser(user);
        Assert.assertTrue(createUser.isSuccess());
        accessToken = createUser.getAccessToken();
    }

    @Test
    @DisplayName("Check user add method with duplicate args")
    public void checkCreateUserWithSameValues() {
        Response createUser = userClient.doNotCreateUser(user, 403);
        System.out.println(createUser.getStatusCode());
        Assert.assertEquals(403, createUser.getStatusCode());
    }

    @Test
    @DisplayName("Check use add method With No Param")
    public void checkCreateUserWithNoEmail(){
        user = new User(null, "qwerty12", "Altynsarin");
        Response createUser = userClient.doNotCreateUser(user, 403);
        Assert.assertEquals("Email, password and name are required fields", 403, createUser.getStatusCode());
    }
}
