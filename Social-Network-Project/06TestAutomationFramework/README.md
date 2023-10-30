# IntelliJ README for Test Automation Framework

## Instructions

**1. Clone the Repository**
- Clone this repository to your local machine.

**2. Open the IntelliJ IDEA Project**
- Open the project located in the `TestAutomationFramework[test-automation-framework]` directory using IntelliJ IDEA.

**3. Build the Project**
- Build the project with `JDK 11` and set up the required dependencies.

## Running Instructions for API Tests

**Preconditions:**
- Ensure that the Database server and the WEAre project are running.

**1. Location of API Tests**
- API Tests are located under the `api.socialnetwork.tests` package.

**2. Running API Tests Suite**
- To run the API test suite, right-click on the `api-testsuite.xml` file and select "Run."

**3. Generating HTML Report**
- An HTML report named `emailable-report.html` will be generated under the `test-output` directory.
- Latest report is `emailable-report.html`.

## Running Instructions for Selenium UI Tests

**Preconditions:**
- Ensure that the Database server and the WEAre project are running.

**1. Location of UI Tests**
- UI Tests are located under the `ui.socialnetwork.tests` package.

**2. Running UI Tests Suite**
- Select step 2 or step 3. To run the UI test suite, navigate to your cloned repository on your local machine. Or directly run it in the
- project's command with right-clicking on `seleniumRunBat.bat` file.

**3. Double-Click to Run Tests**
- Execute the tests by double-clicking on the `seleniumRunBat.bat` file.

**4. Generating Report**
- The test report will be generated in the `T-H-K\TestAutomationFramework\target\site\surefire-report.html` file.
- Latest report is `ui-selenium-report-surefire-report.html`

## Running Instructions for API Security Test

**Preconditions:**
- Ensure that the Database server and the WEAre project are running.
- ZAP (OWASP Zed Attack Proxy) must be installed on your local machine and started with an active session (choose "No" on the pop-up dialog).
- Update the API key in the `ZAPSecurityTest` class with the key from your ZAP session.

**1. Running API Security Test**
- Right-click on the `ZAPSecurityTest` class and select "Run."

**2. Generating HTML Report**
- An HTML report, named `weare-zap-report.html`, will be generated in your project.

**3. Viewing the Report**
- Right-click on the generated report to view the results.

