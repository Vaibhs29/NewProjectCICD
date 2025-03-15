package JavaSelenium.Abstractcomponents;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import JavaSelenium.pageObjectModel.CartPage;
import JavaSelenium.pageObjectModel.OrderPage;

public class Reusablecomponents {

	WebDriver driver;

	public Reusablecomponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement cartHeaderButton;

	@FindBy(css = "[routerlink='/dashboard/myorders']")
	WebElement OrderHeaderbutton;

	public void waitforElementToAppear(By Findby) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(Findby)); // Changed this line
	}

	public void waitforWebElementToAppear(WebElement Findby) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(Findby));
	}

	public void waitforElementToDisappear(WebElement ele) throws InterruptedException {
		Thread.sleep(2000);
	}

	public CartPage goToCart() {
		cartHeaderButton.click();
		return new CartPage(driver);
	}

	public OrderPage goToOrdersPage() {
		OrderHeaderbutton.click();
		OrderPage orderpage = new OrderPage(driver);
		return orderpage;
	}
}
