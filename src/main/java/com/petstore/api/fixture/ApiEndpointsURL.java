package com.petstore.api.fixture;

public enum ApiEndpointsURL {
  USER("user");

  private final String relativeUrl;

  ApiEndpointsURL(String relativeUrl) {
    this.relativeUrl = relativeUrl;
  }

  public String getApiEndpointsUrl() {
    return relativeUrl;
  }
}