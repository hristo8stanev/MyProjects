# MyProjects

1.The folder 'BrowserType' contains various types of browsers: FIREFOX, CHROME, EDGE..
2.The folder 'Constant' contains all constants required for the test.
3.I have 2 folders: 'bingsearchtests' and 'googlesearchtest', which contain all automation tests.
4.To perform tests on various browsers, you can modify the "browserType" and
 select the appropriate enum value for the desired browser.
 @BeforeAll
    public static void classSetup() {

        driver = startBrowser(BrowserType.FIREFOX);   <------- Here!