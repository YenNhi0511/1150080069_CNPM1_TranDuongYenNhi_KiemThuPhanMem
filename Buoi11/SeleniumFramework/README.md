# 🧪 Selenium POM + Data-Driven Framework

[![Selenium CI Tests](../../actions/workflows/selenium-ci.yml/badge.svg)](../../actions/workflows/selenium-ci.yml)
[![Selenium Full Pipeline](../../actions/workflows/selenium-full.yml/badge.svg)](../../actions/workflows/selenium-full.yml)

## 📋 Mô tả

Framework kiểm thử tự động cho [SauceDemo](https://www.saucedemo.com/) sử dụng:
- **Selenium 4** + **TestNG 7** + **Maven**
- **Page Object Model (POM)** pattern
- **Data-Driven Testing** (Excel, JSON, Java Faker)
- **CI/CD** với GitHub Actions
- **Selenium Grid 4** với Docker
- **Allure Report** cho test reporting

## 🏗️ Cấu trúc Project

```
SeleniumFramework/
├── src/main/java/framework/
│   ├── base/          # BasePage, BaseTest
│   ├── config/        # ConfigReader
│   ├── driver/        # DriverFactory
│   ├── pages/         # Page Objects (Login, Inventory, Cart, Checkout)
│   └── utils/         # ExcelReader, JsonReader, ScreenshotUtil, RetryAnalyzer
├── src/test/
│   ├── java/tests/    # Test classes
│   └── resources/     # Config files, test data, TestNG suites
├── .github/workflows/ # CI/CD pipelines
├── docker-compose.yml # Selenium Grid
└── docs/              # Test Strategy & Test Plan
```

## 🚀 Hướng dẫn chạy

### Yêu cầu
- Java JDK 17+
- Maven 3.8+
- Chrome hoặc Firefox browser
- Docker Desktop (cho Selenium Grid)

### 1. Sinh file test data Excel
```bash
mvn compile exec:java -Dexec.mainClass="tests.TestDataGenerator" -Dexec.classpathScope=test
```

### 2. Chạy toàn bộ test
```bash
mvn clean test
```

### 3. Chạy theo browser
```bash
mvn clean test -Dbrowser=chrome
mvn clean test -Dbrowser=firefox
```

### 4. Chạy Smoke tests
```bash
mvn clean test -DsuiteXmlFile=src/test/resources/testng-smoke.xml
```

### 5. Chạy với Selenium Grid
```bash
# Khởi động Grid
docker-compose up -d

# Chạy test qua Grid
mvn clean test -Dgrid.url=http://localhost:4444

# Dừng Grid
docker-compose down
```

### 6. Xem Allure Report
```bash
mvn allure:serve
```

## 🔐 GitHub Secrets (cho CI/CD)

Thêm vào Repository Settings → Secrets:
- `SAUCEDEMO_USERNAME`: `standard_user`
- `SAUCEDEMO_PASSWORD`: `secret_sauce`

## 📊 Allure Report trên GitHub Pages

Sau khi pipeline chạy xong, xem report tại:
`https://<username>.github.io/<repo-name>/`

## 📄 Documentation

- [Test Strategy](docs/TestStrategy.md)
- [Test Plan - Sprint 5](docs/TestPlan.md)
