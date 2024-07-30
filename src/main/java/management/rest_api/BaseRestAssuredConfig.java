package management.rest_api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import management.environment.ApiEnvironment;
import management.environment.DefaultEnvironment;
import org.apache.http.HttpStatus;

public class BaseRestAssuredConfig {

    protected static final String API_BASE_URL = ApiEnvironment.getURL();
    protected static final ContentType JSON = ContentType.JSON;

    public static void init() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setConfig(createTimeoutConfig())
                //todo: add headers if required
                .setBaseUri(API_BASE_URL)
                .setContentType(JSON)
                .build();

        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.SC_OK)
                .build();
    }

    private static RestAssuredConfig createTimeoutConfig(){
        return RestAssured.config()
                .httpClient(
                        HttpClientConfig.httpClientConfig()
                                .setParam("http.connection.timeout", 100000)
                                .setParam("http.socket.timeout", 100000));
    }
}
