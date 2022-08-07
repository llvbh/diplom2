import api.OrderClient;
import api.UserClient;
import api.UserCredentials;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pojo.*;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GetUsersOrderTest {
    private UserClient userClient;
    private OrderClient orderClient= new OrderClient();
    private String accessToken;

    @Before
    public void setUp() {
        userClient = new UserClient();
        User user = new User("NazymSeitbekova1@apple.com", "NazymSeitbekova", "55555");
        UserCredentials cred = UserCredentials.from(user);
        ResponseUserLogin userInfo = userClient.loginUser(cred);
        accessToken = userInfo.getAccessToken();
    }

    @After
    public void after() throws InterruptedException {
        Thread.sleep(2000);
    }

    @Test
    public void checkGetAuthUsersOrders() {
        int expectedOrdersCount = 3;
        for (int i = 1; i < expectedOrdersCount + 1; i++){
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
