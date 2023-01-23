package testScenarios.userInfo;

import baseSettings.BaseSettings;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.UserUtils;


public class UserInfo extends BaseSettings {

    @Test
    public void userInfoCheck(){
        //check the status code
        Assert.assertEquals(UserUtils.retrieveUser().getStatusCode(), 200);
        JsonPath jsonPathEvaluator = UserUtils.retrieveUser().jsonPath();
        String id = jsonPathEvaluator.get("id");
        String email = jsonPathEvaluator.get("email");
        String firstName = jsonPathEvaluator.get("firstName");
        String lastName = jsonPathEvaluator.get("lastName");
        Assert.assertEquals(id,"4267");
        Assert.assertEquals(email,"uvabharathi20@gmail.com");
        Assert.assertEquals(firstName,"Uva Bharathi");
        Assert.assertEquals(lastName,"Rajendran");

    }
}
