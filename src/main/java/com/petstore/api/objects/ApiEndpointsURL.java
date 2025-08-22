package com.petstore.api.objects;

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