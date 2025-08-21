package com.petstore.api.fixture.datayamlloader.classes.user;

import com.petstore.api.fixture.datayamlloader.config.Textable;

public enum UserDataTypes implements Textable {

  USER_ID("id"),
  USER_USERNAME("username"),
  USER_FIRST_NAME("firstName"),
  USER_LAST_NAME("lastName"),
  USER_EMAIL("email"),
  USER_PASSWORD("password"),
  USER_PHONE("phone"),
  USER_STATUS("userStatus"),

  USER_API_EXPECTED_DATA("userApiExpectedData"),
  USER_API_UPDATED_DATA("userApiUpdatedData"),
  USER_API_INVALID_CREATE_DATA("userApiInvalidCreateData"),;

  private final String dataText;

  UserDataTypes(String dataText) {
    this.dataText = dataText;
  }

  @Override
  public String getText() {
    return dataText;
  }
}
