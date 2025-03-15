package JavaSelenium.pageObjectModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage {

	WebDriver driver;

	public ConfirmationPage(WebDriver driver) { // Explicit constructor
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".hero-primary")
	WebElement Confirmsg;

	public String getconfirmationmesaage() {
		return Confirmsg.getText();
	}

	
}