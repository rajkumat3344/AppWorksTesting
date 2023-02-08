package org.example.LoginPage;

import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.*;

import org.openqa.selenium.*;

import org.openqa.selenium.chrome.*;

import java.io.*;
import java.util.Date;


public class Login {
    public static void main(String[] args) throws EmailException, IOException, InterruptedException {

        Date date = new Date();

         String successMessage = "Successfully LoggedIn! "+date;
         String failedMessage = "Failed LoggedIn! "+date;

         String fileName = date.toString().replace(" ","-").replace(":","-");


        System.setProperty("webdriver.chrome.driver","C:\\Users\\rdutta\\Documents\\Meta-CourseEra\\chromedriver_win32 (1)\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://dmhc-ds-dev2.opentext.cloud/otdsws/login?RFA=4c744013-1dd5-4322-bb08-85bc81cae390%3Ahttps%3A%2F%2Fdmhc-aw2-dev2.opentext.cloud%2Fhome%2Fnimbus%2Fcom.eibus.sso.otds.TicketConsumerService.wcp%3FAuthContext%3D2.bmltYnVzPW90ZHM%3D%26RelayState%3D%2Fhome%2Fnimbus%2F&PostTicket=true&language=en-US");
        driver.findElement(By.xpath("//*[@id=\"otds_username\"]")).sendKeys("otadmin@otds.admin");
        driver.findElement(By.xpath("//*[@id=\"otds_password\"]")).sendKeys("jvcktaH#j6rk9$oKd");
        driver.findElement(By.xpath("//*[@id=\"loginbutton\"]")).click();
        String url = driver.getCurrentUrl();

        Login login = new Login();


        if(url.equals("https://dmhc-aw2-dev2.opentext.cloud/home/nimbus/")){
            System.out.println(successMessage);

            //If Successfully logged in we need to send the test report via mail

           login.emailService(successMessage);

        }else{
            System.out.println(failedMessage);

            //If Failed logged in we need to send the test report via mail

            // login.emailService(failedMessage);

            //Screenshot
            File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenShot, new File(".//screenshot//"+fileName+".png"));
        }



    }

    private void emailService(String successMessage) throws EmailException {
        Email email = new SimpleEmail();
        email.setHostName("smtp.office365.com");
        email.setSmtpPort(587);
        email.setAuthenticator(new DefaultAuthenticator("rdutta@opentext.com", "HaiderRam@123"));
        email.setSSLOnConnect(true);
        email.setFrom("rdutta@opentext.com");
        email.setSubject("Login Access Test");
        email.setMsg(successMessage);
        email.addTo("nandanik@opentext.com");
        email.send();
    }

}