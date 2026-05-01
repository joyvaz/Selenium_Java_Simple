
# 🚀 Selenium Java BDD Framework

## 📋 Overview

This project is a robust Selenium Java BDD (Behavior Driven Development) automation framework. It leverages Java, Selenium WebDriver, Cucumber, TestNG, and Allure for comprehensive test automation and reporting. The framework is designed for maintainability, scalability, and parallel execution.

## 🗂️ Project Structure

```
src/
   main/
      java/
         runners/           # Test runners (RunTest.java, ReRunTest.java)
         utils/             # Utilities and hooks
   test/
      java/
         pages/             # Page Object Model classes
            login/           # Login page objects and locators
         stepdefinitions/   # Step definition classes for Cucumber
      resources/
         features/          # Feature files (e.g., Login.feature)
reports/                # Allure and Cucumber reports
target/                 # Build output
pom.xml                 # Maven configuration
testng-execution.xml    # TestNG suite configuration
```

## 🚀 Getting Started

### 1. Prerequisites

- **Java:** JDK 21 (or compatible with your Maven compiler settings)
- **Maven:** 3.6+
- **Allure CLI:** For report generation (`npm install -g allure-commandline` or [see Allure docs](https://docs.qameta.io/allure/))

### 2. Installation

Clone the repository and install dependencies:

```sh
git clone <repo-url>
cd Selenium_Java_Simple
mvn clean install
```

### 3. Configuration

- Update test data and environment variables in `src/main/resources` or as required by your project.
- Feature files are located in `src/test/resources/features`.
- Page objects and step definitions are in `src/test/java/pages` and `src/test/java/stepdefinitions`.

### 4. Running Tests

To execute all tests using Maven and TestNG:

```sh
mvn clean test
```

Or run a specific suite:

```sh
mvn test -DsuiteXmlFile=testng-execution.xml
```

### 5. Viewing Reports

**Allure Report:**

After test execution, generate and view the Allure report:

```sh
allure serve reports/allure-results
```

**Cucumber HTML Report:**

Check the `reports/cucumber-html-reports/` directory for HTML reports.

## 🔧 Features

- **Page Object Model:** Clean separation of test logic and UI structure
- **Cucumber BDD:** Write tests in Gherkin syntax
- **TestNG Integration:** Flexible test execution and configuration
- **Parallel Execution:** Configurable via Maven Surefire plugin
- **Allure Reporting:** Rich, interactive test reports
- **Retry Mechanism:** Re-run failed tests (see `ReRunTest.java` and TestNG XML)

## 🛠️ Troubleshooting

- **Java Not Recognized:** Ensure Java is installed and `JAVA_HOME` is set
- **Maven Not Found:** Install Maven and add it to your PATH
- **Allure Not Found:** Install Allure CLI globally
- **WebDriver Issues:** Ensure browser drivers are compatible and available
- **Tests Not Running:** Check Maven dependencies and feature file paths

## 📄 License

This project uses Selenium and other open-source libraries. See their respective licenses for details.
