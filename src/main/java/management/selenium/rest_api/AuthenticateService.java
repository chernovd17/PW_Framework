package management.selenium.rest_api;


import io.restassured.http.Cookies;
import io.restassured.response.Response;
import management.selenium.rest_api.dto.UserLoginDTO;
import management.selenium.sessions_management.SeleniumSessions;

import static io.restassured.RestAssured.given;

public class AuthenticateService extends BaseService {

    public static Response authenticateUser(UserLoginDTO userLoginDTO, String uri) {
        SeleniumSessions.getCurrentSession().getLoggerSession().ACTION("Authenticating as " + userLoginDTO + " user");
        Response response = given()
                .basePath(uri)
                .when()
                .body(userLoginDTO)
                .post()
                .then()
                .extract()
                .response();

        return response;
    }

    public static Cookies getAuthenticateCookies(UserLoginDTO userLoginDTO, String uri) {
        return authenticateUser(userLoginDTO, uri)
                .getDetailedCookies();
    }
}
