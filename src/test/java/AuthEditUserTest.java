import api.UserClient;
import api.UserCredentials;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import pojo.*;

import static org.junit.Assert.*;

public class AuthEditUserTest {

    private UserClient userClient;
    private ResponseUserLogin userInfo;
    String accessToken;

    @Before
    public void setUp() {
        userClient = new UserClient();
        User user = new User("posteee24@apple.com", "newNameddd4", "Email43333");
        UserCredentials cred = UserCredentials.from(user);
        userInfo = userClient.loginUser(cred);
        accessToken = userInfo.getAccessToken();
    }

    @Test
    @DisplayName("Check  Login method")
    public void checkLoginUser(){
        User user = new User("posteee24s@apple.com", "newNameddds4", "Email43s333");
        UserCredentials cred = UserCredentials.from(user);
        ResponseUserLogin userInfo = userClient.loginUser(cred);
        assertEquals("posteee24@apple.com", userInfo.getUser().getEmail());
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
    @DisplayName("Check edit method")
    public void checkEditAuthUser(){
        ResponseUserInfo newEmail = new ResponseUserInfo("post3@apple.com", "newName3");
        ResponseEditUserWithAuth editUser = userClient.editUser(newEmail, accessToken);
        assertEquals("post3@apple.com", editUser.getUser().getEmail());
    }

    @Test
    @DisplayName("Check edit method without auth")
    public void checkEditNotAuthUser(){
        ResponseUserInfo newEmail = new ResponseUserInfo("incorrect@google.com", "incorrect");
        ResponseEditUserWithError editUser = userClient.doNotEditUser(newEmail);
        assertFalse(editUser.isSuccess());
    }
}
