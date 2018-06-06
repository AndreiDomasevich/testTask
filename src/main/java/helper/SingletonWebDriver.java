package helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SingletonWebDriver {

    private static WebDriver driver;
    private static String pathToWebDriver;
    private static String osName;
    private static String firefoxName;
    private static String chromeName;
    private static final String chromeDriver = "webdriver.chrome.driver";
    private static final String geckoDriver = "webdriver.gecko.driver";

    public SingletonWebDriver () {}

    public static WebDriver getDriver() {
        if (driver != null) {
            return driver;
        } else {
            System.setProperty(chromeDriver, MyProperties.getMyProperty("windowsPath") + MyProperties.getMyProperty("chromeNameWindows"));
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            return driver;

        }
    }

    public static WebDriver initDriver(String  browser) {
        if(System.getProperty("os.name").contains("Windows"))
            osName = "windows";
        else
            osName = "linux";
        switch(osName)
        {
            case "windows":{
                pathToWebDriver = MyProperties.getMyProperty("windowsPath");
                chromeName = MyProperties.getMyProperty("chromeNameWindows");
                firefoxName = MyProperties.getMyProperty("firefoxNameWindows");
                break;
            }

            case "linux":{
                pathToWebDriver = MyProperties.getMyProperty("linuxPath");
                chromeName = MyProperties.getMyProperty("chromeNameLinux");
                firefoxName = MyProperties.getMyProperty("firefoxNameLinux");
                break;
            }

            default: {
                pathToWebDriver = MyProperties.getMyProperty("windowsPath");
                chromeName = MyProperties.getMyProperty("chromeNameWindows");
                firefoxName = MyProperties.getMyProperty("firefoxNameWindows");
                break;
            }

        }
        switch (browser) {
                case "chrome": {
                    System.setProperty(chromeDriver, pathToWebDriver + chromeName);
                    driver = new ChromeDriver();
                    break;
                }

                case "firefox": {
                    System.setProperty(geckoDriver, pathToWebDriver + firefoxName);
                    driver = new FirefoxDriver();
                    break;
                }

                default: {
                    System.setProperty(chromeDriver, pathToWebDriver + chromeName);
                    driver = new ChromeDriver();
                }
            }

        driver.manage().window().maximize();
        return driver;
    }
}
