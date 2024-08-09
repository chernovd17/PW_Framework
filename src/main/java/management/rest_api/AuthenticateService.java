package management.rest_api;


import io.restassured.http.Cookies;
import io.restassured.response.Response;
import management.rest_api.pojo.UserLoginPojo;
import management.selenium.sessions_management.SeleniumSessions;

import static io.restassured.RestAssured.given;

public class AuthenticateService extends BaseService {

    public static Response authenticateUser(UserLoginPojo userLoginPojo, String uri) {
        SeleniumSessions.getCurrentSession().getLoggerSession().ACTION("Authenticating as " + userLoginPojo + " user");
        Response response = given()
                .basePath(uri)
                .when()
                .body(userLoginPojo)
                .post()
                .then()
                .extract()
                .response();

        return response;
    }

    public static Cookies getAuthenticateCookies(UserLoginPojo userLoginPojo, String uri) {
        return authenticateUser(userLoginPojo, uri)
                .getDetailedCookies();
    }
}
