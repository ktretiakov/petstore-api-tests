package com.petstore.api.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

  private static final Properties properties = new Properties();

  static {
    try (InputStream input = ConfigManager.class.getClassLoader()
        .getResourceAsStream("application.properties")) {
      if (input == null) {
        throw new RuntimeException("application.properties not found in resources");
      }
      properties.load(input);
    } catch (IOException e) {
      throw new RuntimeException("Failed to load application.properties", e);
    }
  }

  public static String getProperty(String key) {
    return properties.getProperty(key);
  }
}