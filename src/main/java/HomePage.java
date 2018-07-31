import helper.BasePage;
import helper.MyProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage{

    private static final By market = By.xpath(".//*[@data-id='market']");
    private static final By electronic = By.xpath(".//*[@class='topmenu__item i-bem topmenu__item_js_inited']//*[text()='Электроника']");
    private static final By televisors = By.xpath(".//*[@data-department='Электроника']//*[@class='topmenu__sublist']//*[text()='Телевизоры']");
    private static final By priceTo = By.xpath(".//*[@id='glpriceto']");
    private static final By diagonal = By.xpath(".//*[@for='15069594_15069598']");
    private static final By ddd = By.xpath(".//*[@for='6138358_1']");
    private static final By description = By.xpath(".//*[@class='n-snippet-card2__content']");
    private static final By prices = By.xpath(".//div[@class='price']");
    private static final Double priceLimit = 5000.0;
    public static List<String> expectedDiagonals = new ArrayList<>();
    private static final By oneTv = By.xpath(".//*[@class='n-snippet-list n-snippet-list_type_vertical metrika b-zone b-spy-init i-bem metrika_js_inited snippet-list_js_inited b-spy-init_js_inited b-zone_js_inited']/div[@data-id]");
    private static final By newSorting = By.xpath(".//*[@class='n-filter-panel-dropdown__main']//*[text() = 'по новизне']");
    private static final By ratingSorting = By.xpath(".//*[@class='n-filter-panel-dropdown__main']//*[text() = 'по рейтингу']");
    private static final By ratingString = By.xpath(".//*[@class='n-snippet-card2__header-stickers']");

    public void goToYandex(){
        navigateTo(MyProperties.getMyProperty("URL"));
    }

    public void clickMarket(){
        findElement(market).click();
        Actions actions = new Actions(driver);
        actions.moveToElement(findElement(electronic)).perform();
        findElement(televisors).click();
    }

    public void setParameters() {
        findElement(priceTo).sendKeys(Integer.toString(priceLimit.intValue()));
        findElement(diagonal).click();
        findElement(ddd).click();
        refresh();// пытался дождаться завершения всех аякс-запросов, но не вышло, сделал так
    }

    public void printAndCheckDescription()  {
        List<WebElement> elements;
        List<String> list = new ArrayList();
        elements = getWebElementsList(description);
        for (int i = 0; i < elements.size(); i++) {
            list.add(elements.get(i).getText());
            Assert.assertTrue(list.get(i).contains("3D"));
            Boolean flag1 = false;
            for(int j = 50; j <= 55; j ++) {
                String diagonal = Integer.toString(j);
                if(list.get(i).contains(diagonal + "\"")) {
                    flag1 = true;
                    expectedDiagonals.add(diagonal);
                }
            }
            Assert.assertTrue(flag1);
        }

        List<WebElement> priceList;
        List<Double> list2 = new ArrayList<>();
        priceList = getWebElementsList(prices);
        for (int i = 0; i < priceList.size(); i++) {
            list2.add(Double.parseDouble(stripNonDigits(priceList.get(i).getText())) / 100);
        }
        Boolean flag2 = true;
        for (int i = 0; i < list2.size(); i++) {
            if(list2.get(i) > priceLimit)
                flag2 = false;
        }
        Assert.assertTrue(flag2);
    }

    private String stripNonDigits(CharSequence input){
        StringBuilder sb = new StringBuilder(input.length());
        for(int i = 0; i < input.length(); i++){
            final char c = input.charAt(i);
            if(c > 47 && c < 58){
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private WebElement rememberFirstElement(){
        WebElement firstTelevisor = getWebElementsList(oneTv).get(0);
        return firstTelevisor;
    }

    private void changeSorting(By sorting){
        findElement(sorting).click();
        driver.navigate().refresh();
    }

    public void searchFirstTelevisor(){
        WebElement etalon = rememberFirstElement();
        String dataID = etalon.getAttribute("data-id");
        changeSorting(newSorting);
        Boolean flag = false;
        for(int i = 0; i < getWebElementsList(oneTv).size(); i ++){
            if(getWebElementsList(oneTv).get(i).getAttribute("data-id").equals(dataID))
                flag = true;
        }
        Assert.assertTrue(flag);
    }

    public void checkRatingSort(){
        changeSorting(ratingSorting);
        String tempString;
        List<Double> rating = new ArrayList<>();
        for(int i = 0; i < getWebElementsList(ratingString).size(); i ++)
        {
            tempString = getWebElementsList(ratingString).get(i).getText();
            if(tempString.equals(""))
                break;
            tempString = tempString.substring(0, tempString.indexOf('\n'));
            rating.add(Double.parseDouble(tempString));
        }

        for(int i = 0; i < rating.size() - 1; i ++)
        {
            Assert.assertTrue(rating.get(i) >= rating.get(i + 1));
        }

    }

}
