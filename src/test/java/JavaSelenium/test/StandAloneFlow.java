package JavaSelenium.test;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import JavaSelenium.pageObjectModel.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneFlow{

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String ProductName = "ZARA COAT 3";
		WebDriverManager.chromiumdriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		LandingPage landingpage = new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("yadavvaibhav9890@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Vaibhav@12345");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> productList = driver.findElements(By.cssSelector(".mb-3"));
		System.out.println("Available Product count is :  " + productList.size());

		WebElement product = productList.stream().filter(element -> element.getText().contains(ProductName)).findFirst()
				.orElse(null);

		product.findElement(By.xpath("//button[contains(text(), 'Add To Cart')]")).click();

		System.out.println("'Zara' is selected added to cart");

		/*
		 * ( int i=0; i< List.size();i++) {
		 * 
		 * String Produt= List.get(i).getText(); if (Produt.contains("Saree")) {
		 * 
		 * driver.findElement(By.xpath("//button[contains(text(), 'Add To Cart')]")).
		 * click(); } }
		 */
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		// .ng-animating
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		// >>> its taking too much time , performance issue
		driver.findElement(By.xpath("//button[@routerlink= '/dashboard/cart']")).click();
		driver.findElement(By.xpath("//h1"));
		System.out.println("My cart page is displayed , please cross verify amount and proceed for checkout");

		List<WebElement> cartList = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartList.stream().anyMatch(item -> item.getText().contains(ProductName));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();

		WebElement countryInput = driver.findElement(By.xpath("//input[@placeholder='Select Country']"));
		countryInput.sendKeys("India");

		// Small wait before checking for results
		// Thread.sleep(1000); // Try reducing or increasing if needed

		// Now, wait for the suggestions dropdown
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

		// a.moveToElement(driver.findElement(By.xpath("//span[text()= '
		// India']"))).build().perform();
		driver.findElement(By.xpath("//span[text()= ' India']")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		String Confirmsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(Confirmsg.equalsIgnoreCase("Thankyou for the order."));
		System.out.println("Your order placed succsssfully");

	}

}
