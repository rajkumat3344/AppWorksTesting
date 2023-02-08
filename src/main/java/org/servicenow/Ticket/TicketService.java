package org.servicenow.Ticket;

import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.*;

import org.openqa.selenium.*;

import org.openqa.selenium.chrome.*;

import java.io.*;
import java.util.Date;


public class TicketService {
    public static void main(String[] args) throws EmailException, IOException, InterruptedException {

        Date date = new Date();

        String successMessage = "Successfully LoggedIn! " + date;
        String failedMessage = "Failed LoggedIn! " + date;

        String fileName = date.toString().replace(" ", "-").replace(":", "-");


        System.setProperty("webdriver.chrome.driver", "C:\\Users\\rdutta\\Documents\\Meta-CourseEra\\chromedriver_win32 (1)\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://login.opentext.com/login.do?host=https%3A%2F%2Fsso.otiam.opentext.com&ct_orig_uri=%2Ffed%2Fapp%2Fidp.saml20.sp.init.sso%3FentityID%3Dhttps%3A%2F%2Fsupport.opentext.com%26redirect%3Dsso&nonce=5046.1675677545088");
        driver.findElement(By.xpath("//*[@id=\"user\"]")).sendKeys("rdutta@opentext.com");
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("HaiderRam@123");
        driver.findElement(By.xpath("//*[@id=\"form1\"]/div[3]/button")).click();
        String url = driver.getCurrentUrl();


        if (url.equals("https://support.opentext.com/now/workspace/agent/home")) {
            System.out.println(successMessage);

            //If Successfully logged in we need to send the test report via mail


        } else {
            System.out.println(failedMessage);

            //If Failed logged in we need to send the test report via mail

            // login.emailService(failedMessage);

            //Screenshot
            File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenShot, new File(".//screenshot//" + fileName + ".png"));
        }


    }

}