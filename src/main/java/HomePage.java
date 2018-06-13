import helper.BasePage;
import helper.MyProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class HomePage extends BasePage{

    private static final By authorizeButton = By.xpath(".//*[@id='authorize']/div/a");
    private static final By loginField = By.xpath(".//*[@name='login']");
    private static final By passwordField = By.xpath(".//*[@name='password']");

    public void goToTutBy(){
        navigateTo(MyProperties.getMyProperty("URL"));
    }

    public void logIn(){
        findElement(authorizeButton).click();
        setOneField("login");
        setOneField("password");
        pressEnter();
    }

    private void setOneField(String type){
        if(type.equals("login"))
            findElement(loginField).sendKeys(MyProperties.getMyProperty(type));
        else if(type.equals("password"))
            findElement(passwordField).sendKeys(MyProperties.getMyProperty(type));
    }

    private void pressEnter(){
        findElement(passwordField).sendKeys(Keys.ENTER);
    }





}
