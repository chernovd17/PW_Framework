package base_tests.selenium.api;

import com.beust.ah.A;
import io.restassured.response.Response;
import management.selenium.helpers.Verification;
import management.selenium.rest_api.BaseRestAssuredConfig;
import management.selenium.rest_api.pojo.Album;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class ApiTest2 extends BaseApiTest{

    private final String path = "/albums";
    @Test
    public void test(){
        Response response = BaseRestAssuredConfig.sendGetRequestAndGetResponse(path);

        List<Album> albums = response.body().jsonPath().getList("", Album.class);
        albums.size();

        List<Album> albs = albums.stream().sorted(Comparator.comparing(Album::getTitle)).toList();

        Assert.assertTrue(isListContainText(albums, "odio"), "Verify if List contains required value");

        List<String> albumsTitles = albums.stream().map(Album::getTitle).toList();

        List<String> sortedAlbums = albumsTitles.stream().sorted().collect(Collectors.toList());


        System.out.println();
    }

    private boolean isListContainText(List<Album> albums, String value){
        for(Album album : albums){
            if(album.getTitle().contains(value))
                return true;
        }
        return false;
    }


}
