import api.OrderClient;
import api.UserClient;
import org.junit.*;
import pojo.*;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GetUsersOrderTest {

    private static UserClient userClient;
    private final OrderClient orderClient = new OrderClient();
    private static String accessToken;

    @BeforeClass
    public static void setUp() {
        userClient = new UserClient();
        User user = new User("NazymSeitbekova50@google.com1", "NazymSeitbekova", "55555");
        ResponseUser createUser = userClient.createUser(user, 200);
        accessToken = createUser.getAccessToken();
    }

    @AfterClass
    public static void deleteUser() {
        if (accessToken != null ) {
            UserClient.deleteUser(accessToken);
        }
    }

    @Test
    public void checkGetAuthUsersOrders() {
        int expectedOrdersCount = 3;
        for (int i = 0; i < expectedOrdersCount; i++){
            Ingredients ingredients = new Ingredients(List.of("61c0c5a71d1f82001bdaaa6d"));
            orderClient.createOrder(accessToken, ingredients);
        }
        ResponseUserWithAuthOrders getOrdersAuthUser = userClient.getOrdersAuthUser(accessToken);
        assertEquals(expectedOrdersCount, getOrdersAuthUser.getOrders().size());
   }

    @Test
    public void checkGetNotAuthUsersOrders() {
        ResponseUserWithError getOrdersAuthUser = userClient.getOrdersNotAuthUser("");
        assertEquals("You should be authorised", getOrdersAuthUser.getMessage());
    }
}
