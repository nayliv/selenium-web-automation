# Selenium Web Automation

Web UI Test Automation Framework developed with **Java, Selenium WebDriver, JUnit 5, and Maven**.

This project was created as part of my Quality Assurance portfolio with the goal of demonstrating good practices in test automation, framework architecture, maintainability, scalability, and clean code.

> 🚧 **Project Status:** Under development

---

## 📌 About the Project

The purpose of this project is to build a structured and scalable Web UI Test Automation Framework using Selenium WebDriver.

The framework is being developed incrementally, starting with the core architecture and evolving toward features such as configuration management, reusable page components, explicit waits, reporting, screenshots, logging, parallel execution, and CI/CD integration.

The main goals are:

- Apply good practices in Test Automation
- Implement the Page Object Model pattern
- Separate framework responsibilities into dedicated layers
- Reduce code duplication
- Improve test readability and maintainability
- Support multiple browsers
- Prepare the framework for parallel execution
- Generate useful execution evidence and reports
- Integrate automated tests with CI/CD pipelines

---

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| Java | Programming language |
| Selenium WebDriver | Web browser automation |
| JUnit 5 | Test framework |
| Maven | Dependency management and build |
| Git | Version control |
| GitHub | Source code repository |

Additional tools and libraries may be added as the framework evolves.

---

## 🏗️ Project Architecture

The framework follows a layered architecture designed to separate test logic from Selenium implementation details.

```text
Tests
  │
  ▼
Page Objects / Components
  │
  ▼
Actions
  │
  ▼
Selenium WebDriver
  │
  ▼
Browser
```

The objective is to prevent Selenium-specific implementation details from being directly coupled to the test cases.

For example, test classes should preferably use:

```java
loginPage.login(username, password);
```

instead of:

```java
driver.findElement(By.id("username")).sendKeys(username);
driver.findElement(By.id("password")).sendKeys(password);
driver.findElement(By.id("login")).click();
```

This approach improves readability, reusability, and maintainability.

---

## 📂 Project Structure

```text
selenium-web-automation/
│
├── logs/
├── reports/
├── screenshots/
│
├── src/
│   │
│   ├── main/
│   │   ├── java/
│   │   │   └── io/github/nayliv/
│   │   │       │
│   │   │       ├── actions/
│   │   │       ├── components/
│   │   │       ├── config/
│   │   │       ├── driver/
│   │   │       ├── exceptions/
│   │   │       ├── models/
│   │   │       ├── pages/
│   │   │       └── utils/
│   │   │
│   │   └── resources/
│   │
│   └── test/
│       ├── java/
│       │   └── io/github/nayliv/
│       │
│       └── resources/
│
├── .gitignore
├── pom.xml
└── README.md
```

### Main packages

| Package | Responsibility |
|---|---|
| `actions` | Reusable browser and element interactions |
| `components` | Reusable UI components shared between pages |
| `config` | Framework and environment configuration |
| `driver` | WebDriver creation and lifecycle management |
| `exceptions` | Custom framework exceptions |
| `models` | Objects representing test data or domain entities |
| `pages` | Page Objects representing application pages |
| `utils` | Generic utility classes |

The test layer will contain test classes, test setup, listeners, data providers, and other test-specific resources.

---

## 🧩 Design Patterns

The framework is being designed around automation patterns such as:

### Page Object Model

Each application page is represented by a dedicated class responsible for:

- Element locators
- Page interactions
- Page-specific behavior

This keeps Selenium implementation details outside the test classes.

### Component Object Model

Reusable interface elements such as:

- Headers
- Menus
- Modals
- Tables
- Dropdowns

can be represented as independent components and reused across multiple Page Objects.

### Driver Factory

Browser creation will be centralized in a dedicated factory instead of creating WebDriver instances directly inside test classes.

Example:

```java
WebDriver driver = DriverFactory.createDriver(BrowserType.CHROME);
```

### Driver Manager

WebDriver lifecycle management will be centralized to simplify access to the current browser instance and prepare the framework for parallel execution.

---

## ⚙️ Requirements

Before running the project, make sure the following tools are installed:

- Java
- Maven
- Git
- Google Chrome, Firefox, or Microsoft Edge
- IDE such as IntelliJ IDEA

Check the installations with:

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

Install the dependencies and build the project:

```bash
mvn clean install
```

---

## ▶️ Running the Tests

To execute all automated tests:

```bash
mvn clean test
```

More execution options will be documented as the framework evolves.

---

## 🌐 Browser Management

The framework is being designed to support multiple browsers, including:

- Google Chrome
- Mozilla Firefox
- Microsoft Edge

Modern Selenium versions provide **Selenium Manager**, which can automatically manage the required browser drivers in supported environments.

---

## 🧪 Test Strategy

Automated tests will be organized primarily by application feature rather than execution type.

Example:

```text
tests/
├── login/
├── account/
├── checkout/
└── payment/
```

Execution classifications such as:

- Smoke
- Regression
- Critical
- End-to-End

can be handled through test tags instead of duplicating tests into different folders.

---

## 🗺️ Roadmap

### Core Framework

- [x] Create Maven project
- [x] Define initial package structure
- [x] Configure framework properties
- [x] Implement BrowserType
- [x] Implement DriverFactory
- [x] Implement DriverManager
- [ ] Implement BaseTest
- [ ] Implement BasePage

### Test Automation

- [ ] Create first Page Object
- [ ] Create first automated test
- [ ] Add explicit waits
- [ ] Add reusable element actions
- [ ] Add assertions strategy
- [ ] Add test data management

### Framework Improvements

- [ ] Screenshot capture on failures
- [ ] Logging
- [ ] Test listeners
- [ ] Custom exceptions
- [ ] Multi-browser execution
- [ ] Parallel execution
- [ ] Environment configuration
- [ ] Test reports

### DevOps

- [ ] GitHub Actions integration
- [ ] Automated test execution through CI
- [ ] Publish test reports as pipeline artifacts

---

## ✅ Automation Principles

Some principles adopted in this project:

- Tests should be independent
- Tests should be readable
- Tests should not directly manipulate WebDriver whenever abstraction is appropriate
- Page Objects should contain page behavior, not test assertions
- Reusable components should not be duplicated across pages
- Test data should be separated from test logic when appropriate
- Hard-coded configuration values should be avoided
- Explicit waits should be preferred over fixed sleeps
- Framework complexity should be introduced only when there is a real need

---

## 🎯 Current Development Stage

The project is currently focused on establishing the core automation architecture.

The next implementation steps are:

```text
Configuration
      ↓
DriverFactory
      ↓
DriverManager
      ↓
BaseTest
      ↓
BasePage
      ↓
First Page Object
      ↓
First Automated Test
```

The framework will evolve incrementally as new automation requirements are introduced.

---

## 👩‍💻 Author

**Nayra**

Quality Assurance professional focused on Software Testing and Test Automation.

Areas of interest:

- Test Automation
- Selenium WebDriver
- Appium
- Java
- API Testing
- Software Quality
- CI/CD
- Artificial Intelligence applied to QA

---

## 📄 License

This project is intended for study and portfolio purposes.
