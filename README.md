# Petstore API Test
## 📌 Overview
This is a lightweight API test automation framework built on top of:
- **Java 17**
- **TestNG**
- **RestAssured**
- **Maven**
- **Allure Report**
- **Logback / SLF4J**

The framework is designed to demonstrate:
- Basic REST API test automation practices
- Clear project structure
- Logging and reporting
- Usage of positive/negative test scenarios
- Parametrized tests

Target API: [Swagger Petstore](https://petstore.swagger.io)

---

## 📂 Project Structure
petstore-api-tests
├── src
│ ├── main/java/com/petstore/api/core # Core API classes & config
│ ├── test/java/com/petstore/api/tests # Test classes
│ └── main/resources # Test resources (if needed)
├── pom.xml # Maven build config
└── README.md # Project documentation

## ✅ Features
- Coverage for **GET, POST, PUT, DELETE** methods (positive & negative cases)
- Request/Response logging
- Request & Response attached to **Allure report** in case of test failures
- Parametrized tests
- Executable from terminal (`mvn test`)
- Console logging on failures

---

## 🚀 How to Run Tests
1. **Clone the repository**:
   ```bash
   git clone https://github.com/ktretiakov/petstore-api-tests.git
   cd petstore-api-tests

2. **🧪 Running Tests**:
    To run all tests, execute:
    ```bash
    mvn clean test

3. **📊 Generating Allure Report**:
    After tests are executed, generate and open the Allure report with:
    ```bash
    mvn allure:serve
   
---

- Tests cover GET, POST, PUT, DELETE methods (positive and negative scenarios).

- Requests and responses are logged in case of failures.

- Allure attaches request/response details to the report for failed tests.

- No need for testng.xml — tests are discovered automatically by Maven Surefire plugin.
