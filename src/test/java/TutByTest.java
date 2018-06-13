
import org.testng.annotations.Test;

public class TutByTest {

    HomePage homePageObject = new HomePage();
    PersonalPage personalPageObject = new PersonalPage();

    @Test
    public void send(){
        homePageObject.goToTutBy();
        homePageObject.logIn();
        personalPageObject.toMail();
        personalPageObject.getNumberOfMessages();
    }
}
