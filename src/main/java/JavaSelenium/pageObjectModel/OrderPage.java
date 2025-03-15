package JavaSelenium.pageObjectModel;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import JavaSelenium.Abstractcomponents.Reusablecomponents;

public class OrderPage extends Reusablecomponents {
	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//tr/td[2]")
	List<WebElement> orderList;
	
	@FindBy(xpath = "//button [@class='btn btn-primary']")
	WebElement viewButton;


	public Boolean verifyOrderDisplay(String ProductName) {
		Boolean match = orderList.stream().anyMatch(item -> item.getText().contains(ProductName));
		return match;

	}

}