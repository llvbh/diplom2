import api.UserClient;
import api.UserCredentials;
import org.junit.Before;
import org.junit.Test;
import pojo.*;

import static org.junit.Assert.assertEquals;

public class GetUsersOrderTest {

    private UserClient userClient;
    ResponseUserLogin userInfo;

    @Before
    public void setUp() {
        userClient = new UserClient();
        User user = new User("44thismynewemail@yandx.ru", "dfsfdsf", "Email3333");
        UserCredentials cred = UserCredentials.from(user);
        userInfo = userClient.loginUser(cred, 200, "success");

        // Убираем слово Bearer из акссесс токена
        userInfo.setAccessToken(userInfo.getAccessToken().substring("Bearer".length() + 1));

        System.out.println(userInfo);
    }

    @Test
    public void checkGetAuthUsersOrders(){
        String accessToken = userInfo.getAccessToken();
        ResponseUserWithAuthOrders getOrdersAuthUser = userClient.getOrdersAuthUser(accessToken);
       System.out.println(accessToken);
        System.out.println(getOrdersAuthUser);
//        ResponseUserInfo newEmail = new ResponseUserInfo("44thismynewemail@yandx.ru", "44new_namehgfhgfgh");
//        ResponseEditUserWithAuth editUser = userClient.getOrdersAuthUser(newEmail, accessToken);
        assertEquals(true, getOrdersAuthUser.isSuccess());

    }

    @Test
    public void checkGetNotAuthUsersOrders(){
        ResponseUserWithError getOrdersAuthUser = userClient.getOrdersNotAuthUser("");
        //System.out.println(accessToken);
 //       System.out.println(getOrdersAuthUser);
//        ResponseUserInfo newEmail = new ResponseUserInfo("44thismynewemail@yandx.ru", "44new_namehgfhgfgh");
//        ResponseEditUserWithAuth editUser = userClient.getOrdersAuthUser(newEmail, accessToken);
        assertEquals("You should be authorised", getOrdersAuthUser.getMessage());

    }
}
