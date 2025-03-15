package JavaSelenium.pageObjectModel;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import JavaSelenium.Abstractcomponents.Reusablecomponents;

public class Productpage extends Reusablecomponents {
	WebDriver driver;

	public Productpage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> productList;

	@FindBy(css = ".ng-animating")
	WebElement spinner;

	By productBy = By.cssSelector(".mb-3");
	By addToCart = By.xpath("//button[contains(text(), 'Add To Cart')]");
	By toastMessage = By.cssSelector("#toast-container");

	public List<WebElement> getProductList() {
		waitforElementToAppear(productBy);

		return productList;
	}

	public WebElement getProductbyName(String ProductName) {
		WebElement product = productList.stream().filter(element -> element.getText().contains(ProductName)).findFirst()
				.orElse(null);
		return product;
	}

	public void addproductToCart(String ProductName) throws InterruptedException {
		WebElement prod = getProductbyName(ProductName);
		prod.findElement(addToCart).click();
		waitforElementToAppear(toastMessage);
		waitforElementToDisappear(spinner);
	}
}