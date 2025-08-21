package com.petstore.api.core;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;

public abstract class AbstractHttpUrl {

  @Getter
  protected RequestSpecification requestSpecification;

  public AbstractHttpUrl() {
    this.requestSpecification = new RequestSpecBuilder()
        .setBaseUri(ConfigManager.getProperty("base.uri"))
        .setContentType("application/json")
        .log(LogDetail.ALL)
        .addFilter(new AllureRestAssured())
        .addFilter(new LoggingFilter())
        .build();
  }
}
