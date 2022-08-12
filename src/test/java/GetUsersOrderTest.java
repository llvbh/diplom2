import api.OrderClient;
import api.UserClient;
import api.UserCredentials;
import org.junit.*;
import pojo.*;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GetUsersOrderTest {
    User user;
    private UserClient userClient;
    private OrderClient orderClient= new OrderClient();
    private static String accessToken;

    @Before
    public void setUp() {
        userClient = new UserClient();
        user = new User("NazymZhSeitbekova@apple.com", "NazymSeitbekova", "55555");
        UserCredentials cred = UserCredentials.from(user);
        ResponseUserLogin userInfo = userClient.loginUser(cred);
        accessToken = userInfo.getAccessToken();
    }

    @After
    public void after()  {
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
        ResponseUserWithError getOrdersAuthUser = userClient.getOrdersNotAuthUser("", 401);
        assertEquals("You should be authorised", getOrdersAuthUser.getMessage());
    }
}
