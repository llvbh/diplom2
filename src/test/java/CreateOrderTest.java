import api.OrderClient;
import api.UserClient;
import api.UserCredentials;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import pojo.*;

import java.util.List;

import static org.junit.Assert.*;

public class CreateOrderTest {
    private UserClient userClient;
    private OrderClient orderClient;
    ResponseUserLogin userInfo;

    private final List<String> ingredients = List.of("61c0c5a71d1f82001bdaaa6d");
    private final List<String> ingredientsWithWrongHash = List.of("6xc1c0c5a71d1f82001bdaaa6d");

    @Before
    public void setUp() {
        userClient = new UserClient();
        orderClient = new OrderClient();
        User user = new User("44thismynewemail@yandx.ru", "dfsfdsf", "Email3333");
        UserCredentials cred = UserCredentials.from(user);
        userInfo = userClient.loginUser(cred, 200, "success");

        // Убираем слово Bearer из аксес токена
        userInfo.setAccessToken(userInfo.getAccessToken().substring("Bearer".length() + 1));

       //System.out.println(userInfo.getAccessToken());
    }

    @Test
    public void checkCreateOrderAuthUser(){
        String accessToken = userInfo.getAccessToken();
        System.out.println(accessToken);

            ResponseUserInfo newEmail = new ResponseUserInfo("44thismynewemail@yandx.ru", "44new_namehgfhgfgh");
            Ingredients ingredients = new Ingredients(List.of("61c0c5a71d1f82001bdaaa6d"));
        List<String> createOrder = orderClient.createOrder(accessToken, ingredients, 200);
        System.out.println("fgfgd");
        System.out.println(createOrder);
        assertNotNull(createOrder);
    }

    @Test
    public void checkCreateOrderWithWrongHash(){
        String accessToken = userInfo.getAccessToken();
        //System.out.println(accessToken);

        ResponseUserInfo newEmail = new ResponseUserInfo("44thismynewemail@yandx.ru", "44new_namehgfhgfgh");
        Ingredients ingredients = new Ingredients(List.of("61c0c5a71gfdd1f82001bdaaa6dx"));
        Response createOrder = orderClient.createOrder2(accessToken, ingredients, 500);
        System.out.println("fgfgd");
        System.out.println(createOrder.statusCode());
        assertEquals(500, createOrder.statusCode());

    }

    @Test
    public void checkCreateOrderWithoutIngredients(){
        String accessToken = userInfo.getAccessToken();
        ResponseUserInfo newEmail = new ResponseUserInfo("44thismynewemail@yandx.ru", "44new_namehgfhgfgh");
        Ingredients ingredients = new Ingredients(List.of(""));
        Response createOrder = orderClient.createOrder3(accessToken, null, 400);
        assertEquals(400, createOrder.statusCode());

    }
    @Test
    public void checkCreateOrderWithoutAuthUser(){
        String accessToken = "";
        Ingredients ingredients = new Ingredients(List.of("61c0c5a71d1f82001bdaaa6d"));
        List<String> createOrder2 = orderClient.createOrder(accessToken, ingredients, 200);
        //в реальности данный тест не падает при создании без авторизации, поэтому проверяю на наличие id
        System.out.println("createOrder2");
        System.out.println(createOrder2);
        assertNull(createOrder2);
        System.out.println("yddd");

    }


}