package tests;

import pages.Category;
import pages.ErrorControl;
import pages.HomePage;
import pages.Product;

import java.util.Scanner;

public class MainTest {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        System.out.println("Please enter product name");
        String productName = scanner.nextLine();
        System.out.println("Please enter brand name");
        String brandName = scanner.nextLine();
        System.out.println("Please enter least price");
        String leastPrice = scanner.nextLine();
        System.out.println("Please enter highest price");
        String highestPrice = scanner.nextLine();
        System.out.println("Please enter filter name : \n 1-Recommended \n 2-Lowest price \n 3-Highest price ");
        String filter = scanner.nextLine();

        BaseTest baseTest = new BaseTest();
        baseTest.setUp();
        double startTime = System.currentTimeMillis();

        ErrorControl errorControl = new ErrorControl();
        errorControl.homePageControl();

        HomePage homePage = new HomePage();
        homePage.notifications();
        homePage.productSearch(productName);

        Category categoryPage = new Category();
        categoryPage.productBrand(brandName);
        categoryPage.productPrice(leastPrice,highestPrice);
        categoryPage.productFilter(filter);

        Product productPage = new Product();
        productPage.addToCart();

        baseTest.tearDown();
        double endTime = System.currentTimeMillis();
        double duration = endTime - startTime;
        System.out.println("Test Süresi: " + (duration / 1000) + " saniye");
    }
}
