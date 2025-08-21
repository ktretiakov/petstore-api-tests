package com.petstore.api.fixture.datayamlloader.config;

import lombok.Getter;

@Getter
public enum DataModule {
  USERS_DATA("user/"),
  STORE_DATA("store/"),
  PET_DATA("pet/");

  private final String module;

  DataModule(String module) {
    this.module = module;
  }

  @Override
  public String toString() {
    return this.getModule();
  }
}
