package pages;

import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.BaseTest;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.UUID;

public class ErrorControl {

    public static File takeScreenshot(WebDriver driver, String fileName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File screenshot = ts.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File("src/test/java/screenshot/" + fileName);
        FileUtils.copyFile(screenshot, destinationFile);
        return destinationFile;
    }

    private String generateUniqueFileName(String baseName) {
        String uniqueID = UUID.randomUUID().toString();
        return baseName + "_" + uniqueID + ".png";
    }

    public void sendingMail(File screenshotFile) throws EmailException {
        HtmlEmail email = new HtmlEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("eneskaynak2002@gmail.com", "Demo@123"));
        email.setSSLOnConnect(true);
        email.setFrom("eneskaynak2002@gmail.com");
        email.setSubject("Selenium Test Report");
        email.setMsg("This is a test mail from Selenium");

        EmailAttachment attachment = new EmailAttachment();
        attachment.setPath(screenshotFile.getAbsolutePath());
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription("Test Screenshot");
        attachment.setName(screenshotFile.getName());
        email.attach(attachment);

        email.addTo("soymacetin@gmail.com");
        email.send();
    }

    public void homePageControl() {
        try {
            if (!BaseTest.driver.getCurrentUrl().equals(BaseTest.baseUrl)) {
                throw new RuntimeException("Trendyol Anasayfa Açılmadı");
            }
        } catch (RuntimeException e) {
            try {
                String fileName = generateUniqueFileName("homePageCheck");
                File screenshot = takeScreenshot(BaseTest.driver, fileName);
                sendingMail(screenshot);
            } catch (IOException | EmailException ex) {
                ex.printStackTrace();
            } finally {
                BaseTest.driver.quit();
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    public void elementControl(By path) {
        try {
            WebDriverWait waitElement = new WebDriverWait(BaseTest.driver, Duration.ofSeconds(10));
            waitElement.until(ExpectedConditions.visibilityOfElementLocated(path));
            waitElement.until(ExpectedConditions.elementToBeClickable(path));

        } catch (TimeoutException e) {
            try {
                String fileName = generateUniqueFileName("elementVisibility");
                File screenshot = takeScreenshot(BaseTest.driver, fileName);
                sendingMail(screenshot);
            } catch (IOException | EmailException ex) {
                ex.printStackTrace();
            } finally {
                BaseTest.driver.quit();
                throw new TimeoutException("Öge Bulunamadı: " + path.toString());
            }
        }
    }
}
