package org.example.AdminPage;

import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.*;

import org.openqa.selenium.*;

import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.*;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class ContainerCheck {
    public static void main(String[] args) throws EmailException, IOException, InterruptedException {

        Date date = new Date();

        String successMessage = "Successfully LoggedIn! "+date;
        String failedMessage = "Failed LoggedIn! "+date;

        String CUSP = "We are in CUSP page!"+date;

        String fileName = date.toString().replace(" ","-").replace(":","-");


        System.setProperty("webdriver.chrome.driver","C:\\Users\\rdutta\\Documents\\Meta-CourseEra\\chromedriver_win32 (1)\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://dmhc-aw2-dev2.opentext.cloud/home/nimbus/");
        driver.findElement(By.xpath("//*[@id=\"otds_username\"]")).sendKeys("otadmin@otds.admin");
        driver.findElement(By.xpath("//*[@id=\"otds_password\"]")).sendKeys("jvcktaH#j6rk9$oKd");
        driver.findElement(By.xpath("//*[@id=\"loginbutton\"]")).click();
        String url = driver.getCurrentUrl();


        if(url.equals("https://dmhc-aw2-dev2.opentext.cloud/home/nimbus")){
            System.out.println(successMessage+"\n");
            System.out.println("==============****=============="+"\n");


            //driver.manage().timeouts().implicitlyWait( 4, TimeUnit.SECONDS);

            WebDriverWait waitForResourceManagerLoad = new WebDriverWait(driver, 10);
            waitForResourceManagerLoad.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='solution']")));
            driver.findElement(By.xpath("//div[@class='solution']")).click();

            //System.out.println("Solution Count::  "+solCount.size());





            //As here we have taken the solution count we need check each and every solution status-image is either available or warning else if unavailable then
            //click the solution and wait till it load completely and then take screenshot of the page.



        }else{
            System.out.println(failedMessage);

            //Screenshot
            File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenShot, new File(".//screenshot//"+fileName+".png"));
        }

        driver.quit();


    }

}