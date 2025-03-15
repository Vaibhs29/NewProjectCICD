package JavaSelenium.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import JavaSelenium.Testcomponets.BaseTest;
import JavaSelenium.pageObjectModel.CartPage;
import JavaSelenium.pageObjectModel.CheckoutPage;
import JavaSelenium.pageObjectModel.ConfirmationPage;
import JavaSelenium.pageObjectModel.OrderPage;
import JavaSelenium.pageObjectModel.Productpage;

public class SubmitOrderTest extends BaseTest {
	String ProductName = "ZARA COAT 3";

	@Test // (dataProvider = "getData", groups = { "purchaseOrder" })
	public void SubmitOrder() throws InterruptedException {
		{

			String CountryName = "India";
			// Navigate to Landing Page and Login

			Productpage product = landingPage.LoginApplication("yadavvaibhav9890@gmail.com", "Vaibhav@12345");

			// Select Product and Add to Cart
			product.getProductList();
			product.addproductToCart(ProductName);
			CartPage cartpage = product.goToCart();

			// Verify Product in Cart
			boolean result = cartpage.verifyProductDisplay(ProductName);
			Assert.assertTrue(result);

			// Proceed to Checkout
			CheckoutPage checkout = cartpage.goTocheckout();
			checkout.onCheckoutPage(CountryName);
			ConfirmationPage confirm = checkout.submitOrder();

			// Verify Order Confirmation
			String confirmMsg = confirm.getconfirmationmesaage();
			Assert.assertTrue(confirmMsg.equalsIgnoreCase("Thankyou for the order."));

			System.out.println("Your order placed successfully");

		}
	}

	// // wanted to see what I have order , validating under orders section

	@Test // (dependsOnMethods = { "SubmitOrder" })
	public void orderHistoryTest() {

		Productpage product = landingPage.LoginApplication("yadavvaibhav9890@gmail.com", "Vaibhav@12345");
		OrderPage orderpage = product.goToOrdersPage();
		Assert.assertTrue(orderpage.verifyOrderDisplay(ProductName));
	}

	// @DataProvider
	// public void getData() {

	/*
	 * HashMap<String, String> map = new HashMap<>(); map.put("email",
	 * "yadavvaibhav9890@gmail.com"); map.put("pass", "Vaibhav@12345");
	 * map.put("productName", "ZARA COAT 3");
	 * 
	 * HashMap<String, String> map1 = new HashMap<>(); map1.put("email",
	 * "vaibhavyadav2063@gmail.com"); map1.put("pass", "Vaibhav@123456");
	 * map1.put("productName", "ADIDAS ORIGINAL");
	 * 
	 * return new Object[][] {{map}, {map1}};
	 */
	// }

}
