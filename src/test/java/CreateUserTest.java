import api.UserClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pojo.ResponseUser;
import pojo.ResponseUserWithError;
import pojo.User;

public class CreateUserTest {
    private UserClient userClient;
    private User user;
    @Before
    public void setUp() {
        userClient = new UserClient();
        user = new User("Thisismyemail5@gmail.com", "dfsfdsf", "Email3333");

    }
    @Test
    //@DisplayName("Check create user method")
    public void checkCreateNewUser() {
        ResponseUser createUser  = userClient.createUser(user, 200, "success");
        Assert.assertEquals(true, createUser.getSuccess());
    }
    @Test
    //@DisplayName("Check user add method with Dublicate args")
    public void checkCreateUserWithSameValues() {
        ResponseUserWithError createUser= userClient.doNotCreateUser(user, 403, "success");
        System.out.println(createUser);
        Assert.assertEquals("User already exists", createUser.getMessage());
    }
  @Test
  //@DisplayName("Check use add method With No Param")
  public void checkCreateUserWithNoEmail(){
      user = new User(null, "qwerty12", "Altynsarin");
      ResponseUserWithError createUser = userClient.doNotCreateUser(user, 401, "success");
      Assert.assertEquals("Email, password and name are required fields", createUser.getMessage());
  }

}
