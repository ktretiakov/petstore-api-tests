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
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.petstore.api.constants.JsonSchemasConstData.SCHEMA_POST_USER;
import static com.petstore.api.objects.datayamlloader.classes.user.UserDataTypes.USER_API_EXPECTED_DATA;
import static com.petstore.api.objects.datayamlloader.classes.user.UserDataTypes.USER_API_UPDATED_DATA;
import static com.petstore.api.objects.datayamlloader.classes.user.UserDataTypes.USER_FIRST_NAME;
import static com.petstore.api.objects.datayamlloader.classes.user.UserDataTypes.USER_USERNAME;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


@Epic("Pet Store")
@Feature("User API positive tests")
@Listeners({AllureListener.class})
public class UserApiPositiveTest {

  private static UserData userExpectedCreateData, userExpectedEditData;
  private static Response userPostResponse, userGetResponse;
  private static UserConfig userConfig;
  private static String username;

  @BeforeClass
  public void setUpPetStoreUserTestData() {
    userExpectedCreateData =
        UserData.buildUserData(USER_API_EXPECTED_DATA.getText());
    userExpectedEditData =
        UserData.buildUserData(USER_API_UPDATED_DATA.getText());
    username = userExpectedCreateData.getStringData(USER_USERNAME.getText());
  }

  @BeforeMethod
  public void createTestUser() {
    userConfig = new UserConfig();
    userPostResponse = userConfig.createUserAccountForPetStore(userExpectedCreateData);
  }

  @AfterMethod
  public void deleteTestUser(Method method) {
    if (!method.getName().equals("userCanSuccessfullyDeleteCreatedUserInStore")) {
      userConfig.deleteUserAccountFromPetStoreByUserName(username);
    }
  }

  @Test(description = "POST: Add new pet - Positive")
  @Description("Add new pet with valid data and verify 200")
  @Story("User Creation")
  @TmsLink("PT-1")
  public void userCanSuccessfullyPostCreateNewUserInStore() {
    assertThat("Status code should be 200",
        userPostResponse.statusCode(),
        equalTo(200));

    assertThat("First name should match expected",
        userPostResponse.jsonPath().getString(USER_FIRST_NAME.getText()),
        equalTo(userExpectedCreateData.getStringData(USER_FIRST_NAME.getText())));

    assertThat("Invalid contract for the POST response - /user",
        userPostResponse.getBody().asString(),
        matchesJsonSchemaInClasspath(SCHEMA_POST_USER));
  }

  @Test(description = "DELETE: Delete user - Positive")
  @Description("Delete user by username and verify 200")
  @Story("User Deletion")
  @TmsLink("PT-2")
  public void userCanSuccessfullyDeleteCreatedUerByUserName() {

    var deleteResponse = userConfig.deleteUserAccountFromPetStoreByUserName(username);

    assertThat("Status code should be 200",
        deleteResponse.statusCode(),
        equalTo(200));

    assertThat("Response 'message' should contain deleted username",
        deleteResponse.jsonPath().getString("message"),
        equalTo(username));

    userGetResponse = userConfig.getUserByUserName(username);
    assertThat("User should not exist after deletion",
        userGetResponse.statusCode(),
        equalTo(404));
  }

  @Test(description = "GET: Get user by username - Positive")
  @Description("Fetch user by username and verify 200 and response body")
  @Story("User Retrieval")
  @TmsLink("PT-3")
  public void userCanSuccessfullyGetFetchUserByUsername() {

    userGetResponse = userConfig.getUserByUserName(username);

    assertThat("Status code should be 200",
        userGetResponse.statusCode(),
        equalTo(200));

    assertThat("Username in response should match expected",
        userGetResponse.jsonPath().getString("username"),
        equalTo(username));

    assertThat("First name should match expected",
        userGetResponse.jsonPath().getString(USER_FIRST_NAME.getText()),
        equalTo(userExpectedCreateData.getStringData(USER_FIRST_NAME.getText())));
  }

  @Test(description = "PUT: Update user - Positive")
  @Description("Update user by username and verify changes")
  @Story("User Update")
  @TmsLink("PT-4")
  public void userCanBeUpdatedByUserName() {

    var updateResponse = userConfig.updateUserAccountInPetStoreByUserName(
        username,
        userExpectedEditData
    );

    assertThat("Status code should be 200",
        updateResponse.statusCode(),
        equalTo(200));

    userGetResponse = userConfig.getUserByUserName(username);

    assertThat("Status code of GET should be 200",
        userGetResponse.statusCode(),
        equalTo(200));

    assertThat("First name should be updated",
        userGetResponse.jsonPath().getString(USER_FIRST_NAME.getText()),
        equalTo(userExpectedCreateData.getStringData(USER_FIRST_NAME.getText())));
  }
}
