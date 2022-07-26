import api.OrderClient;
import api.UserClient;
import api.UserCredentials;
import org.junit.Before;
import org.junit.Test;
import pojo.*;

import static org.junit.Assert.assertEquals;

public class CreateOrderTest {
    private UserClient userClient;
    private OrderClient orderClient;
    ResponseUserLogin userInfo;
    private final String INGREDIENT = "61c0c5a71d1f82001bdaaa6d";
    private final String INGREDIENT2 = "61c0c5a71d1f82001bdaaa78";

//    @Before
//    public void setUp() {
//        userClient = new UserClient();
//        User user = new User("44thismynewemail@yandx.ru", "dfsfdsf", "Email3333");
//        UserCredentials cred = UserCredentials.from(user);
//        userInfo = userClient.loginUser(cred, 200, "success");
//
//        // Убираем слово Bearer из аксес токена
//        userInfo.setAccessToken(userInfo.getAccessToken().substring("Bearer".length() + 1));
//
//        System.out.println(userInfo);
//    }

//    @Test
//    public void checkGetOrders(){
//        //String accessToken = userInfo.getAccessToken();
//        //String accessToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYyZGFlZjNlOTNlYmMyMDAxYjRhNGQ1NCIsImlhdCI6MTY1ODgzNzM0MiwiZXhwIjoxNjU4ODM4NTQyfQ.fs1vAqvLBUj6ImMfggbHPvEYsA7xZ5_S467J7-g25aU";
//        //System.out.println(accessToken);
//        //ResponseUserInfo newEmail = new ResponseUserInfo("44thismynewemail@yandx.ru", "44new_namehgfhgfgh");
//        String editUser = orderClient.getIngredientId("Флюоресцентная булка R2-D3");
//        //assertEquals("44thismynewemail@yandx.ru", editUser.getUser().getEmail());
//       System.out.println(editUser);
//    }

    @Test
    public void checkCreateOrderAuthUser(){
        //String accessToken = userInfo.getAccessToken();
        //System.out.println(accessToken);
        //ResponseUserInfo newEmail = new ResponseUserInfo("44thismynewemail@yandx.ru", "44new_namehgfhgfgh");
        Order editUser = orderClient.createOrder(INGREDIENT);
       // assertEquals("44thismynewemail@yandx.ru", editUser.getUser().getEmail());
        System.out.println(editUser);
    }
}