package utils;

import baseSettings.BaseSettings;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import utils.enums.MediaType;

public final class PostUtils extends BaseSettings {

    public static Response unlockBarn(int userId, Boolean access) {
        String accessToken;
        if (access)
            accessToken = getAccessToken();
        else
            accessToken = getInvalidAccessToken();
        return RestAssured
                .given()
                .header("Authorization", accessToken)
                .contentType(MediaType.JSON)
                .post(getbasePath()+ "/" + userId + "/barn-unlock")
                .then()
                //.statusCode(HttpStatus.SC_OK)
                .and()
                .time(Matchers.lessThan(getResponseTimeLimit()))
                .extract().response();
    }
}
