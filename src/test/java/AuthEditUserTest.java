import api.UserClient;
import api.UserCredentials;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import pojo.*;

import static org.junit.Assert.*;

public class AuthEditUserTest {

    private UserClient userClient;
    private ResponseUserLogin userInfo;

    @Before
    public void setUp() {
        userClient = new UserClient();
        User user = new User("44thismynewemail@yandx.ru", "dfsfdsf", "Email3333");
        UserCredentials cred = UserCredentials.from(user);
        userInfo = userClient.loginUser(cred);
    }

    @Test
    @DisplayName("Check  Login method")
    public void checkLoginUser(){
        assertTrue(userInfo.isSuccess());
    }


    @Test
    @DisplayName("Check Login method With Wrong Args")
    public void checkLoginUserWithWrongArgs(){
        User user = new User(null, "dfsfdsf", "Email3333");
        UserCredentials cred = UserCredentials.from(user);
        ResponseUserLoginWithError userInfo = userClient.loginUserWithError(cred);
        assertFalse(userInfo.isSuccess());
    }

    @Test
    @DisplayName("Check edit method")
    public void checkEditAuthUser(){
        String accessToken = userInfo.getAccessToken();
        ResponseUserInfo newEmail = new ResponseUserInfo("newEmail@apple.com", "newName");
        ResponseEditUserWithAuth editUser = userClient.editUser(newEmail, accessToken);
        assertEquals("Мэйл не соответствует:", "newEmail@apple.com", editUser.getUser().getEmail());
        assertEquals("Имя не соответствует:", "newName", editUser.getUser().getName());
    }

    @Test
    @DisplayName("Check edit method without auth")
    public void checkEditNotAuthUser(){
        ResponseUserInfo newEmail = new ResponseUserInfo("incorrect@google.com", "incorrect");
        ResponseEditUserWithError editUser = userClient.doNotEditUser(newEmail);
        assertFalse(editUser.isSuccess());
    }
}
