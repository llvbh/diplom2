package api;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class RestAssuredClient {

    protected static final String URL = "https://stellarburgers.nomoreparties.site/api";
    protected static final String REGISTER = URL + "/auth/register";
    protected static final String LOGIN = URL + "/auth/login";
    protected static final String USER = URL + "/auth/user";
    protected static final String ORDERS = URL +"/orders";

    protected static final RequestSpecification reqSpec =
         given()
        .header("Content-type", "application/json")
        .baseUri(URL);
}
