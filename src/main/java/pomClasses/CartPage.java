package pomClasses;



	import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.PageFactory;

import abstractcomponant.AbstractComponant;

	public class CartPage extends  AbstractComponant {

		WebDriver driver;

		public CartPage(WebDriver driver) {
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}

		// 🔹 HEADER
		@FindBy(id = "react-burger-menu-btn")
		WebElement hamburgerMenuBtn;

		@FindBy(xpath = "//img[@data-test='close-menu']")
		WebElement closeMenuBtn;

		@FindBy(xpath = "//a[@data-test='shopping-cart-link']")
		WebElement cartIcon;

		@FindBy(xpath = "//a[@data-test='logout-sidebar-link']")
		WebElement logoutLink;

		@FindBy(xpath = "//a[@data-test='reset-sidebar-link']")
		WebElement resetAppStateLink;

		// 🔹 TITLE
		@FindBy(xpath = "//span[@data-test='title']")
		WebElement cartTitle;

		// 🔹 LABELS
		@FindBy(xpath = "//div[@data-test='cart-quantity-label']")
		WebElement qtyLabel;

		@FindBy(xpath = "//div[@data-test='cart-desc-label']")
		WebElement descriptionLabel;

		// 🔹 BUTTONS
		@FindBy(xpath = "//button[@data-test='continue-shopping']")
		WebElement continueShoppingBtn;

		@FindBy(xpath = "//button[@data-test='checkout']")
		WebElement checkoutBtn;

		// 🔹 CART ITEMS
		@FindBy(xpath = "//div[@class='cart_item']")
		List<WebElement> cartItems;

		@FindBy(xpath = "//div[@class='inventory_item_name']")
		List<WebElement> productNames;

		// 🔹 FOOTER
		@FindBy(xpath = "//div[@data-test='footer-copy']")
		WebElement footerText;

		// ==========================
		// 🔹 ACTION METHODS
		// ==========================

		public String getCartTitle() {
			waitForWebElementToAppearByWebElement(cartTitle);
			return cartTitle.getText();
		}

		public boolean isQtyLabelDisplayed() {
			return qtyLabel.isDisplayed();
		}

		public boolean isDescriptionLabelDisplayed() {
			return descriptionLabel.isDisplayed();
		}

		public void clickContinueShopping() {
			waitForElementToWebElementisClickable(continueShoppingBtn);
			continueShoppingBtn.click();
		}

		public void clickCheckout() {
			waitForElementToWebElementisClickable(checkoutBtn);
			checkoutBtn.click();
		}

		public int getCartItemsCount() {
			return cartItems.size();
		}

		public List<String> getCartProductNames() {
			return productNames.stream()
					.map(WebElement::getText)
					.collect(Collectors.toList());
		}

		public void openMenu() {
			hamburgerMenuBtn.click();
		}

		public void closeMenu() {
			closeMenuBtn.click();
		}

		public void clickLogout() {
			logoutLink.click();
		}

		public void clickResetAppState() {
			resetAppStateLink.click();
		}

		public String getFooterText() {
			return footerText.getText();
		}
		
}
