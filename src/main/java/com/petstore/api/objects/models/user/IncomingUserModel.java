package com.petstore.api.objects.models.user;

import lombok.experimental.UtilityClass;

@UtilityClass
public class IncomingUserModel {

  public static UserCreateModel getUserCreateModel(
      Integer id,
      String username,
      String firstName,
      String lastName,
      String email,
      String password,
      String phone,
      Integer userStatus) {

    return new UserCreateModel()
        .setId(id)
        .setUsername(username)
        .setFirstName(firstName)
        .setLastName(lastName)
        .setEmail(email)
        .setPassword(password)
        .setPhone(phone)
        .setUserStatus(userStatus);
  }

  public static UserEditModel getUserEditModel(
      Integer id,
      String username,
      String firstName,
      String lastName,
      String email,
      String password,
      String phone,
      Integer userStatus) {

    return new UserEditModel()
        .setId(id)
        .setUsername(username)
        .setFirstName(firstName)
        .setLastName(lastName)
        .setEmail(email)
        .setPassword(password)
        .setPhone(phone)
        .setUserStatus(userStatus);
  }
}
