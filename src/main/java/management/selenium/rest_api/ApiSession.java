package management.selenium.rest_api;

import io.restassured.http.Cookies;
import management.selenium.rest_api.dto.UserLoginDTO;

public class ApiSession {

    private static final String COOKIE_AWSALB = "AWSALB";
    private static final int TIMEOUT_IN_MILLIS = 300000;//5minutes

    private static ApiSession instance;
    private Cookies cookies;

    public static ApiSession getInstance(UserLoginDTO userLoginDTO, String uri) {
        if (instance == null) {
            instance = new ApiSession(userLoginDTO, uri);
        }
        return instance;
    }

    private ApiSession(UserLoginDTO userLoginDTO, String uri) {
        cookies = AuthenticateService.getAuthenticateCookies(userLoginDTO, uri);
    }

    public Cookies getCookies() {
        return cookies;
    }

    public void setCookies(Cookies cookies) {
        this.cookies = cookies;
    }
}
