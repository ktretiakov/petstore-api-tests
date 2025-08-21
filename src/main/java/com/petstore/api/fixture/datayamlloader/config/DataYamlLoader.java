package com.petstore.api.fixture.datayamlloader.config;

import java.util.Map;

public interface DataYamlLoader {
  Map<String, Object> getMapData(String mainData);

  String getStringData(String subData);

  Integer getIntegerData(String subData);

  Boolean getBooleanData(String subData);
}
