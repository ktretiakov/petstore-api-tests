package com.petstore.api.core.listeners;

import io.qameta.allure.Attachment;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureListener implements ITestListener {

  @Override
  public void onTestFailure(ITestResult result) {
    Object testClass = result.getInstance();
    if (testClass instanceof HasApiResponse) {
      String request = ((HasApiResponse) testClass).getLastRequest();
      String response = ((HasApiResponse) testClass).getLastResponse();
      attachRequest(request);
      attachResponse(response);
    }
  }

  @Attachment(value = "Request", type = "text/plain")
  public String attachRequest(String request) {
    return request;
  }

  @Attachment(value = "Response", type = "application/json")
  public String attachResponse(String response) {
    return response;
  }

  @Override
  public void onTestStart(ITestResult result) {
  }

  @Override
  public void onTestSuccess(ITestResult result) {
  }

  @Override
  public void onTestSkipped(ITestResult result) {
  }

  @Override
  public void onStart(ITestContext context) {
  }

  @Override
  public void onFinish(ITestContext context) {
  }
}
