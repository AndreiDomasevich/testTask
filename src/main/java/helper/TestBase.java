package helper;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static helper.SingletonWebDriver.initDriver;

public class TestBase {

    protected static final String browser = MyProperties.getMyProperty("browser");

    @BeforeClass
    public void setup() {
        initDriver(browser);
    }

    @AfterClass
    public void teardown() {
        SingletonWebDriver.getDriver().quit();
    }
}
