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


public class Admin {
    public static void main(String[] args) throws EmailException, IOException, InterruptedException {

        Date date = new Date();

        String successMessage = "Successfully LoggedIn! "+date;
        String failedMessage = "Failed LoggedIn! "+date;
        String active = "The solution is active.";
        String unavailable = "Solution is unavailable";
        String waring = "Solution has warnings.";
        int full = 100;

        String fileName = date.toString().replace(" ","-").replace(":","-");


        System.setProperty("webdriver.chrome.driver","C:\\Users\\rdutta\\Documents\\Meta-CourseEra\\chromedriver_win32 (1)\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://dmhc-ds-dev2.opentext.cloud/otdsws/login?RFA=4c744013-1dd5-4322-bb08-85bc81cae390%3Ahttps%3A%2F%2Fdmhc-aw2-dev2.opentext.cloud%2Fhome%2Fnimbus%2Fcom.eibus.sso.otds.TicketConsumerService.wcp%3FAuthContext%3D2.bmltYnVzPW90ZHM%3D%26RelayState%3D%2Fhome%2Fnimbus%2F&PostTicket=true&language=en-US");
        driver.findElement(By.xpath("//*[@id=\"otds_username\"]")).sendKeys("otadmin@otds.admin");
        driver.findElement(By.xpath("//*[@id=\"otds_password\"]")).sendKeys("jvcktaH#j6rk9$oKd");
        driver.findElement(By.xpath("//*[@id=\"loginbutton\"]")).click();

        driver.get("https://dmhc-aw2-dev2.opentext.cloud/home/nimbus/app/admin#");
        String url = driver.getCurrentUrl();


        if(url.equals("https://dmhc-aw2-dev2.opentext.cloud/home/nimbus/app/admin#")){
            System.out.println(successMessage+"\n");
            System.out.println("==============****=============="+"\n");


            //driver.manage().timeouts().implicitlyWait( 4, TimeUnit.SECONDS);

            WebDriverWait waitForSolutionLoad = new WebDriverWait(driver, 7);
            waitForSolutionLoad.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='solution']")));
            List solCount = driver.findElements(By.xpath("//div[@class='solution']"));

            //System.out.println("Solution Count::  "+solCount.size());





            //As here we have taken the solution count we need check each and every solution status-image is either available or warning else if unavailable then
            //click the solution and wait till it load completely and then take screenshot of the page.

            if(solCount.size() != 0){
                int index = 1;
                while(index<=solCount.size()){
                    //if the status-image is tick mark
                    //then print solution is available and index++;
                    String path = "//*[@id=\"solutions\"]/div/div/div[%d]";
                    String newString = String.format(path, index);


                    if(driver.findElement(By.xpath(newString)).getText().equals(driver.findElement(By.xpath(newString)).getText())){
                        if(driver.findElement(By.cssSelector("img.status-image")).getAttribute("title").equals(active)){
                            if(driver.findElement(By.xpath(newString)).getText().equals("OpenTextEntityIdentityComponents") || driver.findElement(By.xpath(newString)).getText().equals("OpenTextEntityTaskOutcomeComponents") || driver.findElement(By.xpath(newString)).getText().equals("OpenTextEntityWorkflowTemplateComponents") || driver.findElement(By.xpath(newString)).getText().equals("OpenTextInboxTaskManagement")){
                                continue;
                            }else{
                                System.out.println(driver.findElement(By.xpath(newString)).getText()+" ==>> "+active);
                                System.out.println("\n");

                                System.out.println("\n");
                                System.out.println("=========="+index+" of "+(solCount.size()-4)+" completed. "+"==========");
                                System.out.println("\n");

                                if(index == (solCount.size()-4)){
                                    System.out.println("\n");
                                    System.out.println("========== Solution healthcheck is "+full+"% completed. ==========");
                                }

                                index = index+1;
                            }
                        }else if(driver.findElement(By.cssSelector("img.status-image")).getAttribute("title").equals(waring)){

                            //solution with warning!
                            System.out.println("Warning!!");
                            break;
                        }else if(driver.findElement(By.cssSelector("img.status-image")).getAttribute("title").equals(unavailable)){
                            File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                            FileUtils.copyFile(screenShot, new File(".//screenshot//"+fileName+".png"));

                            //unavailable solution
                            System.out.println(driver.findElement(By.xpath(newString)).getText()+" ==>> "+unavailable);
                            System.out.println("\n");

                            index++;
                        }
                    }
                }
            }else{
                System.out.println("The Solution count :: not found :: "+solCount.size());
                System.out.printf("Please stop and re-run the program.");
                driver.quit();
            }

        }else{
            System.out.println(failedMessage);

            //Screenshot
            File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenShot, new File(".//screenshot//"+fileName+".png"));
        }

        driver.quit();


    }

}