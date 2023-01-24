package baseSettings;

import io.restassured.RestAssured;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.BeforeSuite;

import java.util.Properties;

public class BaseSettings {

    private static Properties properties;

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(ITestContext context) {
        setTestProperties();
        setEnvironmentForTest();
    }

    protected void setTestProperties() {
        properties = new Properties();
        properties.put("baseURI", "http://coop.apps.symfonycasts.com/");
        properties.put("basePath", "/api");
        properties.put("retrieveUser", "/api/me");
        properties.put("unlockBarn", "/api/USER_ID/barn-unlock");
        properties.put("toiletSeatDown", "/api/USER_ID/toiletseat-down");
        properties.put("feedChicken", "/api/USER_ID/chickens-feed");
        properties.put("collectEggs", "/api/USER_ID/eggs-collect");
        properties.put("countEggs", "/api/USER_ID/eggs-count");
        properties.put("responseTimeLimit", "5000");
        properties.put("accessToken", "9d7c414e2bec1df1a0dd5b15fea3287d6a1f50c3");
        properties.put("invalidAccess", "1234567890qwertyuioad455vt55yh660kj4n4fd");
        properties.put("client_id", "Coop Application");
        properties.put("client_secret", "3b7c6e5e10d740ab017fcbdad01b73bf");
        properties.put("response_type", "");
        properties.put("redirect_uri", "");
        properties.put("scope", "barn-unlock toiletseat-down chickens-feed eggs-collect eggs-count profile");
        System.getenv().forEach((key, value) -> properties.setProperty(key, value));
    }

    public static void setEnvironmentForTest() {
        RestAssured.reset();
        RestAssured.baseURI = properties.getProperty("baseURI");
    }

    public static String getAccessToken() {
        return "Bearer " + properties.getProperty("accessToken");
    }
    public static String getInvalidAccessToken() {
        return "Bearer " + properties.getProperty("invalidAccess");
    }

    public static String getClientId()
    {
        return properties.getProperty("client_id");
    }
    public static String getClientSecret()
    {
        return properties.getProperty("client_secret");
    }

    public static String getUsersPath() {
        return properties.getProperty("retrieveUser");
    }
    public static String getbasePath() { return properties.getProperty("basePath");}

    public static String getUnlockBarnPath() {
        return properties.getProperty("unlockBarn");
    }

    public static String getToiletSeatDownPath() {
        return properties.getProperty("toiletSeatDown");
    }

    public static Long getResponseTimeLimit() {
        return Long.parseLong(properties.getProperty("responseTimeLimit"));
    }

}
