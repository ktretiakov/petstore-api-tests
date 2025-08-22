package com.petstore.api.objects.models.pet;

import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class IncomingPetModel {

  public static PetCreateModel getPetCreateModel(
      Integer id,
      PetCategoryModel category,
      String name,
      List<String> photoUrls,
      List<PetTagModel> tags,
      String status) {

    return new PetCreateModel()
        .setId(id)
        .setCategory(category)
        .setName(name)
        .setPhotoUrls(photoUrls)
        .setTags(tags)
        .setStatus(status);
  }

  public static PetEditModel getPetEditModel(
      Integer id,
      PetCategoryModel category,
      String name,
      List<String> photoUrls,
      List<PetTagModel> tags,
      String status) {

    return new PetEditModel()
        .setId(id)
        .setCategory(category)
        .setName(name)
        .setPhotoUrls(photoUrls)
        .setTags(tags)
        .setStatus(status);
  }
}
