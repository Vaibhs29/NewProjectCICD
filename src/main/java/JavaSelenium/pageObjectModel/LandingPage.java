package JavaSelenium.pageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import JavaSelenium.Abstractcomponents.Reusablecomponents;

public class LandingPage extends Reusablecomponents {
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement User;
	@FindBy(id = "userPassword")
	WebElement pass;
	@FindBy(id = "login")
	WebElement submit;
	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;
	

	public Productpage LoginApplication(String email, String password) {
		User.sendKeys(email);
		pass.sendKeys(password);
		submit.click();
		Productpage product = new Productpage(driver);
		return product;

	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
	}
	
	public String getErrorMessage() {
		waitforWebElementToAppear(errorMessage);
		return errorMessage.getText();
		
	}
}
