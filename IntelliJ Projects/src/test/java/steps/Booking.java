package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Booking {
    // Web driver where is going to point
    WebDriver driver;

    @Before
    public void setup_firefox() {
        /*
         * For the Java client, the default behavior is to use the Gecko driver,
         * but it can be overridden to use the legacy extension as a driver with
         * the webdriver.firefox.marionette property:
         */
        System.setProperty("webdriver.gecko.driver", "C:\\IntelliJ Projects\\src\\test\\resources\\geckodriver.exe");
        DesiredCapabilities firefoxOptions = DesiredCapabilities.firefox();
        firefoxOptions.setCapability("marionette", true);
        this.driver = new FirefoxDriver(firefoxOptions);
        // Maximize the window
        this.driver.manage().window().maximize();
        // Default load time
        this.driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        this.driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
    }

    // Close the driver
    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        this.driver.manage().deleteAllCookies();
        this.driver.quit();
        this.driver = null;
    }

    @Given("Go to the Ryanair website, go and book a flight")
    public void goToTheRyanairWebsiteGoAndBookAFlight() {
        driver.get("https://www.ryanair.com/ie/en");
        // Accept all the cookies
        driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/button[2]")).click();
    }

    @And("I make a booking selecting the origin and destination")
    public void iMakeABookingSelectingTheOriginAndDestination(DataTable table) throws Throwable {
        List<List<String>> data = table.asLists(String.class);

        // Wait until the page load the date
        Thread.sleep(2000);

        // Click the To field
        driver.findElement(By.xpath("//input[@id='input-button__destination']")).click();

        // Type the destination
        driver.findElement(By.xpath("//input[@id='input-button__destination']")).sendKeys(data.get(1).get(1));

        Thread.sleep(2000);
        // Select the destination in the hidden table
        WebElement el = driver.findElement(By.cssSelector("span.b2:nth-child(1) > span:nth-child(1)"));
        el.click();

        Thread.sleep(2000);
        // Select the date
        driver.findElement(By.xpath("//div[text()=' 30 ']")).click();
        //driver.findElement(By.xpath("//div[text()=' 6 ']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@data-id='2021-04-06']")).click();

        // Click the number of passengers
        driver.findElement(By.xpath("/html/body/ry-tooltip/div[2]/hp-app-controls-tooltips/fsw-controls-tooltips-container/fsw-controls-tooltips/fsw-passengers-container/fsw-passengers/div/button")).click();

        // Press the checkbox - Agree to the Website Terms of Use
        driver.findElement(By.xpath("/html/body/hp-app-root/hp-home-container/hp-home/hp-search-widget-container/hp-search-widget/div/hp-flight-search-widget-container/div/hp-terms-of-use-container/hp-terms-of-use/ry-checkbox/label/div/div[1]")).click();

        // Hit the search button
        driver.findElement(By.xpath("/html/body/hp-app-root/hp-home-container/hp-home/hp-search-widget-container/hp-search-widget/div/hp-flight-search-widget-container/fsw-flight-search-widget-container/fsw-flight-search-widget/div/div/button")).click();

        Thread.sleep(5000);
        // Click the flight to do the payment
        WebElement el1 = driver.findElement(By.cssSelector("div.ng-tns-c122-18"));
        el1.click();
        Thread.sleep(2000);
        WebElement el2 = driver.findElement(By.cssSelector(".fare-card__button-text"));
        el2.click();

        Thread.sleep(2000);
        WebElement el3 = driver.findElement(By.cssSelector("div.ng-tns-c122-15"));
        el3.click();
        Thread.sleep(2000);
        WebElement el4 = driver.findElement(By.cssSelector(".fare-card__button-text"));
        el4.click();

        // Login into our account
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[text()=' Log in ']")).click();
        driver.findElement(By.xpath("//input[@name='email']")).click();
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(data.get(1).get(2));
        driver.findElement(By.xpath("//input[@name='password']")).click();
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(data.get(1).get(3));
        Thread.sleep(2000);
        WebElement el5 = driver.findElement(By.cssSelector(".auth-submit__button"));
        el5.click();

    }

    @When("I pay for booking with card details")
    public void iPayForBookingWithCardDetailsAnd(DataTable table) throws Throwable {
        List<List<String>> data = table.asLists(String.class);
        // Wait until the page load the date
        Thread.sleep(3000);

        // Continue with the booking process
        WebElement el = driver.findElement(By.cssSelector(".dropdown__toggle"));
        el.click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//div[text()='Mr']")).click();

        // Fill the fields
        driver.findElement(By.xpath("/html/body/flights-root/div/div/div/div/flights-lazy-passengers/pax-app-container/pax-app/ry-spinner/div/div/div[2]/pax-app-form-container/pax-app-form/form/pax-passenger-container/pax-passenger/div/pax-passenger-details-container/pax-passenger-details/pax-passenger-details-form-container/pax-details-form/ry-dropdown/div[2]")).click();
        driver.findElement(By.xpath("/html/body/flights-root/div/div/div/div/flights-lazy-passengers/pax-app-container/pax-app/ry-spinner/div/div/div[2]/pax-app-form-container/pax-app-form/form/pax-passenger-container/pax-passenger/div/pax-passenger-details-container/pax-passenger-details/pax-passenger-details-form-container/pax-details-form/ry-dropdown/div[2]/div/div/ry-dropdown-item[1]")).click();
        driver.findElement(By.xpath("//input[@id='formState.passengers.ADT-0.name']")).click();
        driver.findElement(By.xpath("//input[@id='formState.passengers.ADT-0.name']")).sendKeys(data.get(1).get(0));
        driver.findElement(By.xpath("/html/body/flights-root/div/div/div/div/flights-lazy-passengers/pax-app-container/pax-app/ry-spinner/div/div/div[2]/pax-app-form-container/pax-app-form/form/pax-passenger-container/pax-passenger/div/pax-passenger-details-container/pax-passenger-details/pax-passenger-details-form-container/pax-details-form/ry-input-d[2]/div")).click();
        driver.findElement(By.xpath("//input[@id='formState.passengers.ADT-0.surname']")).click();
        driver.findElement(By.xpath("//input[@id='formState.passengers.ADT-0.surname']")).sendKeys(data.get(1).get(1));
        driver.findElement(By.xpath("/html/body/flights-root/div/div/div/div/ng-component/div/continue-flow-container/continue-flow/div/div")).click();

        // Wait until the page load the date
        Thread.sleep(8000);

        // Select a random seat
        WebElement el4 = driver.findElement(By.cssSelector("button.seats-v2-navigation__button:nth-child(3) > h4:nth-child(2)"));
        el4.click();
        driver.findElement(By.xpath("/html/body/seats-root/div/div/seats-container-root/seats-container-v2/main/div[2]/div/div/div[2]/div[2]/random-allocation-info/reinforcement-message/div/div[3]/div/button[1]")).click();

        // Wait until the page load the date
        Thread.sleep(5000);

        // Check the fields for the luggage
        WebElement el5 = driver.findElement(By.cssSelector("div.card__item:nth-child(1) > bags-cabin-bag-product:nth-child(1) > div:nth-child(1) > bags-product-selector:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > ry-radio-circle-button:nth-child(1) > label:nth-child(1)"));
        el5.click();
        // Click the continue button to finish the booking
        driver.findElement(By.xpath("/html/body/bags-root/bags-booking-container/div/main/div/section[4]/bags-continue-flow-container/bags-continue-flow/button")).click();

        // Wait until the page load the date
        Thread.sleep(3000);

        // Click "No thanks" button to finish the booking
        WebElement el6 = driver.findElement(By.cssSelector(".enhanced-takeover__product-dismiss-cta"));
        el6.click();

        // Wait until the page load the date
        Thread.sleep(5000);

        // Press the continue button
        WebElement el7 = driver.findElement(By.cssSelector(".ry-button--full"));
        el7.click();

        // Go the the checkout
        WebElement el8 = driver.findElement(By.cssSelector("ry-price.ng-tns-c112-1"));
        el8.click();

        // Wait until the page loads the cart
        Thread.sleep(3000);

        // Click the checkout button
        WebElement el9 = driver.findElement(By.xpath("/html/body/app-root/ry-spinner/trip-header-wrapper/header/ry-header/div/div[3]/div[2]/div/ng-component/ry-basket-total-container/ry-basket-total/ry-tooltip/div[2]/ry-price-breakdown-container/div/ry-price-breakdown-footer/div/div/ry-basket-continue-flow-container/ry-basket-continue-flow/button"));
        el9.click();
        //driver.findElement(By.xpath("/html/body/app-root/ry-portal[1]/div/ry-auth-popup-container/div/ry-auth-popup/div/ry-login/ry-auth-footer/div/button")).click();

        // Wait until the page loads
        Thread.sleep(3000);

        // Type the phone number
        driver.findElement(By.xpath("/html/body/app-root/ng-component/ry-spinner/div/payment-form/form/div[1]/contact-details/div/div/div[1]/div[2]/ry-input-d/div")).click();
        driver.findElement(By.xpath("/html/body/app-root/ng-component/ry-spinner/div/payment-form/form/div[1]/contact-details/div/div/div[1]/div[2]/ry-input-d/div")).sendKeys("7777777777");


        // Continue until the payment screen
        driver.findElement(By.xpath("/html/body/app-root/ng-component/ry-spinner/div/payment-form/form/div[2]/insurance/div/div[3]/div[1]/ry-checkbox/label/div/div[1]")).click();
        driver.findElement(By.xpath("/html/body/app-root/ng-component/ry-spinner/div/payment-form/form/div[1]/contact-details/div/div/div[3]/ry-checkbox/label/div/div[1]")).click();

        // Type the credit card
        driver.findElement(By.xpath("/html/body/app-root/ng-component/ry-spinner/div/payment-form/form/div[5]/payment-methods/div/div/ry-tabs/div[2]/add-method-modal/form/div/div[1]/div[1]/div[2]/card-details/form/ry-input-d[1]/div/input")).click();
        driver.findElement(By.xpath("/html/body/app-root/ng-component/ry-spinner/div/payment-form/form/div[5]/payment-methods/div/div/ry-tabs/div[2]/add-method-modal/form/div/div[1]/div[1]/div[2]/card-details/form/ry-input-d[1]/div/input")).sendKeys(data.get(1).get(2));

        // Select the expiration month
        driver.findElement(By.xpath("/html/body/app-root/ng-component/ry-spinner/div/payment-form/form/div[5]/payment-methods/div/div/ry-tabs/div[2]/add-method-modal/form/div/div[1]/div[1]/div[2]/card-details/form/expiry-date/div/span[2]/div[1]/ry-dropdown/div[2]")).click();
        driver.findElement(By.xpath("/html/body/app-root/ng-component/ry-spinner/div/payment-form/form/div[5]/payment-methods/div/div/ry-tabs/div[2]/add-method-modal/form/div/div[1]/div[1]/div[2]/card-details/form/expiry-date/div/span[2]/div[1]/ry-dropdown/div[2]/div/div/ry-dropdown-item[3]/button")).click();

        // Select the expiration year
        driver.findElement(By.xpath("/html/body/app-root/ng-component/ry-spinner/div/payment-form/form/div[5]/payment-methods/div/div/ry-tabs/div[2]/add-method-modal/form/div/div[1]/div[1]/div[2]/card-details/form/expiry-date/div/span[2]/div[2]/ry-dropdown/div[2]")).click();
        driver.findElement(By.xpath("/html/body/app-root/ng-component/ry-spinner/div/payment-form/form/div[5]/payment-methods/div/div/ry-tabs/div[2]/add-method-modal/form/div/div[1]/div[1]/div[2]/card-details/form/expiry-date/div/span[2]/div[2]/ry-dropdown/div[2]/div/div/ry-dropdown-item[3]/button")).click();

        // Type the security number
        driver.findElement(By.xpath("/html/body/app-root/ng-component/ry-spinner/div/payment-form/form/div[5]/payment-methods/div/div/ry-tabs/div[2]/add-method-modal/form/div/div[1]/div[1]/div[2]/card-details/form/verification-code/div/span[1]/ry-input-d/div")).click();
        driver.findElement(By.xpath("/html/body/app-root/ng-component/ry-spinner/div/payment-form/form/div[5]/payment-methods/div/div/ry-tabs/div[2]/add-method-modal/form/div/div[1]/div[1]/div[2]/card-details/form/verification-code/div/span[1]/ry-input-d/div")).sendKeys(data.get(1).get(3));

        // Type the cardholder name
        driver.findElement(By.xpath("/html/body/app-root/ng-component/ry-spinner/div/payment-form/form/div[5]/payment-methods/div/div/ry-tabs/div[2]/add-method-modal/form/div/div[1]/div[1]/div[2]/card-details/form/ry-input-d[2]/div")).click();
        driver.findElement(By.xpath("/html/body/app-root/ng-component/ry-spinner/div/payment-form/form/div[5]/payment-methods/div/div/ry-tabs/div[2]/add-method-modal/form/div/div[1]/div[1]/div[2]/card-details/form/ry-input-d[2]/div")).sendKeys(data.get(1).get(0));

        // Type the address
        driver.findElement(By.xpath("/html/body/app-root/ng-component/ry-spinner/div/payment-form/form/div[5]/payment-methods/div/div/ry-tabs/div[2]/add-method-modal/form/div/div[1]/div[2]/billing-address/address-form/form/ry-input-d[1]/div")).click();
        driver.findElement(By.xpath("/html/body/app-root/ng-component/ry-spinner/div/payment-form/form/div[5]/payment-methods/div/div/ry-tabs/div[2]/add-method-modal/form/div/div[1]/div[2]/billing-address/address-form/form/ry-input-d[1]/div")).sendKeys("Testing Street 1234");

        // Type the city
        driver.findElement(By.xpath("/html/body/app-root/ng-component/ry-spinner/div/payment-form/form/div[5]/payment-methods/div/div/ry-tabs/div[2]/add-method-modal/form/div/div[1]/div[2]/billing-address/address-form/form/ry-input-d[3]/div")).click();
        driver.findElement(By.xpath("/html/body/app-root/ng-component/ry-spinner/div/payment-form/form/div[5]/payment-methods/div/div/ry-tabs/div[2]/add-method-modal/form/div/div[1]/div[2]/billing-address/address-form/form/ry-input-d[3]/div")).sendKeys("Madrid");

        driver.findElement(By.xpath("/html/body/app-root/ng-component/ry-spinner/div/payment-form/form/div[5]/payment-methods/div/div/ry-tabs/div[2]/add-method-modal/form/div/div[1]/div[2]/billing-address/address-form/form/ry-autocomplete/div/div/div[1]")).click();
        driver.findElement(By.xpath("/html/body/app-root/ng-component/ry-spinner/div/payment-form/form/div[5]/payment-methods/div/div/ry-tabs/div[2]/add-method-modal/form/div/div[1]/div[2]/billing-address/address-form/form/ry-autocomplete/div/div/div[1]")).sendKeys("Spain");

        // Type the postal code
        driver.findElement(By.xpath("/html/body/app-root/ng-component/ry-spinner/div/payment-form/form/div[5]/payment-methods/div/div/ry-tabs/div[2]/add-method-modal/form/div/div[1]/div[2]/billing-address/address-form/form/ry-input-d[4]/div/input")).click();
        driver.findElement(By.xpath("/html/body/app-root/ng-component/ry-spinner/div/payment-form/form/div[5]/payment-methods/div/div/ry-tabs/div[2]/add-method-modal/form/div/div[1]/div[2]/billing-address/address-form/form/ry-input-d[4]/div/input")).sendKeys("28222");
        driver.findElement(By.xpath("/html/body/app-root/ng-component/ry-spinner/div/payment-form/form/div[5]/payment-methods/div/div/ry-tabs/div[2]/add-method-modal/form/div/div[1]/div[2]/billing-address/address-form/form/ry-input-d[4]/div/input")).sendKeys(Keys.RETURN);

        // Type the VAT number
        driver.findElement(By.xpath("/html/body/app-root/ng-component/ry-spinner/div/payment-form/form/div[6]/vat-container/div/div[1]/div/ry-toggle/label/div/div")).click();
        driver.findElement(By.xpath("/html/body/app-root/ng-component/ry-spinner/div/payment-form/form/div[6]/vat-container/div/div[2]/div[1]/vat-details/form/ry-input-d[1]/div/input")).click();
        driver.findElement(By.xpath("/html/body/app-root/ng-component/ry-spinner/div/payment-form/form/div[6]/vat-container/div/div[2]/div[1]/vat-details/form/ry-input-d[1]/div/input")).sendKeys("ESX9999999R");

        // Type the Business name
        driver.findElement(By.xpath("/html/body/app-root/ng-component/ry-spinner/div/payment-form/form/div[6]/vat-container/div/div[2]/div[1]/vat-details/form/ry-input-d[2]/div/input")).click();
        driver.findElement(By.xpath("/html/body/app-root/ng-component/ry-spinner/div/payment-form/form/div[6]/vat-container/div/div[2]/div[1]/vat-details/form/ry-input-d[2]/div/input")).sendKeys("TestingWeb");

        // Click the pay now button
        driver.findElement(By.xpath("/html/body/app-root/ng-component/ry-spinner/div/payment-form/form/div[8]/div/pay-button/div/button")).click();
    }

    @Then("I should get payment declined message")
    public void iShouldGetPaymentDeclinedMessage() throws Throwable {
        Thread.sleep(3000);
        // Check the payment wasn't successful
        WebElement checkPayment = driver.findElement(By.xpath("//div[contains(text(), 'Oops, something went wrong. Please check your payment details and try again')]"));
        Assert.assertTrue(checkPayment.isDisplayed());
    }

}