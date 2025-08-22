package com.petstore.api.objects.models.store;

import lombok.experimental.UtilityClass;

@UtilityClass
public class IncomingStoreOrderModel {

  public static StoreOrderCreateModel getStoreOrderCreateModel(
      Integer id,
      Long petId,
      Integer quantity,
      String shipDate,
      String status,
      Boolean complete) {

    return new StoreOrderCreateModel()
        .setId(id)
        .setPetId(petId)
        .setQuantity(quantity)
        .setShipDate(shipDate)
        .setStatus(status)
        .setComplete(complete);
  }
}
