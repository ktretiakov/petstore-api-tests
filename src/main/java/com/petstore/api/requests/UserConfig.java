package com.petstore.api.requests;

import com.petstore.api.core.ConfigApi;
import com.petstore.api.objects.datayamlloader.classes.user.UserData;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.petstore.api.objects.ApiEndpointsURL.USER;
import static com.petstore.api.objects.datayamlloader.classes.user.UserDataTypes.USER_EMAIL;
import static com.petstore.api.objects.datayamlloader.classes.user.UserDataTypes.USER_FIRST_NAME;
import static com.petstore.api.objects.datayamlloader.classes.user.UserDataTypes.USER_ID;
import static com.petstore.api.objects.datayamlloader.classes.user.UserDataTypes.USER_LAST_NAME;
import static com.petstore.api.objects.datayamlloader.classes.user.UserDataTypes.USER_PASSWORD;
import static com.petstore.api.objects.datayamlloader.classes.user.UserDataTypes.USER_PHONE;
import static com.petstore.api.objects.datayamlloader.classes.user.UserDataTypes.USER_STATUS;
import static com.petstore.api.objects.datayamlloader.classes.user.UserDataTypes.USER_USERNAME;
import static com.petstore.api.objects.models.user.IncomingUserModel.getUserCreateModel;
import static com.petstore.api.objects.models.user.IncomingUserModel.getUserEditModel;

public class UserConfig extends ConfigApi {

  public UserConfig() {
    super();
  }

  @Step("Create User account for Pet Store with following parameters - {0}")
  public Response createUserAccountForPetStore(UserData userData) {
    return postBodyToDesiredEndpointWithResponse(
        getUserCreateModel(
            userData.getIntegerData(USER_ID.getText()),
            userData.getStringData(USER_USERNAME.getText()),
            userData.getStringData(USER_FIRST_NAME.getText()),
            userData.getStringData(USER_LAST_NAME.getText()),
            userData.getStringData(USER_EMAIL.getText()),
            userData.getStringData(USER_PASSWORD.getText()),
            userData.getStringData(USER_PHONE.getText()),
            userData.getIntegerData(USER_STATUS.getText())),
        "/" + USER.getApiEndpointsUrl());
  }

  @Step("Delete User account from Pet Store by username - {0}")
  public Response deleteUserAccountFromPetStoreByUserName(String userName) {
    return deleteItemByIdFromDesiredEndpointWithResponse(
        "/" + USER.getApiEndpointsUrl() + "/" + userName
    );
  }

  @Step("Get User account from Pet Store by username - {0}")
  public Response getUserByUserName(String userName) {
    return getItemByItsIdFromDesiredEndpoint(
        "/" + USER.getApiEndpointsUrl() + "/" + userName
    );
  }

  @Step("Update User account in Pet Store by username - {0}")
  public Response updateUserAccountInPetStoreByUserName(String userName, UserData userData) {
    return putBodyToDesiredEndpointWithResponse(
        getUserEditModel(
            userData.getIntegerData(USER_ID.getText()),
            userData.getStringData(USER_USERNAME.getText()),
            userData.getStringData(USER_FIRST_NAME.getText()),
            userData.getStringData(USER_LAST_NAME.getText()),
            userData.getStringData(USER_EMAIL.getText()),
            userData.getStringData(USER_PASSWORD.getText()),
            userData.getStringData(USER_PHONE.getText()),
            userData.getIntegerData(USER_STATUS.getText())),
        "/" + USER.getApiEndpointsUrl() + "/" + userName
    );
  }

  @Step("Update User account in Pet Store by username with Invalid Data - {0}")
  public Response updateUserAccountInPetStoreWithInvalidData(String userName, UserData userData,
                                                             String email, String password) {
    return putBodyToDesiredEndpointWithResponse(
        getUserEditModel(
            userData.getIntegerData(USER_ID.getText()),
            userData.getStringData(USER_USERNAME.getText()),
            userData.getStringData(USER_FIRST_NAME.getText()),
            userData.getStringData(USER_LAST_NAME.getText()),
            email,
            password,
            userData.getStringData(USER_PHONE.getText()),
            userData.getIntegerData(USER_STATUS.getText())),
        "/" + USER.getApiEndpointsUrl() + "/" + userName
    );
  }
}
