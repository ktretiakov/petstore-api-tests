package com.petstore.api.core;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

public class PetStoreConfigApi extends AbstractHttpUrl {

  public PetStoreConfigApi() {
    super();
  }

  /**
   * Returns Response of posting desired body to desired endpoint. Method for custom posting.
   *
   * @param body     - Object value of desired Item
   * @param endpoint - String value of desired endpoint
   * @return the Response value as a result of posting new custom Item to Endpoint
   * @see Response
   */
  public Response postBodyToDesiredEndpointWithResponse(Object body, String endpoint) {
    return RestAssured.given()
        .relaxedHTTPSValidation()
        .spec(requestSpecification)
        .body(body)
        .post(endpoint);
  }

  /**
   * Returns Response of patching desired body to desired endpoint. Method for custom posting.
   *
   * @param body     - Object value of desired Item
   * @param endpoint - String value of desired endpoint
   * @return the Response value as a result of patching ting new custom Item to Endpoint
   * @see Response
   */
  public Response patchBodyToDesiredEndpointWithResponse(Object body, String endpoint) {
    return RestAssured.given()
        .relaxedHTTPSValidation()
        .spec(requestSpecification)
        .body(body)
        .patch(endpoint);
  }

  /**
   * Returns Response of patching desired body to desired endpoint. Method for custom posting.
   *
   * @param endpoint - String value of desired endpoint
   * @return the Response value as a result of patching ting new custom Item to Endpoint
   * @see Response
   */
  public Response patchBodyToDesiredEndpointWithResponseWithoutBody(String endpoint) {
    return RestAssured.given()
        .relaxedHTTPSValidation()
        .spec(requestSpecification)
        .patch(endpoint);
  }

  /**
   * Returns Response of putting desired body to desired endpoint. Method for custom putting.
   *
   * @param body     - Object value of desired Item
   * @param endpoint - String value of desired endpoint
   * @return the Response value as a result of putting ting new custom Item to Endpoint
   * @see Response
   */
  public Response putBodyToDesiredEndpointWithResponse(Object body, String endpoint) {
    return RestAssured.given()
        .relaxedHTTPSValidation()
        .spec(requestSpecification)
        .body(body)
        .put(endpoint);
  }

  /**
   * Returns Response of deleting item by Id from desired endpoint. Method for custom deleting.
   *
   * @param endpoint - String value of desired endpoint
   * @return the Response value as a result of deleting Item from Endpoint
   * @see Response
   */
  public Response deleteItemByIdFromDesiredEndpointWithResponse(String endpoint) {
    return RestAssured.given()
        .relaxedHTTPSValidation()
        .spec(requestSpecification)
        .delete(endpoint);
  }

  /**
   * Deletes item from desired endpoint with query parameters.
   *
   * @param endpoint   - String value of desired endpoint
   * @param parameters - Map of query parameters
   * @return the Response value as a result of deleting Item from Endpoint
   * @see Response
   */
  public Response deleteItemWithParams(String endpoint, Map<String, ?> parameters) {
    return RestAssured.given()
        .relaxedHTTPSValidation()
        .spec(requestSpecification)
        .queryParams(parameters)
        .delete(endpoint);
  }

  /**
   * Returns Response of getting request from desired endpoint. Method for custom getting.
   *
   * @param endpointWithItemId - String value of desired endpoint with item id
   * @return the Response value as a result of getting Item from the Endpoint
   * @see Response
   */
  public Response getItemByItsIdFromDesiredEndpoint(String endpointWithItemId) {
    return RestAssured.given()
        .relaxedHTTPSValidation()
        .spec(requestSpecification)
        .get(endpointWithItemId);
  }

  /**
   * Returns Response of a GET request with query parameters.
   * Method for retrieving an item from the desired endpoint with parameters.
   *
   * @param endpoint    - String value of the desired endpoint
   * @param queryParams - Map of query parameters (key-value pairs)
   * @return Response value as a result of the GET request with parameters
   * @see Response
   */
  public Response getItemWithParams(String endpoint, Map<String, Object> queryParams) {
    return RestAssured.given()
        .relaxedHTTPSValidation()
        .spec(requestSpecification)
        .queryParams(queryParams)
        .get(endpoint);
  }

}