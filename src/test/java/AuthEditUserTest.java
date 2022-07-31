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
        userInfo = userClient.loginUser(cred, 200, "success");
        // Убираем слово Bearer из аксес токена
        userInfo.setAccessToken(userInfo.getAccessToken().substring("Bearer".length() + 1));
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
        ResponseUserLoginWithError userInfo = userClient.loginUserWithError(cred, 401, "success");
        assertFalse(userInfo.isSuccess());
    }

    @Test
    @DisplayName("Check edit method")
    public void checkEditAuthUser(){
        String accessToken = userInfo.getAccessToken();
        ResponseUserInfo newEmail = new ResponseUserInfo("44thismynewemail@yandx.ru", "44new_namehgfhgfgh");
        ResponseEditUserWithAuth editUser = userClient.editUser(newEmail, accessToken);
        assertEquals("44thismynewemail@yandx.ru", editUser.getUser().getEmail());
    }

    @Test
    @DisplayName("Check edit method without auth")
    public void checkEditNotAuthUser(){
        ResponseUserInfo newEmail = new ResponseUserInfo("44thismynewemail@yandx.ru", "44new_namehgfhgfgh");
        ResponseEditUserWithError editUser = userClient.doNotEditUser(newEmail);
        assertFalse(editUser.isSuccess());
    }
}
