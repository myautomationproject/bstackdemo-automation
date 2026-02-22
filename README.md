# Automation Testing Framework for bstackdemo.com

## 📌 Project Overview
This project is an automation testing framework developed for the e-commerce application **bstackdemo.com**.  
The framework is built using Java, Selenium WebDriver, TestNG, and Maven following the Page Object Model (POM) design pattern.

---

## 🎯 Project Objective
To design and implement a modular and scalable automation framework to validate the core functionalities of the bstackdemo e-commerce platform including login, product management, cart operations, and checkout flow.

---

## 🛠 Tools & Technologies Used
- Java
- Selenium WebDriver
- TestNG
- Maven
- ExtentReports
- Git & GitHub

---

## 🏗 Framework Architecture
The framework follows the **Page Object Model (POM)** design pattern.

### Key Components:
- **BaseTest.java** – WebDriver setup and teardown
- **Page Classes** – LoginPage, ProductPage, CartPage, CheckoutPage
- **Utilities** – WebDriverFactory, WaitUtils, ConfigReader
- **Test Classes** – LoginTest, AddToCartTest, CheckoutTest, etc.
- **Listener** – ExtentTestNGListener for report generation
- **testng.xml** – Suite execution configuration

---

## ✅ Test Scenarios Covered

### Login Module
- TC_001: Login with valid credentials
- TC_002: Login with invalid credentials
- TC_003: Login with empty credentials

### Cart Module
- TC_004: Add single item to cart
- TC_005: Add multiple items to cart
- TC_006: Remove item from cart

### Checkout Module
- TC_007: Successful order placement
- TC_008: Checkout without adding items (Negative test)

---

## ▶ How To Run The Project

### Using TestNG Suite
1. Open `testng.xml`
2. Right click → Run As → TestNG Suite

### Using Maven
Run the following command from the project root folder:
mvn clean test


---

## 📊 Reports

- Extent Report is generated under:
reports/ExtentReport_*.html


- TestNG default reports are generated under:
test-output/


---

## 📂 Project Structure
src/
├── main/java
│ ├── pages
│ └── utils
├── test/java
│ ├── tests
│ └── listeners
└── test/resources
└── testng.xml
pom.xml
README.md

---

## 🚀 Conclusion
This framework successfully automates key functionalities of the bstackdemo application using industry-standard tools and best practices. The framework is modular, maintainable, and scalable for future enhancements.



