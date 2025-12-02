# Selenium WebDriver with Java

A comprehensive Selenium WebDriver test automation framework built with Java, JUnit 5, and modern testing tools. This project demonstrates advanced web automation techniques, browser testing patterns, and integration with various testing frameworks and reporting tools.

[![Java](https://img.shields.io/badge/Java-17+-orange.svg)](https://www.oracle.com/java/)
[![Selenium](https://img.shields.io/badge/Selenium-4.38.0-green.svg)](https://www.selenium.dev/)
[![JUnit](https://img.shields.io/badge/JUnit-6.0.1-blue.svg)](https://junit.org/junit5/)
[![Maven](https://img.shields.io/badge/Maven-Build-red.svg)](https://maven.apache.org/)
[![Gradle](https://img.shields.io/badge/Gradle-Build-blue.svg)](https://gradle.org/)

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Project Structure](#project-structure)
- [Running Tests](#running-tests)
- [Test Categories](#test-categories)
- [Reporting](#reporting)
- [Advanced Features](#advanced-features)
- [Browser Support](#browser-support)
- [Configuration](#configuration)
- [CI/CD Integration](#cicd-integration)
- [Contributing](#contributing)
- [License](#license)

## 🎯 Overview

This repository contains a production-ready Selenium WebDriver test automation framework that covers:

- **Web UI Testing**: Comprehensive browser automation tests
- **Cross-Browser Testing**: Chrome, Firefox, Edge, Safari support
- **Advanced Selenium Features**: CDP (Chrome DevTools Protocol), WebAuthn, Network monitoring
- **Modern Testing Patterns**: Page Object Model, Fluent APIs, BDD with Cucumber
- **Cloud & Remote Testing**: Selenium Grid, Docker, Cloud providers integration
- **Mobile Testing**: Appium integration for mobile automation
- **API Testing**: REST-assured integration
- **Multiple Reporting**: Allure, Extent Reports, HTML reports

## ✨ Features

### Core Testing Capabilities

- ✅ Multi-browser support (Chrome, Firefox, Edge, Safari)
- ✅ Headless browser execution
- ✅ Parallel test execution
- ✅ Responsive design testing
- ✅ Mobile emulation & device testing
- ✅ Screenshot & video capture
- ✅ Network traffic monitoring
- ✅ Performance metrics collection

### Advanced Selenium Features

- 🔧 Chrome DevTools Protocol (CDP) integration
- 🔧 Network interception & modification
- 🔧 Geolocation mocking
- 🔧 Device emulation
- 🔧 Basic & Digest authentication
- 🔧 Cookie management
- 🔧 Console log capture
- 🔧 WebAuthn/FIDO2 testing
- 🔧 Shadow DOM handling

### Testing Frameworks & Patterns

- 📐 Page Object Model (POM)
- 📐 Page Factory pattern
- 📐 Fluent page objects
- 📐 BDD with Cucumber
- 📐 Data-driven testing
- 📐 Parameterized tests
- 📐 Test retry mechanisms
- 📐 Test categorization/tagging

### Remote & Cloud Testing

- ☁️ Selenium Grid 4 support
- ☁️ Docker Selenium containers
- ☁️ Cloud provider integration (BrowserStack, Sauce Labs, etc.)
- ☁️ Selenoid integration

### Reporting & Analytics

- 📊 Allure Reports
- 📊 Extent Reports
- 📊 HTML test reports
- 📊 JSON test results
- 📊 Screenshot on failure
- 📊 Test execution metrics

## 🛠️ Technology Stack

### Core Technologies

| Technology         | Version | Purpose                |
| ------------------ | ------- | ---------------------- |
| Java               | 17+     | Programming language   |
| Selenium WebDriver | 4.38.0  | Browser automation     |
| JUnit 5            | 6.0.1   | Test framework         |
| Maven              | 3.5+    | Build tool             |
| Gradle             | 7.0+    | Alternative build tool |

### Testing Libraries

| Library          | Version | Purpose                   |
| ---------------- | ------- | ------------------------- |
| AssertJ          | 3.27.6  | Fluent assertions         |
| WebDriverManager | 6.3.3   | Browser driver management |
| Selenide         | 7.12.2  | Fluent Selenium API       |
| Awaitility       | 4.3.0   | Async wait conditions     |
| DataFaker        | 2.5.3   | Test data generation      |

### Reporting & Analysis

| Tool             | Version | Purpose               |
| ---------------- | ------- | --------------------- |
| Allure           | 2.31.0  | Rich test reports     |
| ExtentReports    | 5.1.2   | HTML test reports     |
| BrowserMob Proxy | 2.1.5   | Network monitoring    |
| Axe Core         | 4.11.0  | Accessibility testing |

### Integration Frameworks

| Framework    | Version | Purpose              |
| ------------ | ------- | -------------------- |
| Cucumber     | 7.32.0  | BDD testing          |
| Spring Boot  | 4.0.0   | Dependency injection |
| Appium       | 10.0.0  | Mobile testing       |
| REST-assured | 5.5.6   | API testing          |

## 📦 Prerequisites

Before running this project, ensure you have:

- **Java Development Kit (JDK)**: Version 17 or higher

  ```bash
  java -version
  ```

- **Maven**: Version 3.5+ (for Maven builds)

  ```bash
  mvn -version
  ```

- **Gradle**: Version 7.0+ (for Gradle builds)

  ```bash
  gradle -version
  ```

- **Browsers**: Chrome, Firefox, Edge, or Safari installed

  - Drivers are managed automatically via WebDriverManager

- **Docker** (Optional): For containerized test execution
  ```bash
  docker -version
  ```

## 🚀 Installation

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/selenium-webdriver-java.git
cd selenium-webdriver-java
```

### 2. Using Maven

```bash
# Install dependencies
mvn clean install -DskipTests

# Verify installation
mvn test -Dtest=HelloWorldChromeJupiterTest
```

### 3. Using Gradle

```bash
# Install dependencies
gradle clean build -x test

# Verify installation
gradle test --tests HelloWorldChromeJupiterTest
```

## 📁 Project Structure

```
selenium-webdriver-java/
├── src/
│   ├── main/
│   │   ├── java/              # Main source code (utilities, helpers)
│   │   └── resources/          # Configuration files
│   └── test/
│       ├── java/io/github/bonigarcia/webdriver/jupiter/
│       │   ├── ch02/           # Chapter 2: Getting Started
│       │   │   ├── helloworld/                    # Basic WebDriver tests
│       │   │   ├── helloworld_otherbrowsers/      # Multi-browser examples
│       │   │   └── helloworld_selenium_manager/   # Selenium Manager usage
│       │   ├── ch03/           # Chapter 3: Locators & Interactions
│       │   │   ├── basic/                         # Basic element interactions
│       │   │   ├── keyboard/                      # Keyboard actions
│       │   │   ├── mouse/                         # Mouse actions
│       │   │   ├── locators/                      # Element location strategies
│       │   │   ├── locators_compound/             # Complex locators
│       │   │   ├── locators_relative/             # Relative locators
│       │   │   ├── user_gestures/                 # Drag & drop, context menu
│       │   │   └── wait/                          # Wait strategies
│       │   ├── ch04/           # Chapter 4: Browser Management
│       │   │   ├── javascript/                    # JavaScript execution
│       │   │   ├── screenshots/                   # Screenshot capture
│       │   │   ├── window/                        # Window handling
│       │   │   ├── history/                       # Browser history
│       │   │   └── shadow_dom/                    # Shadow DOM handling
│       │   ├── ch05/           # Chapter 5: Advanced Features
│       │   │   ├── cdp/                           # Chrome DevTools Protocol
│       │   │   │   ├── basic_auth/                # Authentication
│       │   │   │   └── network_interceptor/       # Network interception
│       │   │   ├── caps/                          # Browser capabilities
│       │   │   │   ├── device_emulation/          # Mobile emulation
│       │   │   │   ├── geolocation/               # Location mocking
│       │   │   │   └── usermedia/                 # Media permissions
│       │   │   ├── print/                         # Print to PDF
│       │   │   └── webauthn/                      # WebAuthn testing
│       │   ├── ch06/           # Chapter 6: Remote WebDriver
│       │   │   ├── remote/                        # Remote driver setup
│       │   │   ├── grid/                          # Selenium Grid
│       │   │   ├── docker/                        # Docker integration
│       │   │   ├── cloud_providers/               # Cloud testing
│       │   │   └── selenoid/                      # Selenoid setup
│       │   ├── ch07/           # Chapter 7: Page Object Model
│       │   │   └── page_objects/                  # POM implementations
│       │   ├── ch08/           # Chapter 8: Test Framework Features
│       │   │   ├── parameterized/                 # Parameterized tests
│       │   │   ├── parallel/                      # Parallel execution
│       │   │   ├── retry/                         # Test retry logic
│       │   │   ├── listeners/                     # Test listeners
│       │   │   ├── categories/                    # Test categorization
│       │   │   ├── order/                         # Test ordering
│       │   │   ├── disabled/                      # Conditional tests
│       │   │   ├── cross_browser/                 # Cross-browser tests
│       │   │   └── failure_analysis/              # Failure handling
│       │   ├── ch09/           # Chapter 9: Integration Testing
│       │   │   ├── cucumber/                      # BDD with Cucumber
│       │   │   ├── spring/                        # Spring Boot integration
│       │   │   ├── download/                      # File downloads
│       │   │   └── ab_testing/                    # A/B testing
│       │   └── ch10/           # Chapter 10: Advanced Topics
│       │       └── rest/                          # REST API testing
│       └── resources/
│           ├── cucumber/                          # Cucumber feature files
│           └── logback-test.xml                   # Logging configuration
├── build/
│   ├── allure-results/         # Allure test results
│   ├── reports/                # Test reports
│   └── test-results/           # JUnit test results
├── target/
│   └── allure-results/         # Maven Allure results
├── pom.xml                     # Maven build configuration
├── build.gradle                # Gradle build configuration
├── gradlew                     # Gradle wrapper (Unix)
├── gradlew.bat                 # Gradle wrapper (Windows)
└── README.md                   # This file
```

## 🧪 Running Tests

### Maven Commands

#### Run All Tests

```bash
mvn test
```

#### Run Specific Test Class

```bash
mvn test -Dtest=HelloWorldChromeJupiterTest
```

#### Run Tests with Tags/Groups

```bash
# Run only smoke tests
mvn test -Dgroups=smoke

# Exclude slow tests
mvn test -DexcludedGroups=slow
```

#### Run Tests in Parallel

```bash
mvn test -Dparallel=methods -DthreadCount=4
```

#### Run Tests with Specific Browser

```bash
mvn test -Dbrowser=firefox
mvn test -Dbrowser=edge
```

#### Generate Allure Report

```bash
mvn allure:serve
# Or generate and open
mvn allure:report
```

### Gradle Commands

#### Run All Tests

```bash
gradle test
```

#### Run Specific Test Class

```bash
gradle test --tests HelloWorldChromeJupiterTest
```

#### Run Tests with Tags/Groups

```bash
# Run only smoke tests
gradle test -Pgroups=smoke

# Exclude slow tests
gradle test -PexcludedGroups=slow
```

#### Run Tests in Parallel

```bash
gradle test -Pparallel
```

#### Generate Allure Report

```bash
gradle allureServe
# Or generate report
gradle allureReport
```

#### Run with Specific Test Pattern

```bash
# Exclude specific tests
gradle test -PexcludeTests="**/Slow*Test.class"
```

### Headless Execution

```bash
# Maven
mvn test -Dheadless=true

# Gradle
gradle test -Pheadless=true
```

### Remote Execution (Selenium Grid)

```bash
# Set remote URL
mvn test -DremoteUrl=http://localhost:4444
gradle test -PremoteUrl=http://localhost:4444
```

## 📚 Test Categories

### Chapter 2: Getting Started

- **Hello World Tests**: Basic WebDriver setup and teardown
- **Multi-Browser**: Chrome, Firefox, Edge, Safari examples
- **Selenium Manager**: Automatic driver management

### Chapter 3: Web Elements & Interactions

- **Basic Locators**: ID, Name, Class, Tag, CSS, XPath
- **Compound Locators**: Chaining multiple locators
- **Relative Locators**: above(), below(), toLeftOf(), toRightOf(), near()
- **Keyboard Actions**: Key press, combinations, shortcuts
- **Mouse Actions**: Click, double-click, context menu, hover
- **User Gestures**: Drag & drop, copy & paste
- **Wait Strategies**: Implicit, explicit, fluent waits

### Chapter 4: Browser Management

- **JavaScript Execution**: Synchronous & asynchronous JS
- **Screenshots**: Full page, element, base64 encoding
- **Window Management**: Resize, maximize, fullscreen, new tabs
- **Browser History**: Forward, back, refresh
- **Shadow DOM**: Accessing shadow root elements
- **Scrolling**: Scroll by pixels, scroll into view, infinite scroll

### Chapter 5: Advanced Browser Features

- **Chrome DevTools Protocol (CDP)**:
  - Network monitoring & interception
  - Performance metrics
  - Console log capture
  - Cookie management
  - Geolocation override
  - Device emulation
  - Network condition emulation
  - Basic & Digest authentication
  - Block URLs
  - Extra headers injection
  - Full-page screenshots
- **Print to PDF**: Chrome, Firefox, Edge
- **WebAuthn**: Virtual authenticator testing
- **Browser Capabilities**: Geolocation, media permissions

### Chapter 6: Remote WebDriver

- **Remote WebDriver**: Connect to remote browsers
- **Selenium Grid 4**: Hub and Node setup
- **Docker Selenium**: Containerized browser testing
- **Cloud Providers**: BrowserStack, Sauce Labs, LambdaTest
- **Selenoid**: Lightweight Selenium hub

### Chapter 7: Page Object Model

- **Basic Page Objects**: Encapsulation of page logic
- **Page Factory**: @FindBy annotations
- **Extended Page Objects**: Base page pattern
- **Fluent Page Objects**: Method chaining
- **Factory Pattern**: Dynamic page creation

### Chapter 8: JUnit 5 Features

- **Parameterized Tests**: @ParameterizedTest, @ValueSource, @CsvSource
- **Parallel Execution**: Test & class-level parallelization
- **Test Retry**: @RepeatedTest, custom retry logic
- **Test Listeners**: BeforeEach, AfterEach, extensions
- **Test Categorization**: @Tag for test grouping
- **Test Ordering**: @TestMethodOrder, @Order
- **Conditional Tests**: @EnabledOnOs, @DisabledIf
- **Cross-Browser Tests**: Parameterized browser testing
- **Failure Analysis**: Screenshot on failure, error reporting

### Chapter 9: Third-Party Integrations

- **Cucumber BDD**: Gherkin feature files, step definitions
- **Spring Boot**: Dependency injection, configuration
- **File Downloads**: Browser downloads, HTTP client
- **A/B Testing**: Testing multiple variants
- **Selenide**: Fluent API alternative
- **Accessibility Testing**: Axe-core integration

### Chapter 10: Advanced Topics

- **REST API Testing**: REST-assured integration
- **Mobile Testing**: Appium setup (foundation)
- **Performance Testing**: Metrics collection
- **Security Testing**: OWASP ZAP integration (foundation)

## 📊 Reporting

### Allure Reports

Generate rich, interactive HTML reports with charts, graphs, and detailed test execution data.

```bash
# Maven
mvn clean test
mvn allure:serve

# Gradle
gradle clean test
gradle allureServe
```

**Features**:

- Test execution trends
- Test duration statistics
- Failed test details with screenshots
- Test history tracking
- Categorization and tagging
- Attachments (logs, screenshots, HAR files)

### Extent Reports

```bash
# ExtentReports HTML file is generated automatically
# Location: ./extentReport.html
```

**Features**:

- Modern responsive HTML reports
- Test execution timeline
- Pass/Fail statistics
- Screenshot embedding
- System information

### Standard Reports

```bash
# Maven Surefire reports
# Location: target/surefire-reports/

# Gradle test reports
# Location: build/reports/tests/test/index.html
```

## 🔧 Advanced Features

### Network Traffic Monitoring

```java
// Using BrowserMob Proxy
// Capture HAR files for network analysis
// Monitor request/response headers
// Block specific URLs
```

### Performance Metrics

```java
// Using CDP Performance APIs
// Collect page load times
// Measure resource loading
// Analyze runtime performance
```

### Security Testing

```java
// Using OWASP ZAP Client
// Automated security scanning
// Vulnerability detection
// SSL/TLS testing
```

### Accessibility Testing

```java
// Using Axe-core
// WCAG 2.1 compliance testing
// Accessibility violation detection
// Generate accessibility reports
```

### Visual Regression Testing

```java
// Screenshot comparison
// Pixel-by-pixel comparison
// Visual diff reporting
```

## 🌐 Browser Support

| Browser  | Version | Headless | Remote | Notes            |
| -------- | ------- | -------- | ------ | ---------------- |
| Chrome   | 90+     | ✅       | ✅     | Full CDP support |
| Firefox  | 88+     | ✅       | ✅     | Geckodriver      |
| Edge     | 90+     | ✅       | ✅     | Chromium-based   |
| Safari   | 14+     | ❌       | ✅     | macOS/iOS only   |
| HtmlUnit | Latest  | ✅       | ✅     | Headless browser |

### Browser Configuration

#### Chrome Options

```java
ChromeOptions options = new ChromeOptions();
options.addArguments("--headless");
options.addArguments("--disable-gpu");
options.addArguments("--no-sandbox");
options.addArguments("--disable-dev-shm-usage");
options.addArguments("--window-size=1920,1080");
```

#### Firefox Options

```java
FirefoxOptions options = new FirefoxOptions();
options.addArguments("-headless");
options.setLogLevel(FirefoxDriverLogLevel.INFO);
```

#### Edge Options

```java
EdgeOptions options = new EdgeOptions();
options.addArguments("--headless");
options.addArguments("--disable-gpu");
```

## ⚙️ Configuration

### Logging Configuration

Edit `src/test/resources/logback-test.xml`:

```xml
<configuration>
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>
    <root level="INFO">
        <appender-ref ref="STDOUT" />
    </root>
</configuration>
```

### WebDriverManager Configuration

```java
// Automatic driver management
WebDriverManager.chromedriver().setup();

// Force latest version
WebDriverManager.chromedriver().clearDriverCache().setup();

// Use specific version
WebDriverManager.chromedriver().driverVersion("120.0.6099.109").setup();
```

### System Properties

```bash
# Browser selection
-Dbrowser=chrome|firefox|edge|safari

# Headless mode
-Dheadless=true

# Remote URL
-DremoteUrl=http://localhost:4444

# Screenshot on failure
-Dscreenshot=true

# Wait timeout
-Dtimeout=10
```

## 🔄 CI/CD Integration

### GitHub Actions

```yaml
name: Selenium Tests

on: [push, pull_request]

jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - name: Set up JDK 17
        uses: actions/setup-java@v3
        with:
          java-version: "17"
          distribution: "temurin"
      - name: Run tests with Maven
        run: mvn clean test
      - name: Generate Allure Report
        if: always()
        run: mvn allure:report
      - name: Upload Allure Results
        if: always()
        uses: actions/upload-artifact@v3
        with:
          name: allure-results
          path: target/allure-results
```

### Jenkins Pipeline

```groovy
pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Test') {
            steps {
                sh 'mvn clean test'
            }
        }
        stage('Report') {
            steps {
                allure includeProperties: false,
                       jdk: '',
                       results: [[path: 'target/allure-results']]
            }
        }
    }
    post {
        always {
            junit 'target/surefire-reports/*.xml'
            archiveArtifacts artifacts: 'target/screenshots/*.png',
                           allowEmptyArchive: true
        }
    }
}
```

### GitLab CI

```yaml
test:
  image: maven:3.8-openjdk-17
  script:
    - mvn clean test
  artifacts:
    when: always
    paths:
      - target/surefire-reports/
      - target/allure-results/
    reports:
      junit: target/surefire-reports/TEST-*.xml
```

## 🐳 Docker Support

### Run Tests in Docker

```bash
# Build Docker image
docker build -t selenium-tests .

# Run tests
docker run --rm selenium-tests

# Run with Selenium Grid
docker-compose up -d
mvn test -DremoteUrl=http://localhost:4444
docker-compose down
```

### Docker Compose Example

```yaml
version: "3"
services:
  selenium-hub:
    image: selenium/hub:latest
    ports:
      - "4444:4444"

  chrome:
    image: selenium/node-chrome:latest
    depends_on:
      - selenium-hub
    environment:
      - SE_EVENT_BUS_HOST=selenium-hub
      - SE_EVENT_BUS_PUBLISH_PORT=4442
      - SE_EVENT_BUS_SUBSCRIBE_PORT=4443

  firefox:
    image: selenium/node-firefox:latest
    depends_on:
      - selenium-hub
    environment:
      - SE_EVENT_BUS_HOST=selenium-hub
      - SE_EVENT_BUS_PUBLISH_PORT=4442
      - SE_EVENT_BUS_SUBSCRIBE_PORT=4443
```

## 🤝 Contributing

Contributions are welcome! Please follow these guidelines:

1. **Fork the repository**
2. **Create a feature branch**: `git checkout -b feature/your-feature`
3. **Commit your changes**: `git commit -am 'Add new feature'`
4. **Push to the branch**: `git push origin feature/your-feature`
5. **Create a Pull Request**

### Coding Standards

- Follow Java coding conventions
- Use meaningful variable and method names
- Add JavaDoc comments for public methods
- Write unit tests for new features
- Ensure all tests pass before submitting PR

## 📖 Documentation & Resources

### Official Documentation

- [Selenium WebDriver](https://www.selenium.dev/documentation/)
- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
- [WebDriverManager](https://github.com/bonigarcia/webdrivermanager)
- [Allure Framework](https://docs.qameta.io/allure/)

### Useful Links

- [Selenium GitHub](https://github.com/SeleniumHQ/selenium)
- [Selenium Blog](https://www.selenium.dev/blog/)
- [Stack Overflow - Selenium Tag](https://stackoverflow.com/questions/tagged/selenium)

## 📝 License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details.

## 👥 Authors & Acknowledgments

- **Repository Owner**: [Your Name]
- **Framework Base**: Based on Selenium WebDriver examples and patterns
- **Community**: Thanks to all contributors and the Selenium community

## 🐛 Troubleshooting

### Common Issues

#### Driver Not Found

```bash
# Solution: WebDriverManager handles this automatically
# If issues persist, clear driver cache:
rm -rf ~/.cache/selenium/
```

#### Tests Failing in Headless Mode

```bash
# Add window size to prevent element visibility issues:
--window-size=1920,1080
--start-maximized
```

#### Timeout Exceptions

```java
// Increase implicit wait time
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

// Use explicit waits
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("element")));
```

#### ChromeDriver Version Mismatch

```bash
# WebDriverManager automatically matches versions
# Force update: delete cached driver
WebDriverManager.chromedriver().clearDriverCache().setup();
```

## 📞 Support

For questions, issues, or suggestions:

- **Issues**: [GitHub Issues](https://github.com/yourusername/selenium-webdriver-java/issues)
- **Discussions**: [GitHub Discussions](https://github.com/yourusername/selenium-webdriver-java/discussions)
- **Email**: your.email@example.com

---

**Happy Testing! 🚀**
