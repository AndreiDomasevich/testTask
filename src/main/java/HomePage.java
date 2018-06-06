import helper.BasePage;
import helper.MyProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class HomePage extends BasePage{

    private static final By loginField = By.xpath(".//*[@id='mailbox:login']");
    private static final By passwordField = By.xpath(".//*[@id='mailbox:password']");

    public void goToMailRu(){
        navigateTo(MyProperties.getMyProperty("URL"));
    }

    private void setData(){
        setOneField("login");
        setOneField("password");
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

    public void logIn(){ // не уверен, что лучшая идея так структурировать каждое отдельное действие. Просто, чтобы прояснить ситуацию, это один из возможных способов.
        setData();
        pressEnter();
    }



}
