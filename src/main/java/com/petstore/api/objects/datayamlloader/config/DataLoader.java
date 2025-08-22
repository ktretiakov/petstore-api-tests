package com.petstore.api.objects.datayamlloader.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

import static java.lang.String.format;

public class DataLoader {

  private static final String FIXTURE_PATH_WEB = "fixtures/%sData.yml";
  private static final String FIXTURE_PATH_HTTP = "fixtures/%sHttp.yml";
  private static final ObjectMapper objectMapper = new ObjectMapper();
  private static final Yaml yaml = new Yaml();

  private DataLoader() {
  }


  public static Map<String, Object> loadData(DataModule module, String name) {
    String path = name.endsWith("_fixture")
        ? format(FIXTURE_PATH_HTTP, module)
        : format(FIXTURE_PATH_WEB, module);

    return getDataFromYaml(path, name);
  }

  @SuppressWarnings("unchecked")
  private static Map<String, Object> getDataFromYaml(String path, String name) {
    try (InputStream inputStream = DataLoader.class.getClassLoader().getResourceAsStream(path)) {
      if (inputStream == null) {
        throw new IllegalArgumentException("YAML file not found: " + path);
      }
      Map<String, Object> allData = yaml.load(inputStream);
      if (!allData.containsKey(name)) {
        throw new IllegalArgumentException("No fixture '" + name + "' in " + path);
      }
      return (Map<String, Object>) allData.get(name);
    } catch (Exception e) {
      throw new RuntimeException("Error reading yaml: " + path, e);
    }
  }

  public static Map<String, Object> loadUsersData(String name) {
    return loadData(DataModule.USERS_DATA, name);
  }

  public static Map<String, Object> loadStoreData(String name) {
    return loadData(DataModule.STORE_DATA, name);
  }

  public static Map<String, Object> loadPetsData(String name) {
    return loadData(DataModule.PET_DATA, name);
  }

}
