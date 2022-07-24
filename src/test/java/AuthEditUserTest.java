import api.UserClient;
import api.UserCredentials;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pojo.*;

import static org.junit.Assert.assertEquals;

public class AuthEditUserTest {

    private UserClient userClient;
    ResponseUserLogin userInfo;

    @Before
    public void setUp() {
        userClient = new UserClient();
        User user = new User("44thismynewemail@yandx.ru", "dfsfdsf", "Email3333");
        UserCredentials cred = UserCredentials.from(user);
        userInfo = userClient.loginUser(cred, 200, "success");

        // Убираем слово Bearer из аксес токена
        userInfo.setAccessToken(userInfo.getAccessToken().substring("Bearer".length() + 1));

        System.out.println(userInfo);
    }

    @Test
   // @DisplayName("Check courier Login method")
    public void checkLoginUser(){
       assertEquals(true, userInfo.isSuccess());
    }

    @Test
    // @DisplayName("Check courier Login method With Wrong Args")
    public void checkLoginUserWithWrongArgs(){
        User user = new User(null, "dfsfdsf", "Email3333");
        UserCredentials cred = UserCredentials.from(user);
        ResponseUserLoginWithError userInfo = userClient.loginUserWithError(cred, 401, "success");
        //assertEquals("thisismyemail5@gmail.com", userInfo.getUser().getEmail());
        assertEquals(false, userInfo.isSuccess());
    }

    @Test
    public void checkEditAuthUser(){
        String accessToken = userInfo.getAccessToken();
        //System.out.println(accessToken);
        ResponseUserInfo newEmail = new ResponseUserInfo("44thismynewemail@yandx.ru", "44new_namehgfhgfgh");
        ResponseEditUserWithAuth editUser = userClient.editUser(newEmail, accessToken);
        assertEquals("44thismynewemail@yandx.ru", editUser.getUser().getEmail());
        //System.out.println(editUser);
    }

    @Test
    public void checkEditNotAuthUser(){
        ResponseUserInfo newEmail = new ResponseUserInfo("44thismynewemail@yandx.ru", "44new_namehgfhgfgh");
        ResponseEditUserWithError editUser = userClient.doNotEditUser(newEmail);
        //System.out.println(editUser);
        assertEquals(false, editUser.isSuccess());
    }


}
