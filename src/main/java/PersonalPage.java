import helper.BasePage;
import helper.MyProperties;
import org.openqa.selenium.By;

public class PersonalPage extends BasePage {

    private static final By writeMessageButton = By.xpath(".//*[@class='b-toolbar__item']/a/span");
    private static final By receiverField = By.xpath(".//*[@data-original-name='To']");
    private static final By textArea = By.xpath(".//*[@id='tinymce']");
    private static final By sendButton = By.xpath(".//*[@class='b-toolbar__btn__text']");

    public void clickWriteMessageButton(){
        findElement(writeMessageButton).click();
    }

    public void fillFields(){
        findElement(receiverField).sendKeys(MyProperties.getMyProperty("receiver"));
        driver.switchTo().frame(0);
        findElement(textArea).sendKeys(MyProperties.getMyProperty("message"));
        driver.switchTo().defaultContent();
    }

    public void sendButtonClick(){
        findElement(sendButton).click();
    }

}
