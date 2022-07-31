import api.OrderClient;
import api.UserClient;
import api.UserCredentials;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import pojo.*;

import java.util.List;

import static org.junit.Assert.*;

public class CreateOrderTest {

    private OrderClient orderClient;
    private ResponseUserLogin userInfo;

    @Before
    public void setUp() {
        UserClient userClient = new UserClient();
        orderClient = new OrderClient();
        User user = new User("Thisismy666@gmail.com", "dfsfdsf", "Email3333");
        UserCredentials cred = UserCredentials.from(user);
        userInfo = userClient.loginUser(cred, 200, "success");
        // Убираем слово Bearer из аксес токена
        userInfo.setAccessToken(userInfo.getAccessToken().substring("Bearer".length() + 1));
    }

    @Test
    @DisplayName("Check create order")
    public void checkCreateOrderAuthUser() throws InterruptedException {
        String accessToken = userInfo.getAccessToken();
        Ingredients ingredients = new Ingredients(List.of("61c0c5a71d1f82001bdaaa6d"));
        List<String> createOrder = orderClient.createOrder(accessToken, ingredients, 200);
        assertNotNull(createOrder);
        Thread.sleep(2000);
    }

    @Test
    @DisplayName("Check create order with ingredients")
    public void checkCreateOrderWithIngredients() throws InterruptedException {
        String accessToken = userInfo.getAccessToken();
        Ingredients ingredients = new Ingredients(List.of("61c0c5a71d1f82001bdaaa6d","61c0c5a71d1f82001bdaaa70"));
        Response createOrder = orderClient.createOrderIngredients(accessToken, ingredients, 200);
        assertTrue(createOrder.asString().contains("name"));
        Thread.sleep(2000);
    }

    @Test
    @DisplayName("Check create order with wrong ingredients")
    public void checkCreateOrderWithWrongHash() throws InterruptedException {
        String accessToken = userInfo.getAccessToken();
        ResponseUserInfo newEmail = new ResponseUserInfo("44thismynewemail@yandx.ru", "44new_namehgfhgfgh");
        Ingredients ingredients = new Ingredients(List.of("61c0c5a71gfdd1f82001bdaaa6dx"));
        Response createOrder = orderClient.createOrderIngredients(accessToken, ingredients, 500);
        assertEquals(500, createOrder.statusCode());
        Thread.sleep(2000);
    }

    @Test
    @DisplayName("Check create order without ingredients")
    public void checkCreateOrderWithoutIngredients() throws InterruptedException {
        String accessToken = userInfo.getAccessToken();
        ResponseUserInfo newEmail = new ResponseUserInfo("44thismynewemail@yandx.ru", "44new_namehgfhgfgh");
        Ingredients ingredients = new Ingredients(List.of(""));
        Response createOrder = orderClient.createOrderWithoutIngredients(accessToken, null, 400);
        assertEquals(400, createOrder.statusCode());
        Thread.sleep(2000);
    }

    @Test
    @DisplayName("Check create order without auth")
    public void checkCreateOrderWithoutAuthUser() throws InterruptedException {
        String accessToken = "";
        Ingredients ingredients = new Ingredients(List.of("61c0c5a71d1f82001bdaaa6d"));
        List<String> createOrderIngredients = orderClient.createOrder(accessToken, ingredients, 200);
        assertNull(createOrderIngredients);
        Thread.sleep(2000);
    }
}