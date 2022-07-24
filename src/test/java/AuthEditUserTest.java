import api.UserClient;
import api.UserCredentials;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.BeforeClass;
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

        //assertEquals("thisismyemail5@gmail.com", userInfo.getUser().getEmail());
        assertEquals(true, userInfo.isSuccess());
    }

//    @Test
//    // @DisplayName("Check courier Login method With Wrong Args")
//    public void checkLoginUserWithWrongArgs(){
//        User user = new User(null, "dfsfdsf", "Email3333");
//        UserCredentials cred = UserCredentials.from(user);
//        ResponseUserLoginWithError userInfo = userClient.loginUserWithError(cred, 401, "success");
//        //assertEquals("thisismyemail5@gmail.com", userInfo.getUser().getEmail());
//        assertEquals(false, userInfo.isSuccess());
//    }

    @Test
    // @DisplayName("Check courier Login method With Wrong Args")
    public void checkEditUser(){
        String accessToken = userInfo.getAccessToken();
        System.out.println(accessToken);
        ResponseUserInfo newEmail = new ResponseUserInfo("44thismynewemail@yandx.ru", "44new_namehgfhgfgh");
        ResponseEditUser editUser = userClient.editUser(newEmail, accessToken, 200);
    }

}
