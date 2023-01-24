package testScenarios.putToiletSeatDown;

import baseSettings.BaseSettings;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ActionUtils;
import utils.UserUtils;

public class toiletSeatDown extends BaseSettings  {

    /**
     * Test to validate happy path of put toilet seat down.
     */
    @Test
    public void testPutToiletSeatDown(){
        Response putToiletSeatDownResponse = ActionUtils.putToiletSeatDown(UserUtils.getUserId(), true);
        Assert.assertEquals(putToiletSeatDownResponse.getStatusCode(), 200);
        Assert.assertEquals(putToiletSeatDownResponse.jsonPath().get("action"),"toiletseat-down");
        Assert.assertEquals(putToiletSeatDownResponse.jsonPath().get("success"), true);
        String message = putToiletSeatDownResponse.jsonPath().get("message");
        Assert.assertTrue(message.contains( "You just put the toilet seat down."));
        String retryPutToiletSeatDown= ActionUtils.putToiletSeatDown(UserUtils.getUserId(), true).jsonPath().get("message");
        // Again try to put toilet seat down immediately.
        Assert.assertTrue(retryPutToiletSeatDown.contains("the toilet seat is already down"));
    }

    /**
     *  Test to validate unsuccessful attempt to put toilet seat down using invalid userid.
     */
    @Test
    public void testPutToiletSeatDownFail(){
        Response putToiletSeatDownResponse = ActionUtils.putToiletSeatDown(UserUtils.getUserId() + 1, true);
        Assert.assertEquals(putToiletSeatDownResponse.getStatusCode(), 401);
        Assert.assertEquals(putToiletSeatDownResponse.jsonPath().get("error"),"access_denied");
        Assert.assertEquals(putToiletSeatDownResponse.jsonPath().get("error_message"),"You do not have access to take this action on behalf of this user");
    }

    /**
     * Test to validate invlaid access token.
     */
    @Test
    public void putToiletSeatDownInvalidAccessTest(){
        Response putToiletSeatDownResponse = ActionUtils.putToiletSeatDown(UserUtils.getUserId(), false );
        Assert.assertEquals(putToiletSeatDownResponse.getStatusCode(), 401);
        Assert.assertEquals(putToiletSeatDownResponse.jsonPath().get("error"),"invalid_token");
        Assert.assertEquals(putToiletSeatDownResponse.jsonPath().get("error_description"),"The access token provided is invalid");
    }
}
