package com.petstore.api.objects.datayamlloader.classes.pet;

import com.petstore.api.objects.datayamlloader.config.Textable;

public enum PetDataTypes implements Textable {

  STORE_API_EXPECTED_DATA("storeApiExpectedData");

  private final String dataText;

  PetDataTypes(String dataText) {
    this.dataText = dataText;
  }

  @Override
  public String getText() {
    return dataText;
  }
}
