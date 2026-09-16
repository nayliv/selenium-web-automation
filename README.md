# Selenium Web Automation

**AI-assisted Web Test Automation Framework built with Java, Selenium WebDriver, JUnit 5, and Maven.**

This project is part of my Quality Assurance portfolio and aims to demonstrate practical knowledge of test automation, framework architecture, maintainability, scalability, clean code, and AI-assisted software engineering practices.

> 🚧 **Project Status:** Under development

---

## 📌 About the Project

The purpose of this project is to build a structured and scalable Web UI Test Automation Framework using Selenium WebDriver.

The framework is being developed incrementally, starting with its core architecture and evolving toward features such as configuration management, reusable page components, explicit waits, reporting, screenshots, logging, parallel execution, and CI/CD integration.

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
- Explore responsible use of Artificial Intelligence throughout the development process

---

## 🤖 AI-Assisted Development

Artificial Intelligence is used as a development assistant throughout this project.

AI supports activities such as:

- Framework architecture discussions
- Code review and refactoring suggestions
- Test design
- Documentation
- Troubleshooting and debugging
- Technical research
- Exploration of automation best practices
- Learning and comparison of different implementation approaches

AI-generated suggestions are reviewed, adapted, implemented, and validated before being incorporated into the project.

The purpose of using AI is not to replace the engineering process, but to support learning, technical decision-making, and development productivity while maintaining developer ownership over the final implementation.

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

Additional tools and libraries may be introduced as the framework evolves.

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

The goal is to prevent Selenium-specific implementation details from being directly coupled to test cases.

For example, test classes should preferably use higher-level interactions such as:

```java
loginPage.login(username, password);
```

instead of directly manipulating browser elements:

```java
driver.findElement(By.id("username")).sendKeys(username);
driver.findElement(By.id("password")).sendKeys(password);
driver.findElement(By.id("login")).click();
```

This approach improves:

- Readability
- Reusability
- Maintainability
- Separation of responsibilities
- Scalability of the automation suite

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

> `logs`, `reports`, and `screenshots` are intended to contain runtime-generated artifacts and should not normally be versioned.

### Main Packages

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

## 🧩 Design Patterns and Concepts

The framework is being designed around automation patterns and architectural concepts that improve maintainability and reusability.

### Page Object Model

Each application page is represented by a dedicated class responsible for:

- Element locators
- Page interactions
- Page-specific behavior

This keeps Selenium implementation details outside the test classes.

---

### Component Object Model

Reusable interface elements such as:

- Headers
- Menus
- Modals
- Tables
- Dropdowns

can be represented as independent components and reused across multiple Page Objects.

---

### Driver Factory

Browser creation is centralized instead of creating WebDriver instances directly inside test classes.

Example:

```java
WebDriver driver = DriverFactory.createDriver(BrowserType.CHROME);
```

This makes browser creation easier to maintain and extend.

---

### Driver Manager

WebDriver lifecycle management is centralized to simplify access to the current browser instance and prepare the framework for future parallel execution.

---

### Configuration Management

Framework configuration is externalized whenever possible.

Examples of configurable properties include:

```properties
browser=chrome
base.url=https://example.com
timeout=10
headless=false
```

This avoids spreading hard-coded configuration values throughout the automation code.

---

## ⚙️ Requirements

Before running the project, make sure the following tools are installed:

- Java
- Maven
- Git
- Google Chrome, Mozilla Firefox, or Microsoft Edge
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

Navigate to the project directory:

```bash
cd selenium-web-automation
```

Build the project:

```bash
mvn clean install
```

---

## ▶️ Running the Tests

To execute all automated tests:

```bash
mvn clean test
```

Additional execution options will be documented as the framework evolves.

---

## 🌐 Browser Management

The framework is being designed to support multiple browsers, including:

- Google Chrome
- Mozilla Firefox
- Microsoft Edge

Browser selection is handled through framework configuration and the `BrowserType` enum.

Modern Selenium versions also provide **Selenium Manager**, which can automatically manage compatible browser drivers in supported environments.

---

## 🧪 Test Strategy

Automated tests will primarily be organized by application functionality rather than execution classification.

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

can be controlled through test tags instead of duplicating tests into separate folders.

---

## 🗺️ Roadmap

### Core Framework

- [x] Create Maven project
- [x] Define initial package structure
- [x] Configure framework properties
- [x] Implement `BrowserType`
- [x] Implement `DriverFactory`
- [x] Implement `DriverManager`
- [x] Implement `BaseTest`
- [ ] Implement `BasePage`

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
- [ ] Headless execution
- [ ] Parallel execution
- [ ] Multiple environment configuration
- [ ] Test reports

### DevOps

- [ ] GitHub Actions integration
- [ ] Automated test execution through CI
- [ ] Cross-browser execution in CI
- [ ] Publish test reports as pipeline artifacts

### AI-Assisted Engineering

- [x] Use AI to support framework architecture discussions
- [x] Use AI to support technical documentation
- [x] Use AI for implementation reviews and learning
- [ ] Explore AI-assisted test scenario generation
- [ ] Explore AI-assisted failure analysis
- [ ] Explore AI-assisted test maintenance approaches

---

## ✅ Automation Principles

Some principles adopted in this project:

- Tests should be independent
- Tests should be readable
- Tests should focus on behavior rather than Selenium implementation details
- Page Objects should contain page behavior, not test assertions
- Reusable components should not be duplicated across pages
- Test data should be separated from test logic when appropriate
- Hard-coded configuration values should be avoided
- Explicit waits should be preferred over fixed sleeps
- Browser lifecycle management should be centralized
- Framework complexity should be introduced only when there is a real need
- AI-generated suggestions should be reviewed and validated before adoption

---

## 📚 Learning Approach

This project is also used as a practical learning environment.

Instead of building the entire framework upfront, features are introduced incrementally as real automation needs arise.

This approach makes it possible to understand not only **how** each framework component works, but also **why** it exists and which problem it solves.

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
