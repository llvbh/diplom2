import api.UserClient;
import api.UserCredentials;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pojo.*;
import static org.junit.Assert.assertEquals;

public class GetUsersOrderTest {
    private UserClient userClient;
    private ResponseUserLogin userInfo;
    String accessToken;

    @Before
    public void setUp() {
        userClient = new UserClient();
        User user = new User("Thisismymailc@gmail.com", "John", "cPassword555");
        UserCredentials cred = UserCredentials.from(user);
        userInfo = userClient.loginUser(cred);
        accessToken = userInfo.getAccessToken();
    }
    @After
    public void after() throws InterruptedException {
        Thread.sleep(2000);
    }

    @Test
    public void checkGetAuthUsersOrders(){
        ResponseUserWithAuthOrders getOrdersAuthUser = userClient.getOrdersAuthUser(accessToken);
        String getfirstOrderId = getOrdersAuthUser.getOrders().get(0).get_id();
        assertEquals("Проверка есть ли в ответе первый заказ клиента", "62ed63c693ebc2001b4ad58f", getfirstOrderId);
    }

    @Test
    public void checkGetNotAuthUsersOrders() {
        ResponseUserWithError getOrdersAuthUser = userClient.getOrdersNotAuthUser("", 401);
        assertEquals("You should be authorised", getOrdersAuthUser.getMessage());
    }
}
