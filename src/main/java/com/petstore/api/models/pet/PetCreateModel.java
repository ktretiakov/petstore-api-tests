package com.petstore.api.models.pet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@ToString
public class PetCreateModel {

  @JsonProperty("id")
  private Integer id;

  @JsonProperty("category")
  private PetCategoryModel category;

  @JsonProperty("name")
  private String name;

  @JsonProperty("photoUrls")
  private List<String> photoUrls;

  @JsonProperty("tags")
  private List<PetTagModel> tags;

  @JsonProperty("status")
  private String status;
}
