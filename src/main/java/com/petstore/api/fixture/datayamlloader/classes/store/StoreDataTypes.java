package com.petstore.api.fixture.datayamlloader.classes.store;

import com.petstore.api.fixture.datayamlloader.config.Textable;

public enum StoreDataTypes implements Textable {

  STORE_API_EXPECTED_DATA("storeApiExpectedData");

  private final String dataText;

  StoreDataTypes(String dataText) {
    this.dataText = dataText;
  }

  @Override
  public String getText() {
    return dataText;
  }
}
