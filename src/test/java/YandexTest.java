
import helper.TestBase;
import org.testng.annotations.Test;

public class YandexTest extends TestBase{

    private HomePage homePageObject = new HomePage();
    private SingleTvPage singleTvPageObject = new SingleTvPage();


    @Test
    public void steps() {
        homePageObject.goToYandex();
        homePageObject.clickMarket();
        homePageObject.setParameters();
        homePageObject.printAndCheckDescription();
        singleTvPageObject.checkCharakteristics();
        homePageObject.searchFirstTelevisor();
        homePageObject.checkRatingSort();
    }
}
