package testScenarios.unlockBarn;

import baseSettings.BaseSettings;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.PostUtils;
import utils.UserUtils;

public class UnlockBarn extends BaseSettings {

    /**
     * Test to validate happy path of unlock barn.
     */
    @Test
    public void unlockBarnTest(){
        Response unlockBarnResponse = PostUtils.unlockBarn(UserUtils.getUserId(), true);
        Assert.assertEquals(unlockBarnResponse.getStatusCode(), 200);
        Assert.assertEquals(unlockBarnResponse.jsonPath().get("action"),"barn-unlock");
        Assert.assertEquals(unlockBarnResponse.jsonPath().get("success"), true);
        Assert.assertEquals(unlockBarnResponse.jsonPath().get("message"), "You just unlocked your barn! Watch out for strangers!");
    }

    /**
     * Test to valdiate unsuccessful attempt to unlock barn using invalid userid.
     */
    @Test
    public void unlockBarnFailTest(){
        Response unlockBarnResponse = PostUtils.unlockBarn(UserUtils.getUserId() + 1, true );
        Assert.assertEquals(unlockBarnResponse.getStatusCode(), 401);
        Assert.assertEquals(unlockBarnResponse.jsonPath().get("error"),"access_denied");
        Assert.assertEquals(unlockBarnResponse.jsonPath().get("error_message"),"You do not have access to take this action on behalf of this user");
    }

    /**
     * Test to validate invlaid access token.
     */
    @Test
    public void unlockBardInvalidAccessTest(){
        Response unlockBarnResponse = PostUtils.unlockBarn(UserUtils.getUserId(), false );
        Assert.assertEquals(unlockBarnResponse.getStatusCode(), 401);
        Assert.assertEquals(unlockBarnResponse.jsonPath().get("error"),"invalid_token");
        Assert.assertEquals(unlockBarnResponse.jsonPath().get("error_description"),"The access token provided is invalid");
    }
}
