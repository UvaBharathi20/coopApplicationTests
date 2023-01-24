package utils;

import baseSettings.BaseSettings;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import utils.enums.MediaType;

public final class ActionUtils extends BaseSettings {

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

    public static Response putToiletSeatDown(int userId, Boolean access){
        String accessToken;
        if (access)
            accessToken = getAccessToken();
        else
            accessToken = getInvalidAccessToken();
        return RestAssured
                .given()
                .header("Authorization", accessToken)
                .contentType(MediaType.JSON)
                .post(getbasePath()+ "/" + userId + "/toiletseat-down")
                .then()
                //.statusCode(HttpStatus.SC_OK)
                .and()
                .time(Matchers.lessThan(getResponseTimeLimit()))
                .extract().response();
    }
}
