package base_tests.selenium.api;

import helpers.Validation;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import management.rest_api.dto.UserDTO;
import management.rest_api.pojo.UserData;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.URI;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public class ApiTest extends BaseApiTest {

    private static final String PATH = "/api/users";


    @Test(testName = "Verify if all avatars contain ids")
    public void test() {

        RequestSpecification request = given().when();

        Response response = request.get(URI.create(PATH));
        JsonPath jsonPath = response.body().jsonPath();

        List<UserData> data = jsonPath.getList("data", UserData.class);

        data.forEach(d -> {
            String avatar = d.getAvatar();
            String id = String.valueOf(d.getId());
            Assert.assertTrue(avatar.contains(id), String.format("Verify if avatar %s contains id %s", avatar, id));
        });

        System.out.println();
    }



    @Test(testName = "Verify User creating")
    public void test2() {
        RequestSpecification request = given();
        ResponseSpecification respSpec = new ResponseSpecBuilder().expectStatusCode(HttpStatus.SC_CREATED).build();

        UserDTO userForCreating = UserDTO.builder().name("TestName").job("TestJob").build();
        respSpec = request.basePath(PATH).body(userForCreating).then().spec(respSpec);

        Response response = request.post();

        JsonPath jsonPath = response.body().jsonPath();

        String id = jsonPath.getString("id");
        String name = jsonPath.getString("name");
        String job = jsonPath.getString("job");
        String createdAt = jsonPath.getString("createdAt");
        Assert.assertEquals(request.post().getStatusCode(), HttpStatus.SC_CREATED, String.format("Verify if status code is %s", HttpStatus.SC_CREATED));
    }
}
