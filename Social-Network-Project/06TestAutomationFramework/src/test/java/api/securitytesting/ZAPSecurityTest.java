package api.securitytesting;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;
import org.zaproxy.clientapi.core.ApiResponse;
import org.zaproxy.clientapi.core.ClientApi;
import org.zaproxy.clientapi.core.ClientApiException;

public class ZAPSecurityTest {
    static final String ZAP_PROXY_ADDRESS = "localhost";
    static final int ZAP_PROXY_PORT = 8080;
    static final String ZAP_API_KEY = "ivn8s36c2f29v9g490q03vh4hi";
    private WebDriver driver;
    private ClientApi api;

    @BeforeMethod
    public void setup() {

        String proxyServerUrl = ZAP_PROXY_ADDRESS + ":" + ZAP_PROXY_PORT;
        Proxy proxy = new Proxy();
        proxy.setHttpProxy(proxyServerUrl);
        proxy.setSslProxy(proxyServerUrl);

        ChromeOptions co = new ChromeOptions();
        co.setAcceptInsecureCerts(true);
        co.setProxy(proxy);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(co);
        api = new ClientApi(ZAP_PROXY_ADDRESS, ZAP_PROXY_PORT, ZAP_API_KEY);
    }

    @Test
    public void weAreSecurityTest() {
        driver.get("http://localhost:8081/");
        Assert.assertTrue(driver.getTitle().contains("WE are social media"));

    }

    @AfterMethod
    public void tearDown() {

        if (api != null) {

            String title = "WeAre ZAP Security Test Report";
            String template = "traditional-html";
            String description = "This is WeAre Social Network zap security test report";
            String reportFilename = "weare-zap-report.html";
            String targetFolder = System.getProperty("user.dir");

            try {
                ApiResponse response = api.reports.generate(title, template, null, description, null, null, null, null, null,
                        reportFilename, null, targetFolder, null);
                System.out.println("ZAP report generated at this location:" + response.toString());
            } catch (ClientApiException e) {
                e.printStackTrace();
            }
        }
        driver.quit();
    }

}
