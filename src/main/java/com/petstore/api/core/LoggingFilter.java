package com.petstore.api.core;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingFilter implements Filter {

  private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

  @Override
  public Response filter(FilterableRequestSpecification requestSpec,
                         FilterableResponseSpecification responseSpec,
                         FilterContext ctx) {

    StringBuilder log = new StringBuilder();

    log.append("===== REQUEST =====\n");
    log.append("METHOD: ").append(requestSpec.getMethod()).append("\n");
    log.append("URI: ").append(requestSpec.getURI()).append("\n");

    if (requestSpec.getHeaders() != null && !requestSpec.getHeaders().asList().isEmpty()) {
      log.append("HEADERS: ").append(requestSpec.getHeaders()).append("\n");
    }

    if (requestSpec.getBody() != null) {
      Object body = requestSpec.getBody();
      log.append("BODY: ").append(body.toString()).append("\n");
    }

    Response response = ctx.next(requestSpec, responseSpec);

    log.append("===== RESPONSE =====\n");
    log.append("Status: ").append(response.getStatusCode()).append("\n");

    if (response.getBody() != null) {
      log.append("Body: ").append(response.getBody().asPrettyString()).append("\n");
    }

    logger.info(log.toString());

    return response;
  }
}