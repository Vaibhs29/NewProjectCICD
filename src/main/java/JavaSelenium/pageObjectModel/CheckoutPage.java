package JavaSelenium.pageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import JavaSelenium.Abstractcomponents.Reusablecomponents;

public class CheckoutPage extends Reusablecomponents {
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement countryInput;
	@FindBy(xpath = "//span[text()= ' India']")
	WebElement SelectCountry;
	@FindBy(css = ".action__submit")
	WebElement placeorder;

	public void onCheckoutPage(String CountryName) {
		countryInput.sendKeys(CountryName);
		SelectCountry.click();
	
	}
	
	public ConfirmationPage submitOrder() {
		placeorder.click();
		return new ConfirmationPage(driver); 
		
			
	}



}