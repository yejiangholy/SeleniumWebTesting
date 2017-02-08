package com.company;
import org.apache.log4j.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Main {
     static Logger log = Logger.getLogger("Main testing");
    static Appender append;
    static WebDriver driver = new ChromeDriver();
    static String baseUrl;

    public static void main(String[] args) {

        boolean part1 = false;
        boolean db = true;
        if(part1) {
            PropertyConfigurator.configure("log.properties");
            baseUrl = "http://52.11.193.136/";
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            driver.get(baseUrl);
            HomePage home = new HomePage(driver);
            home.testLoginUser("Ye Jiang", "sung753JY");

            checkAllLinkResponse checkHomeLinks = new checkAllLinkResponse(driver, baseUrl);
            checkHomeLinks.testLinkResponse();
        }

        if(db) {
            DatabaseTest dbTester = new DatabaseTest();
            dbTester.setUp();
            try {
                dbTester.test();
            }catch(Exception e){
                e.printStackTrace();
            }
            dbTester.close();
        }
     }
}
