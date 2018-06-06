package helper;

import static helper.SingletonWebDriver.initDriver;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
public class TestBase {

    protected static final String browser = MyProperties.getMyProperty("browser");

    public void passOrFail(Boolean bool){
        if(bool)
            assertTrue(true);
        else
            assertTrue(false);
    }

    @BeforeClass
    public void setup() {
        initDriver(browser);
    }

    @AfterClass
    public void teardown() {
        SingletonWebDriver.getDriver().quit();
    }
}
