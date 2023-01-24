# coopApplicationTests

##CoopApplication Api Tests

I have added my access token generated and the user details, incase the access token expires please generate new token and follow the below steps to use the generated token.

Follow these steps to confgure tests:
open the BaseSettings.java file in /src/main/java/baseSettings location.
add your acces token in the BaseSettings.java file obtained from the http://coop.apps.symfonycasts.com/api

Add your user details in src/test/java/testScenarios/userInfo/UserInfo.java where the user deails infor are validated. 

Testing

Run from terminal

To run all test use: mvn clean kotest

To run the test from xml file right click on the coopApplicationTests/testRunners/coopApplicationTestsInParallel.xml file and select option run "[filename]"
  
<img width="1440" alt="Screenshot 2023-01-24 at 01 40 41" src="https://user-images.githubusercontent.com/87369149/214186835-8361a016-bb3c-4925-8ff8-040ae0d36aa4.png">
