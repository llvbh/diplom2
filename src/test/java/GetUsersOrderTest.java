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
        userInfo = userClient.loginUser(cred); System.out.println("22222");
    }

    @Test
    public void checkGetAuthUsersOrders() throws InterruptedException { System.out.println("111");
        String accessToken = userInfo.getAccessToken();
        ResponseUserWithAuthOrders getOrdersAuthUser = userClient.getOrdersAuthUser(accessToken);
        System.out.println("sdgfdfd");
        System.out.println(getOrdersAuthUser.isSuccess());
        System.out.println(getOrdersAuthUser.getOrders());
        assertTrue(getOrdersAuthUser.isSuccess());
        Thread.sleep(2000);
    }

    @Test
    public void checkGetNotAuthUsersOrders() throws InterruptedException {
        ResponseUserWithError getOrdersAuthUser = userClient.getOrdersNotAuthUser("", 401);
        assertEquals("You should be authorised", getOrdersAuthUser.getMessage());
        Thread.sleep(2000);
    }
}
