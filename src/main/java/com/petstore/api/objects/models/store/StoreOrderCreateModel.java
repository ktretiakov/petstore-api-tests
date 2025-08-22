package com.petstore.api.objects.models.store;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@ToString
public class StoreOrderCreateModel {

  @JsonProperty("id")
  private Integer id;

  @JsonProperty("petId")
  private Long petId;

  @JsonProperty("quantity")
  private Integer quantity;

  @JsonProperty("shipDate")
  private String shipDate;

  @JsonProperty("status")
  private String status;

  @JsonProperty("complete")
  private Boolean complete;
}
