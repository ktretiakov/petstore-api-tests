package com.petstore.api.fixture.datayamlloader.classes.user;

import com.petstore.api.fixture.datayamlloader.config.DataYamlLoader;
import lombok.Builder;

import java.util.Map;

import static com.petstore.api.fixture.datayamlloader.config.DataLoader.loadUsersData;

@Builder
public class UserData implements DataYamlLoader {

  private String externalMainData;
  private Map<String, Object> externalSubData;

  @Override
  public Map<String, Object> getMapData(String mainData) {
    return loadUsersData(mainData);
  }

  @Override
  public String getStringData(String subData) {
    return loadUsersData(externalMainData).get(subData).toString();
  }

  @Override
  public Integer getIntegerData(String subData) {
    return (Integer) loadUsersData(externalMainData).get(subData);
  }

  @Override
  public Boolean getBooleanData(String subData) {
    return (Boolean) loadUsersData(externalMainData).get(subData);
  }

  public static UserData buildUserData(String externalMainData) {
    return new UserData.UserDataBuilder().externalMainData(externalMainData).build();
  }
}
