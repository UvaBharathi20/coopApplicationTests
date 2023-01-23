package utils;

import baseSettings.BaseSettings;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import utils.enums.MediaType;

import java.util.concurrent.atomic.AtomicInteger;

public final class UserUtils extends BaseSettings {

    private static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static Response retrieveUser() {
        return RestAssured
                .given()
                .header("Authorization", getAccessToken())
                .contentType(MediaType.JSON)
                .get(getUsersPath())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .time(Matchers.lessThan(getResponseTimeLimit()))
                .extract().response();
    }

    public static int getUserId() {
        return RestAssured
                .given()
                .header("Authorization", getAccessToken())
                .contentType(MediaType.JSON)
                .get(getUsersPath())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .time(Matchers.lessThan(getResponseTimeLimit()))
                .extract().jsonPath().getInt("id");
    }
}
