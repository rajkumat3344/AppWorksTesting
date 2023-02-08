package org.example;

import junit.framework.TestCase;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.logging.Logger;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase {
    private Logger LOGGER = Logger.getLogger(this.getClass().getSimpleName());
    private WebDriver driver;

    public class AppWorksBasicTests {
        private Logger LOGGER = Logger.getLogger(this.getClass().getSimpleName());
        private WebDriver driver;

        @Before
        public void setUp() {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\rdutta\\Documents\\Meta-CourseEra\\chromedriver_win32 (1)\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.get("http://rduttapf26l0br.opentext.net:81/home/system/");
        }


        @Test
        public void loginLogoutTest() {
            LOGGER.info("loginTest");

            //The test implementation



            Assert.assertEquals("AppWorks Platform Login", driver.getTitle());


        }

        @After
        public void tearDown() {
            driver.quit();
        }
    }
}

