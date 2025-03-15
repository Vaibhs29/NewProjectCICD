package JavaSelenium.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import JavaSelenium.Testcomponets.BaseTest;
import JavaSelenium.pageObjectModel.CartPage;
import JavaSelenium.pageObjectModel.Productpage;

import java.io.IOException;

public class ErrorValidationTest extends BaseTest {

	@Test(groups = {"ErrorHandling"}, retryAnalyzer=JavaSelenium.Testcomponets.Retry.class)

	public void loginErrorValidation() throws IOException {
		// Attempt login with incorrect credentials
		// Need to see CICD so updating this comments. 
		landingPage.LoginApplication("yadavvaibhav9890@gmail.com", "Vibhav@12345");

		;
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}
	
	@Test (groups = {"ErrorHandling"})
	public void productValidationError() throws InterruptedException {

		String ProductName = "ZARA COAT 3";
		// Navigate to Landing Page and Login

		Productpage product = landingPage.LoginApplication("yadavvaibhav9890@gmail.com", "Vaibhav@12345");

		// Select Product and Add to Cart
		product.getProductList();
		product.addproductToCart(ProductName);
		CartPage cartpage = product.goToCart();

		// Verify Product in Cart
		boolean result = cartpage.verifyProductDisplay("ZARA COAT 3");
		Assert.assertTrue(result);
	}
}
