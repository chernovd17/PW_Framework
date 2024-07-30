package management.rest_api;

import io.restassured.http.Cookies;

public class ApiSession {

    private static final String COOKIE_AWSALB = "AWSALB";
    private static final int TIMEOUT_IN_MILLIS = 300000;//5minutes

    private static ApiSession instance;
    private Cookies cookies;

    public static ApiSession getInstance() {
        if (instance == null) {
            instance = new ApiSession();
            // Initialize your session here
        }
        return instance;
    }

}
