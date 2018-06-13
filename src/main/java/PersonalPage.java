import helper.BasePage;
import org.openqa.selenium.By;

public class PersonalPage extends BasePage {

    private static final By mailButton = By.xpath(".//*[@title='Почта']");
    private static final By singleMessage = By.xpath(".//*[@class='ns-view-container-desc mail-MessagesList js-messages-list']/div");

    public void toMail(){
        findElement(mailButton).click();
    }

    public void getNumberOfMessages(){

        System.out.println(getWebElementsList(singleMessage).size());//в задании не было необходимости учитывать ситуацию отсутствия писем, поэтому она и не предусматривалась.
    }

}
