package JavaSelenium.pageObjectModel;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import JavaSelenium.Abstractcomponents.Reusablecomponents;

public class CartPage extends Reusablecomponents {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".cartSection h3")

	List<WebElement> cartList;
	@FindBy(css = ".totalRow button")
	WebElement checkout;

	public CheckoutPage goTocheckout() {

		checkout.click();
		CheckoutPage checkout = new CheckoutPage(driver);
		return checkout;

	}

	public Boolean verifyProductDisplay(String ProductName) {
		Boolean match = cartList.stream().anyMatch(item -> item.getText().contains(ProductName));
		return match;

	}

}