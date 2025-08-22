package com.petstore.api.objects.datayamlloader.classes.store;

import com.petstore.api.objects.datayamlloader.config.DataYamlLoader;
import lombok.Builder;

import java.util.Map;

import static com.petstore.api.objects.datayamlloader.config.DataLoader.loadStoreData;

@Builder
public class StoreData implements DataYamlLoader {

  private String externalMainData;
  private Map<String, Object> externalSubData;

  @Override
  public Map<String, Object> getMapData(String mainData) {
    return loadStoreData(mainData);
  }

  @Override
  public String getStringData(String subData) {
    return loadStoreData(externalMainData).get(subData).toString();
  }

  @Override
  public Integer getIntegerData(String subData) {
    return (Integer) loadStoreData(externalMainData).get(subData);
  }

  @Override
  public Boolean getBooleanData(String subData) {
    return (Boolean) loadStoreData(externalMainData).get(subData);
  }

  public static StoreData buildStoreData(String externalMainData) {
    return new StoreData.StoreDataBuilder().externalMainData(externalMainData).build();
  }
}
