import api.UserClient;
import api.UserCredentials;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import pojo.*;

import static org.junit.Assert.*;

public class AuthEditUserTest {

    private static User user;
    private static UserClient userClient;
    private static String accessToken;

    @BeforeClass
    public static void setUp() {
        userClient = new UserClient();
        user = new User("post24@apple.com", "newName", "123456");
    }

    @AfterClass
    public static void returnUsersCred() {
       ResponseUserInfo cred = new ResponseUserInfo("post24@apple.com", "newName");
       userClient.editUser(cred, accessToken, 200);
    }

    @After
    public void after() throws InterruptedException {
        Thread.sleep(2000);
    }

    @Test
    @DisplayName("Check Login method")
    public void checkLoginUser() {
        UserCredentials cred = UserCredentials.from(user);
        ResponseUserLogin userInfo = userClient.loginUser(cred, 200);
        accessToken = userInfo.getAccessToken();
        assertEquals("post24@apple.com", userInfo.getUser().getEmail());
    }

    @Test
    @DisplayName("Check edit email, name method")
    public void checkEditAuthUser() {
        ResponseUserInfo newCred = new ResponseUserInfo("xxxxx@apple.com", "yyyyyyyyyyy");
        ResponseEditUserWithAuth editUser = userClient.editUser(newCred, accessToken, 200);
        assertEquals("xxxxx@apple.com", editUser.getUser().getEmail());
        assertEquals("yyyyyyyyyyy", editUser.getUser().getName());
    }

    @Test
    @DisplayName("Check Login method With Wrong Args")
    public void checkLoginUserWithWrongArgs() {
        User user = new User("cgvgj@gmail.com", "Imya", "123456");
        UserCredentials cred = UserCredentials.from(user);
        ResponseEditUserWithError userInfo = userClient.loginUserWithError(cred, 401);
        assertFalse(userInfo.isSuccess());
    }

    @Test
    @DisplayName("Check edit method without auth")
    public void checkEditNotAuthUser() {
        ResponseUserInfo newEmail = new ResponseUserInfo("incorrect@google.com", "incorrect");
        ResponseEditUserWithError editUser = userClient.doNotEditUser(newEmail, 401);
        assertFalse(editUser.isSuccess());
    }
}
