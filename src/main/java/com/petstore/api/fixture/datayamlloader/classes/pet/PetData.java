package com.petstore.api.fixture.datayamlloader.classes.pet;

import com.petstore.api.fixture.datayamlloader.config.DataYamlLoader;
import lombok.Builder;

import java.util.Map;

import static com.petstore.api.fixture.datayamlloader.config.DataLoader.loadPetsData;

@Builder
public class PetData implements DataYamlLoader {

  private String externalMainData;
  private Map<String, Object> externalSubData;

  @Override
  public Map<String, Object> getMapData(String mainData) {
    return loadPetsData(mainData);
  }

  @Override
  public String getStringData(String subData) {
    return loadPetsData(externalMainData).get(subData).toString();
  }

  @Override
  public Integer getIntegerData(String subData) {
    return (Integer) loadPetsData(externalMainData).get(subData);
  }

  @Override
  public Boolean getBooleanData(String subData) {
    return (Boolean) loadPetsData(externalMainData).get(subData);
  }

  public static PetData buildPetsData(String externalMainData) {
    return new PetData.PetDataBuilder().externalMainData(externalMainData).build();
  }
}
