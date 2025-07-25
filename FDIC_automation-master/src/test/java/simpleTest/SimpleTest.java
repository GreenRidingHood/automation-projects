package simpleTest;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class SimpleTest {
    private WebDriver driver;
    private WebDriverWait wait;
    @BeforeMethod
    public void startUp(){
        System.setProperty("web.chrome.driver","chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10,200);
        driver.get("https://www.fdic.gov/resources/resolutions/bank-failures/failed-bank-list/index.html");
    }
    @AfterMethod
    public void tearDown() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ee){}
        driver.quit();
    }
    @Test
    public void openFDIC() {
        By tableSearchLocator = By.xpath("//div[@class='dataTables_filter']");
        WebElement tableSearchField = driver.findElement(tableSearchLocator);

        // tableSearchField.sendKeys(Keys.ENTER);
        // tableSearchField.sendKeys("The");

        //new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='usa-input usa-input-search']"))).click();
        //new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='dataTables_filter']"))).sendKeys("The");

        Actions builder = new Actions(driver);
        builder.moveToElement(tableSearchField).doubleClick().perform();
        // Thread.sleep(5000);
        // builder.moveToElement(tableSearchField).sendKeys("The");

        Assert.assertTrue(tableSearchField.isDisplayed());

    }
    @Test
    public void getting_correct_number_of_bank(){

        //Finding number of Rows
        List<WebElement> rowsNumber = driver.findElements(By.xpath("//table/tbody/tr"));
        int rowCount = rowsNumber.size();
        System.out.println("Number of rows in this table : " + rowCount);

        WebElement dataLength = driver.findElement(By.xpath("//div[@id='DataTables_Table_0_info']"));
        Assert.assertEquals(dataLength.getText().split(" ")[3],String.valueOf(rowCount));

    }
    @Test
    public void update_rows_number(){

        //Finding number of Rows
        Select dropdown = new Select(driver.findElement(By.xpath("//div[@class='dataTables_length']/label/select")));
        dropdown.selectByIndex(2);
        List<WebElement> rowsNumber = driver.findElements(By.xpath("//table/tbody/tr"));
        int rowCount = rowsNumber.size();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("table")));
        Assert.assertEquals(String.valueOf(rowCount),"60");

    }
}
/*
* What can do more with elements
* // System.out.println("Inner text from element: " + ELEMENT.getText());
  // System.out.println("Attribute type value: " + ELEMENT.getAttribute("type"));
  // System.out.println("Color: " + ELEMENT.getCssValue("background"));
*/
/* More driver commands
    // String url = driver.getCurrentUrl();
    // System.out.println("Current url = " + url);

    // driver.navigate().refresh();
    // String pageTitle = driver.getTitle();
    // System.out.println("Page title = " + pageTitle);
*/