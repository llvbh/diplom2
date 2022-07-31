import api.UserClient;
import api.UserCredentials;
import org.junit.Before;
import org.junit.Test;
import pojo.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GetUsersOrderTest {

    private UserClient userClient;
    private ResponseUserLogin userInfo;

    @Before
    public void setUp() {
        userClient = new UserClient();
        User user = new User("Thisismy666@gmail.com", "dfsfdsf", "Email3333");
        UserCredentials cred = UserCredentials.from(user);
        userInfo = userClient.loginUser(cred, 200, "success");
        // Убираем слово Bearer из акссесс токена
        userInfo.setAccessToken(userInfo.getAccessToken().substring("Bearer".length() + 1));
    }

    @Test
    public void checkGetAuthUsersOrders() throws InterruptedException {
        String accessToken = userInfo.getAccessToken();
        ResponseUserWithAuthOrders getOrdersAuthUser = userClient.getOrdersAuthUser(accessToken);
        assertTrue(getOrdersAuthUser.isSuccess());
        Thread.sleep(2000);
    }

    @Test
    public void checkGetNotAuthUsersOrders() throws InterruptedException {
        ResponseUserWithError getOrdersAuthUser = userClient.getOrdersNotAuthUser("");
        assertEquals("You should be authorised", getOrdersAuthUser.getMessage());
        Thread.sleep(2000);
    }
}
