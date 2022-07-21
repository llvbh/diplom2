package api;

import pojo.ResponseUser;
import pojo.ResponseUserWithError;
import pojo.User;

import static io.restassured.RestAssured.given;


public class UserClient {
    protected final String ROOT = "https://stellarburgers.nomoreparties.site/api/";
    protected final String REGISTER = ROOT + "auth/register";
    protected final String LOGIN = ROOT + "auth/login";

     public ResponseUser createUser(User user, int statusCode, String responseMessage){
        return given()
            .header("Content-type", "application/json").baseUri("https://stellarburgers.nomoreparties.site/api")
            .body(user)
            .post("https://stellarburgers.nomoreparties.site/api/auth/register")
            .then()
            .extract()
            .body()
            .as(ResponseUser.class);
     }

    public ResponseUserWithError dontCreateUser(User user, int statusCode, String responseMessage){
        return given()
                .header("Content-type", "application/json").baseUri("https://stellarburgers.nomoreparties.site/api")
                .body(user)
                .post("https://stellarburgers.nomoreparties.site/api/auth/register")
                .then()
                .extract()
                .body()
                .as(ResponseUserWithError.class);
    }
}
