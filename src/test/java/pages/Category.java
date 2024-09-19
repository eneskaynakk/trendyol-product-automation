package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import tests.BaseTest;

import java.util.List;

public class Category {
    ErrorControl errorControl = new ErrorControl();

    By brandNamePath = By.cssSelector("input[placeholder=\"Marka ara\"]");
    By leastPricePath = By.cssSelector("input[class=\"fltr-srch-prc-rng-input min\"]");
    By highestPricePath = By.cssSelector("input[class=\"fltr-srch-prc-rng-input max\"]");
    By searchButton = By.cssSelector("button[class=\"fltr-srch-prc-rng-srch\"]");
    By filterButton = By.cssSelector("div[class=\"selected-order\"]");
    List<WebElement> priceButton = BaseTest.driver.findElements(By.className("fltr-cntnr-ttl"));

    public void productBrand(String brandName){
        errorControl.elementControl(brandNamePath);
        BaseTest.driver.findElement(brandNamePath).sendKeys(brandName);

        try {
            By brandNameButton = By.xpath("//div[text()=" + "'" + brandName + "'" + "]");
            errorControl.elementControl(brandNameButton);
            BaseTest.driver.findElement(brandNameButton).click();

        }catch (TimeoutException e){
            throw new TimeoutException("Hata: Aradığınız Marka Bulunamadı");
        }
    }

    public void productPrice(String leastPrice, String highestPrice){
        for (WebElement price : priceButton) {
            if (price.getText().equals("Fiyat")) {
                price.click();
                break;
            }
        }

        errorControl.elementControl(leastPricePath);
        BaseTest.driver.findElement(leastPricePath).sendKeys(leastPrice);

        errorControl.elementControl(highestPricePath);
        BaseTest.driver.findElement(highestPricePath).sendKeys(highestPrice);

        errorControl.elementControl(searchButton);
        BaseTest.driver.findElement(searchButton).click();
    }

    public void productFilter(String filter){
        errorControl.elementControl(filterButton);
        BaseTest.driver.findElement(filterButton).click();

        By filterOption = By.xpath("(//span[@class=\"search-dropdown-text\"])[" + filter + "]");
        errorControl.elementControl(filterOption);
        BaseTest.driver.findElement(filterOption).click();

    }
}
