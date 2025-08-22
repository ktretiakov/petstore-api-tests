package com.petstore.api;

import com.petstore.api.core.listeners.AllureListener;
import com.petstore.api.objects.datayamlloader.classes.user.UserData;
import com.petstore.api.requests.UserConfig;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.petstore.api.objects.datayamlloader.classes.user.UserDataTypes.USER_API_EXPECTED_DATA;
import static com.petstore.api.objects.datayamlloader.classes.user.UserDataTypes.USER_API_INVALID_CREATE_DATA;
import static com.petstore.api.objects.datayamlloader.classes.user.UserDataTypes.USER_API_UPDATED_DATA;
import static com.petstore.api.objects.datayamlloader.classes.user.UserDataTypes.USER_USERNAME;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

@Epic("Pet Store")
@Feature("User API negative tests")
@Listeners({AllureListener.class})
public class UserApiNegativeTests {

  private static UserData userExpectedCreateData, userExpectedEditData,
      userInvalidCreateData;
  private static Response userPostResponse;
  private static UserConfig userConfig;
  private static String username, invalidUserName;

  @BeforeClass
  public void setUpTestData() {
    userExpectedCreateData =
        UserData.buildUserData(USER_API_EXPECTED_DATA.getText());
    userExpectedEditData =
        UserData.buildUserData(USER_API_UPDATED_DATA.getText());
    userInvalidCreateData =
        UserData.buildUserData(USER_API_INVALID_CREATE_DATA.getText());

    username = userExpectedCreateData.getStringData(USER_USERNAME.getText());
    invalidUserName = userInvalidCreateData.getStringData(USER_USERNAME.getText());
  }

  @BeforeMethod
  public void createTestUser() {
    userConfig = new UserConfig();
    userConfig.createUserAccountForPetStore(userExpectedCreateData);
  }

  @AfterMethod
  public void deleteTestUser(Method method) {
    userConfig.deleteUserAccountFromPetStoreByUserName(username);
  }

  @Test(description = "POST: Create user with invalid data - Negative")
  @Description("Try creating user with invalid data and verify error")
  @Story("User Creation Negative")
  @TmsLink("NT-1")
  public void userCanNOTPostCreateNewUserWithInvalidData() {
    userPostResponse = userConfig.createUserAccountForPetStore(userInvalidCreateData);

    assertThat("Status code should not be 200",
        userPostResponse.statusCode(),
        not(200));

    assertThat("Error message should be present",
        userPostResponse.getBody().asString(),
        containsString("error"));
  }

  @Test(description = "GET: Non-existing user - Negative")
  @Description("Try to fetch user that does not exist")
  @Story("User Retrieval Negative")
  @TmsLink("NT-2")
  public void userCanNOTGetUserNonExistingUserName() {
    var userGetResponse = userConfig.getUserByUserName(invalidUserName);

    assertThat("Status code should be 404",
        userGetResponse.statusCode(),
        equalTo(404));

    assertThat("Message should be 'User not found'",
        userGetResponse.jsonPath().getString("message"),
        containsString("User not found"));
  }

  @Test(description = "DELETE: Non-existing user - Negative")
  @Description("Try to delete user that does not exist")
  @Story("User Deletion Negative")
  @TmsLink("NT-3")
  public void userCanNOTDeleteNotExistingUser() {
    var userDeleteResponse = userConfig.deleteUserAccountFromPetStoreByUserName(invalidUserName);

    assertThat("Status code should be 404",
        userDeleteResponse.statusCode(),
        equalTo(404));
  }

  @Test(description = "POST: Create user with existing username - Negative")
  @Description("Attempt to create a user with an existing username should fail")
  @Story("User Creation Negative")
  @TmsLink("NT-4")
  public void userCanNOTPostCreateNewUserWithAlreadyExistsUserName() {
    userPostResponse = userConfig.createUserAccountForPetStore(userExpectedCreateData);

    assertThat("Status code should indicate error (e.g. 400 or 409)",
        userPostResponse.statusCode(),
        equalTo(400));

    assertThat("Error message should mention existing username",
        userPostResponse.jsonPath().getString("message").toLowerCase(),
        equalTo("user already exists"));
  }

  @DataProvider(name = "invalidUserDataProvider")
  public Object[][] invalidUserDataProvider() {
    return new Object[][]{
        {"Empty email", "", "qwerty123", 400},
        {"Null password", "test@gmail.com", null, 400},
        {"Invalid email format", "wrong-email", "qwerty123", 400},
        {"Empty username", "test@gmail.com", "qwerty123", 400}
    };
  }

  @Test(
      dataProvider = "invalidUserDataProvider",
      description = "PUT: Update user with invalid data - Negative"
  )
  @Description("Attempt to update a user with invalid data should fail")
  @Story("User Update Negative")
  @TmsLink("NT-5")
  public void userCannotBeUpdatedWithInvalidData(
      String testCase,
      String email,
      String password,
      int expectedStatus
  ) {

    var updateResponse = userConfig.updateUserAccountInPetStoreWithInvalidData(
        username, userExpectedEditData, email, password);

    assertThat("[" + testCase + "] Status code should match expected",
        updateResponse.statusCode(),
        equalTo(expectedStatus));

    assertThat("[" + testCase + "] Error message should not be null",
        updateResponse.jsonPath().getString("message") != null,
        equalTo(true));
  }
}
