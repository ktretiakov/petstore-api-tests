package com.petstore.api.objects.models.pet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@ToString
public class PetTagModel {

  @JsonProperty("id")
  private Integer id;

  @JsonProperty("name")
  private String name;
}
