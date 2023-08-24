# MyProjects

1.I have 2 folders: 'bingsearchtests' and 'googlesearchtest'.
2.In the 'bingsearchtests' folder, I have 2 classes: one with 'Head' and one with 'Headless'.
3.In the 'googlesearchtest' folder, I also have 2 classes: one with 'Head' and one with 'Headless'.

4.To perform tests on various browsers, you can modify the "browserType" and
 select the appropriate enum value for the desired browser.

 @BeforeAll
    public static void classSetup() {

        driver = startBrowser(BrowserType.FIREFOX);   <------- Here