//import api.UserClient;
//import org.junit.Before;
//import org.junit.Test;
//import user.User;
//
//public class LoginUserTest {
//
//    private UserClient userClient;
//    private int courierId;
//
//    @Before
//    public void setUp() {
//        userClient = new UserClient();
//    }
//
//    @Test
//   // @DisplayName("Check courier Login method")
//    public void checkLoginCourier(){
//        try{
//            User user = new User("Ibyrai_science1", "qwerty12", "Altynsarin");
//            CourierCredentials cred = CourierCredentials.from(courier);
//            courierId = courierClient.loginCourier(cred, 200, "id");
//            assertNotEquals(0, courierId);
//        }
//        finally{
//            if(courierId > 0) {courierClient.deleteCourier(courierId, 200);}
//        }
//    }
//
//
//}
