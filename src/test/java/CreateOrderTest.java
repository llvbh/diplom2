import api.OrderClient;
import api.UserClient;
import api.UserCredentials;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pojo.*;
import java.util.List;
import static org.junit.Assert.*;

public class CreateOrderTest {
    private OrderClient orderClient= new OrderClient();
    private ResponseUserLogin userInfo;
    private static String accessToken;

    @Before
    public void setUp() {
        UserClient userClient = new UserClient();
        User user = new User("Nazym3@apple.com", "Nazym3", "55555");
        UserCredentials cred = UserCredentials.from(user);
        userInfo = userClient.loginUser(cred);
        accessToken = userInfo.getAccessToken();

    }
    @After
    public void after() throws InterruptedException {
        Thread.sleep(2000);
    }

    @Test
    @DisplayName("Check create order")
    public void checkCreateOrderAuthUser(){
        Ingredients ingredients = new Ingredients(List.of("61c0c5a71d1f82001bdaaa6d"));
        List<String> createOrderAuthUser = orderClient.createOrder(accessToken, ingredients);
        assertNotNull(createOrderAuthUser);
    }

    @Test
    @DisplayName("Check create order without auth")
    public void checkCreateOrderWithoutAuthUser() {
        Ingredients ingredients = new Ingredients(List.of("61c0c5a71d1f82001bdaaa6d"));
        List<String> createOrderIngredients = orderClient.createOrder("", ingredients);
        assertNull(createOrderIngredients);
    }

    @Test
    @DisplayName("Check create order with ingredients")
    public void checkCreateOrderWithIngredients() {
        Ingredients ingredients = new Ingredients(List.of("61c0c5a71d1f82001bdaaa6d","61c0c5a71d1f82001bdaaa70"));
        Response createOrderWithIngredients = orderClient.createOrderIngredients(accessToken, ingredients);
        assertEquals(200, createOrderWithIngredients.statusCode());
    }

    @Test
    @DisplayName("Check create order with wrong ingredients")
    public void checkCreateOrderWithWrongHash(){
        Ingredients ingredients = new Ingredients(List.of("61c0c5a71gfdd1f82001bdaaa6dx"));
        Response createOrderWithWrongHash = orderClient.createOrderIngredients(accessToken, ingredients);
        assertEquals(500, createOrderWithWrongHash.statusCode());
    }

    @Test
    @DisplayName("Check create order without ingredients")
    public void checkCreateOrderWithoutIngredients() {
        Ingredients ingredients = new Ingredients(null);
        Response createOrderWithoutIngredients = orderClient.createOrderWithoutIngredients(accessToken);
        assertEquals(400, createOrderWithoutIngredients.statusCode());
    }
}