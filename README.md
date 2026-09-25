# Selenium Web Automation

**AI-assisted Web Test Automation Framework built with Java, Selenium WebDriver, JUnit 5, Cucumber, and Maven.**

This project is part of my Quality Assurance portfolio and aims to demonstrate practical knowledge of test automation, BDD, framework architecture, maintainability, scalability, clean code, and AI-assisted software engineering practices.

> 🚧 **Project Status:** Under development

---

## 📌 About the Project

The purpose of this project is to build a structured and scalable Web UI Test Automation Framework using Selenium WebDriver and Cucumber.

The framework is being developed incrementally. New components and abstractions are introduced as real automation requirements emerge, avoiding unnecessary complexity and overengineering.

The project currently includes:

- Selenium WebDriver browser automation
- Cucumber BDD scenarios written in Gherkin
- JUnit 5 / JUnit Platform integration
- Page Object Model
- Centralized WebDriver lifecycle management
- External framework configuration
- Multi-browser architecture
- Reusable Step Definitions
- Cucumber tags
- HTML execution reports
- Screenshots attached to Cucumber scenarios
- Positive and negative login scenarios

The main goals are:

- Apply good practices in Test Automation
- Create readable and maintainable automated tests
- Separate test logic from Selenium implementation details
- Apply the Page Object Model pattern
- Reduce code duplication
- Support multiple browsers
- Externalize framework configuration
- Generate useful test evidence and reports
- Prepare the framework for future parallel execution
- Integrate automated tests with CI/CD pipelines
- Explore responsible use of Artificial Intelligence in QA Engineering

---

## 🤖 AI-Assisted Development

Artificial Intelligence is used as a development assistant throughout this project.

AI supports activities such as:

- Framework architecture discussions
- Code review and refactoring suggestions
- Test design
- BDD scenario design
- Documentation
- Troubleshooting and debugging
- Technical research
- Exploration of automation best practices
- Comparison of implementation approaches

AI-generated suggestions are reviewed, adapted, implemented, and validated before being incorporated into the project.

The goal is to use AI as an engineering support tool while maintaining developer ownership over architectural decisions, implementation, validation, and framework evolution.

---

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| Java 21 | Programming language |
| Selenium WebDriver | Web browser automation |
| Cucumber | BDD and executable specifications |
| JUnit 5 | Test assertions and JUnit Platform integration |
| Maven | Dependency management and build |
| Git | Version control |
| GitHub | Source code repository |

Additional tools and libraries may be introduced as new framework requirements emerge.

---

## 🏗️ Framework Architecture

The framework follows a layered architecture designed to keep test scenarios readable while isolating Selenium implementation details.

```text
Feature Files
     │
     ▼
Step Definitions
     │
     ▼
Page Objects / Components
     │
     ▼
BasePage / Actions
     │
     ▼
DriverManager
     │
     ▼
Selenium WebDriver
     │
     ▼
Browser
```

Browser lifecycle is handled separately through Cucumber Hooks:

```text
Scenario starts
      │
      ▼
@Before Hook
      │
      ▼
DriverFactory
      │
      ▼
DriverManager
      │
      ▼
Scenario execution
      │
      ▼
@After Hook
      │
      ├── Attach screenshot
      │
      └── Quit WebDriver
```

This architecture prevents Step Definitions and Page Objects from being responsible for creating or closing browser instances.

---

## 📂 Project Structure

```text
selenium-web-automation/
│
├── src/
│   │
│   ├── main/
│   │   ├── java/
│   │   │   └── io/github/nayliv/
│   │   │       │
│   │   │       ├── actions/
│   │   │       ├── components/
│   │   │       │
│   │   │       ├── config/
│   │   │       │   └── ConfigManager.java
│   │   │       │
│   │   │       ├── driver/
│   │   │       │   ├── BrowserType.java
│   │   │       │   ├── DriverFactory.java
│   │   │       │   └── DriverManager.java
│   │   │       │
│   │   │       ├── exceptions/
│   │   │       ├── models/
│   │   │       │
│   │   │       ├── pages/
│   │   │       │   ├── BasePage.java
│   │   │       │   ├── LoginPage.java
│   │   │       │   └── ProductsPage.java
│   │   │       │
│   │   │       └── utils/
│   │   │
│   │   └── resources/
│   │       └── config/
│   │           └── application.properties
│   │
│   └── test/
│       ├── java/
│       │   └── io/github/nayliv/
│       │       │
│       │       ├── hooks/
│       │       │   └── Hooks.java
│       │       │
│       │       ├── runners/
│       │       │   └── RunCucumberTest.java
│       │       │
│       │       └── steps/
│       │           └── LoginSteps.java
│       │
│       └── resources/
│           └── features/
│               └── login.feature
│
├── target/
│   ├── reports/
│   │   └── cucumber/
│   │       └── cucumber.html
│   │
│   └── surefire-reports/
│
├── .gitignore
├── pom.xml
└── README.md
```

> The `target` directory contains generated build and execution artifacts and is not versioned.

---

## ⚙️ Configuration Management

Framework configuration is stored in:

```text
src/main/resources/config/application.properties
```

Example:

```properties
browser=chrome
base.url=https://www.saucedemo.com/
timeout=10
headless=false
```

Configuration access is centralized through `ConfigManager`.

Example:

```java
ConfigManager.getBaseUrl();
ConfigManager.getBrowser();
ConfigManager.getTimeout();
ConfigManager.isHeadless();
```

This prevents configuration values from being hardcoded throughout the framework.

The configuration flow is:

```text
application.properties
        │
        ▼
ConfigManager
        │
        ├── Hooks
        ├── Steps
        └── Framework components
```

---

## 🌐 WebDriver Management

### BrowserType

The supported browser types are represented through an enum.

Example:

```java
BrowserType.CHROME
BrowserType.FIREFOX
BrowserType.EDGE
```

---

### DriverFactory

`DriverFactory` is responsible for creating the appropriate WebDriver implementation.

Example:

```java
WebDriver driver =
        DriverFactory.createDriver(
                ConfigManager.getBrowser()
        );
```

---

### DriverManager

`DriverManager` stores the current WebDriver instance using `ThreadLocal`.

This allows framework components to access the active driver without creating additional browser instances.

```java
DriverManager.getDriver();
```

The use of `ThreadLocal` also prepares the architecture for future parallel execution.

---

## 🔄 Browser Lifecycle

The WebDriver lifecycle is controlled by Cucumber Hooks.

```text
@Before
   │
   ├── Read browser configuration
   ├── Create WebDriver
   ├── Store WebDriver in DriverManager
   └── Configure browser
        │
        ▼
    Scenario
        │
        ▼
@After
   │
   ├── Attach final screenshot
   └── Quit WebDriver
```

Each scenario receives a fresh browser session.

---

## 🧩 Page Object Model

The framework uses the Page Object Model to separate UI implementation details from test scenarios.

For example, instead of exposing Selenium commands directly inside Step Definitions:

```java
driver.findElement(By.id("user-name")).sendKeys(username);
driver.findElement(By.id("password")).sendKeys(password);
driver.findElement(By.id("login-button")).click();
```

the test can use a higher-level action:

```java
loginPage.login(username, password);
```

This improves:

- Readability
- Reusability
- Maintainability
- Separation of responsibilities
- Scalability

---

## 🧱 BasePage

`BasePage` provides reusable Selenium operations shared by Page Objects.

Current operations include:

```text
find
click
type
getText
isDisplayed
```

Additional abstractions will only be introduced when they become necessary for real test scenarios.

---

## 🥒 BDD with Cucumber

Test scenarios are written using Gherkin.

Example:

```gherkin
@login @smoke @positive
Scenario: Login successfully with valid credentials
  Given the user is on the login page
  When the user logs in with username "standard_user" and password "secret_sauce"
  Then the products page should be displayed
```

Step Definitions translate Gherkin statements into framework actions.

```java
@When("the user logs in with username {string} and password {string}")
public void theUserLogsInWithUsernameAndPassword(
        String username,
        String password) {

    loginPage.login(username, password);
}
```

This allows the same Step Definition to be reused with different test data.

---

## 🧪 Current Login Test Coverage

The current login feature covers important positive and negative scenarios:

- Successful login with valid credentials
- Login with invalid credentials
- Login without username
- Login without password
- Login without username and password
- Login with a locked user

Examples of tags currently used:

```text
@login
@smoke
@positive
@negative
@validation
```

Tags allow scenarios to be classified without duplicating tests into separate suites or runners.

---

## ▶️ Running the Tests

To execute all tests:

```bash
mvn clean test
```

Tests can also be executed through the `RunCucumberTest` runner directly from the IDE.

The execution flow is:

```text
RunCucumberTest
      │
      ▼
Feature files
      │
      ▼
Hooks @Before
      │
      ▼
Step Definitions
      │
      ▼
Page Objects
      │
      ▼
Assertions
      │
      ▼
Hooks @After
      │
      ▼
Report generation
```

---

## 📊 Cucumber HTML Report

The framework generates a native Cucumber HTML report after execution.

The report is generated at:

```text
target/reports/cucumber/cucumber.html
```

The report contains:

- Features
- Scenarios
- Tags
- Executed Steps
- Execution duration
- Passed and failed Steps
- Error details
- Scenario screenshots

The Cucumber runner uses the HTML formatter alongside console output.

Example configuration:

```java
@ConfigurationParameter(
        key = PLUGIN_PROPERTY_NAME,
        value = "pretty, html:target/reports/cucumber/cucumber.html"
)
```

---

## 📸 Test Evidence

At the end of each scenario, the framework captures the final browser state before closing the WebDriver.

The screenshot is attached directly to the Cucumber `Scenario`:

```java
scenario.attach(
        screenshot,
        "image/png",
        "Validation - " + scenario.getName()
);
```

This means screenshots are available directly inside the Cucumber HTML report for both:

```text
PASSED scenarios
FAILED scenarios
```

Capturing the screenshot before `DriverManager.quitDriver()` provides visual evidence of the final application state.

---

## 🏷️ Test Organization

Features are organized by application functionality rather than execution classification.

Example:

```text
features/
├── login/
├── products/
├── cart/
└── checkout/
```

Execution classifications should be handled through Cucumber tags such as:

```text
@smoke
@regression
@positive
@negative
@validation
@critical
```

This avoids creating separate runners for each feature or execution type.

---

## ✅ Automation Principles

Some principles adopted in this project:

- Tests should be independent
- Scenarios should be readable
- Selenium implementation details should remain outside Gherkin scenarios
- Browser creation should be centralized
- Browser lifecycle should be controlled by Hooks
- Page Objects should represent page behavior
- Assertions should remain outside Page Objects
- Reusable Steps should avoid unnecessary duplication
- Configuration values should not be hardcoded
- Explicit waits should be preferred over fixed sleeps
- Generated reports should remain under Maven's `target` directory
- Framework complexity should be introduced only when a real need emerges
- AI-generated suggestions should be reviewed and validated before adoption

---

## 🗺️ Roadmap

### Core Framework

- [x] Create Maven project
- [x] Define initial package structure
- [x] Configure framework properties
- [x] Implement `ConfigManager`
- [x] Implement `BrowserType`
- [x] Implement `DriverFactory`
- [x] Implement `DriverManager`
- [x] Implement `BasePage`
- [x] Implement Cucumber Hooks
- [x] Configure JUnit Platform + Cucumber runner

### Test Automation

- [x] Create first Page Objects
- [x] Create first Cucumber feature
- [x] Create reusable Step Definitions
- [x] Implement positive login scenario
- [x] Implement negative login scenarios
- [x] Add Cucumber tags
- [ ] Add explicit waits
- [ ] Add reusable element actions
- [ ] Expand test data strategy
- [ ] Add reusable UI components

### Reporting

- [x] Add Cucumber HTML report
- [x] Attach screenshots to scenarios
- [ ] Improve failure evidence
- [ ] Add structured logging
- [ ] Evaluate advanced reporting when necessary

### Framework Improvements

- [ ] Headless execution
- [ ] Multi-browser execution validation
- [ ] Multiple environment configuration
- [ ] Parallel execution
- [ ] Custom framework exceptions
- [ ] Improve synchronization strategy

### DevOps

- [ ] GitHub Actions integration
- [ ] Automated test execution through CI
- [ ] Cross-browser execution in CI
- [ ] Publish test reports as pipeline artifacts

### AI-Assisted Engineering

- [x] Use AI to support framework architecture discussions
- [x] Use AI to support technical documentation
- [x] Use AI for implementation reviews and learning
- [x] Use AI to support test scenario design
- [ ] Explore AI-assisted failure analysis
- [ ] Explore AI-assisted test maintenance approaches

---

## 📚 Development Approach

This project is also used as a practical learning environment.

Instead of implementing every possible framework feature upfront, new capabilities are introduced when actual automation scenarios expose a need.

The development approach follows:

```text
New Scenario
     │
     ▼
Can the current framework support it?
     │
 ┌───┴────┐
 │        │
Yes       No
 │        │
 ▼        ▼
Reuse   Identify missing capability
          │
          ▼
      Implement it
          │
          ▼
       Refactor
```

This approach helps keep the framework simple, maintainable, and driven by practical requirements rather than unnecessary abstractions.

---

## ⚙️ Requirements

Before running the project, make sure the following tools are installed:

- Java 21
- Maven
- Git
- Google Chrome, Mozilla Firefox, or Microsoft Edge
- IDE such as IntelliJ IDEA

Verify the installations:

```bash
java -version
```

```bash
mvn -version
```

```bash
git --version
```

---

## 📥 Installation

Clone the repository:

```bash
git clone https://github.com/nayliv/selenium-web-automation.git
```

Navigate to the project:

```bash
cd selenium-web-automation
```

Install dependencies and build the project:

```bash
mvn clean install
```

Run the automated tests:

```bash
mvn clean test
```

After execution, open:

```text
target/reports/cucumber/cucumber.html
```

to inspect the test report.

---

## 👩‍💻 Author

**Nayra**

Quality Assurance professional focused on Software Testing and Test Automation.

Areas of interest:

- Test Automation
- Selenium WebDriver
- Cucumber / BDD
- Java
- Appium
- API Testing
- Software Quality
- CI/CD
- Artificial Intelligence applied to QA

---

## 📄 License

This project is intended for study and portfolio purposes.
