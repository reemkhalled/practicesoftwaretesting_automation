# PracticeSoftwareTesting – UI Test Automation

End-to-end UI test automation for [Practice Software Testing (Toolshop)](https://practicesoftwaretesting.com/), built with **Java, Selenium WebDriver, and TestNG** using the **Page Object Model (POM)**.

## What is tested

| Test class | Scenario |
|---|---|
| `TestRegister` | Registers a new user with randomly generated data and verifies the redirect to the login page |
| `TestLogin` | Registers a fresh user, logs in with those credentials, and verifies the redirect to the account page |
| `TestE2EPurchase` | Full journey: register → login → open the home page → select **Combination Pliers** → add to cart → checkout with **Cash on Delivery** → verify payment success and order confirmation |

## Tech stack

- Java 24
- Selenium WebDriver 4.45.0
- TestNG 7.12.0
- WebDriverManager 5.9.2 (automatic ChromeDriver management)
- Allure TestNG 2.35.3 (reporting dependency)
- Maven

## Project structure

```
src
├── main/java/io/paysky
│   ├── Pages          # Page objects
│   │   ├── LoginPage.java
│   │   ├── RegisterPage.java
│   │   ├── HomePage.java
│   │   ├── ProductPage.java
│   │   └── CheckoutPage.java
│   └── Utils          # Helpers
│       ├── BaseTest.java          # Browser setup / teardown
│       ├── UserData.java          # User model
│       └── RandomDataUtils.java   # Random test-data generation
└── test
    ├── java/io/paysky/Tests       # Test classes
    │   ├── TestRegister.java
    │   ├── TestLogin.java
    │   └── TestE2EPurchase.java
    └── resources
        └── TestNg.xml             # TestNG suite configuration
```

## Prerequisites

- JDK 24 (the `pom.xml` targets Java 24)
- Maven 3.x
- Google Chrome (the matching driver is downloaded automatically by WebDriverManager)
- Internet access to reach `https://practicesoftwaretesting.com/`

## Getting started

```bash
git clone https://github.com/reemkhalled/practicesoftwaretesting_automation.git
cd practicesoftwaretesting_automation
```

## Running the tests

**From IntelliJ IDEA:** right-click `src/test/resources/TestNg.xml` and choose **Run**.

**From the command line:**

```bash
mvn clean test -DsuiteXmlFile=src/test/resources/TestNg.xml
```

> Note: for `mvn test` to pick up the suite file, the Maven Surefire plugin must be configured in `pom.xml`, or the suite file passed as shown above.

### Running specific groups

`TestNg.xml` selects tests by group (currently `E2E` and `Login`). To run a different set, edit the `<include name="..."/>` entries under `<groups><run>`.

## Test data

Each run generates unique user data (name, date of birth, address, phone, email, password) through `RandomDataUtils` and `UserData`, so tests never collide with accounts created in earlier runs.

## Author

Reem Khaled
