
import org.testng.annotations.Test;

public class MailRuSendMessage {

    HomePage homePageObject = new HomePage();
    PersonalPage personalPageObject = new PersonalPage();

    @Test
    public void send(){
        homePageObject.goToMailRu();
        homePageObject.logIn();
        personalPageObject.clickWriteMessageButton();
        personalPageObject.fillFields();
        personalPageObject.sendButtonClick();
    }
}
