import api.UserClient;
import api.UserCredentials;
import io.qameta.allure.junit4.DisplayName;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import pojo.*;

import static org.junit.Assert.*;

public class AuthEditUserTest {
    private User user;
    private static UserClient userClient;
    private ResponseUserLogin userInfo;
    private static String accessToken;

    @Before
    public void setUp() {
        userClient = new UserClient();
        user = new User("post24@apple.com", "newName", "Email43333");
    }

    @AfterClass
    public static void returnUsersCred() {
           ResponseUserInfo cred = new ResponseUserInfo("post24@apple.com", "newName");
       ResponseEditUserWithAuth editUser = userClient.editUser(cred, accessToken);
       assertEquals("post24@apple.com", editUser.getUser().getEmail());
    }

    @Test
    @DisplayName("Check  Login method")
    public void checkLoginUser(){
        UserCredentials cred = UserCredentials.from(user);
        userInfo = userClient.loginUser(cred);
        accessToken = userInfo.getAccessToken();
        ResponseUserLogin userInfo = userClient.loginUser(cred);
        assertEquals("post24@apple.com", userInfo.getUser().getEmail());
    }

    @Test
    @DisplayName("Check edit email method")
    public void checkEditAuthUser(){
        ResponseUserInfo newCred = new ResponseUserInfo("xxxxx@apple.com", "yyyyyyyyyyy");
        ResponseEditUserWithAuth editUser = userClient.editUser(newCred, accessToken);
        assertEquals("xxxxx@apple.com", editUser.getUser().getEmail());
        assertEquals("yyyyyyyyyyy", editUser.getUser().getName());
       // assertEquals("123445", editUser.getUser().getName());
    }

    @Test
    @DisplayName("Check Login method With Wrong Args")
    public void checkLoginUserWithWrongArgs(){
        User user = new User(null, "Imya", "123456");
        UserCredentials cred = UserCredentials.from(user);
        ResponseUserLoginWithError userInfo = userClient.loginUserWithError(cred);
        System.out.println(userInfo.isSuccess());
        assertEquals("email or password are incorrect", userInfo.getMessage());
    }

    @Test
    @DisplayName("Check edit method without auth")
    public void checkEditNotAuthUser(){
        ResponseUserInfo newEmail = new ResponseUserInfo("incorrect@google.com", "incorrect");
        ResponseEditUserWithError editUser = userClient.doNotEditUser(newEmail);
        assertFalse(editUser.isSuccess());
    }
}
