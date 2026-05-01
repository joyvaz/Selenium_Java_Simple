# 🚀 Selenium Java BDD Framework

## 📋 Overview

The Selenium Java BDD Framework is designed for test automation. It uses Java, Selenium, Cucumber (BDD), and TestNG to make testing easier and more effective. This framework supports parallel execution, which means you can run tests at the same time, saving you valuable time. 

Other features include:

- **Page Object Model Design:** This makes it simple to manage your tests.
- **Allure Reporting:** Get clear reports to understand test results.
- **Thread Safety:** Run multiple tests safely at once.

## 🚀 Getting Started

To begin using the Selenium-Java-Framework, follow the instructions below. You will be able to download and run the software quickly.

### 1. System Requirements

Before you download the framework, ensure your system meets the following requirements:

- **Operating System:** Windows, macOS, or Linux
- **Java Version:** JDK 8 or higher
- **Disk Space:** At least 100MB free
- **Memory:** Minimum 2GB RAM

### 4. Setting Up Your Environment

To run the framework, make sure you have Java installed:

1. Clone the Repo
2. Navigate to folder aand run mvn install

### 5. Running Your First Test

1. Navigate to the folder where you extracted the framework files.
2. Locate the example test files to understand how it works.
3. Open the Command Prompt or Terminal, and run your tests by executing:
   ```
   mvn clean test
   ```
   This command runs all tests in the project.

### 6. View Test Results

After running your tests, you can check the results. Allure will create a detailed report. To view it:

1. Navigate to the `target/allure-results` directory.
2. Open a terminal or command prompt there and run the command:
   ```
   allure serve
   ```
   This will open a browser showing your test results.

## 🔧 Features

- **Easy Setup:** Simple download and setup procedures.
- **Parallel Execution:** Run multiple tests simultaneously.
- **Detailed Reporting:** Clear insights using Allure.
- **Modular Design:** Easily add or modify test cases.

## 🛠️ Troubleshooting

If you encounter issues while using the framework, here are common fixes:

- **Java Not Recognized:** Ensure Java is installed correctly and added to system PATH.
- **Tests Not Running:** Check if Maven is installed and working.
- **Report Not Generating:** Ensure Allure dependencies are properly added.
