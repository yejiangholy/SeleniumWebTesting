package com.company;
import org.apache.log4j.Appender;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.lang.*;

/**
 * Created by YeJiang on 2/6/17.
 *
 * pre-request : already navigate to the home page
 */
public  class HomePage {

    public static WebElement element;
    public static WebDriver driver;
    static Logger log = Logger.getLogger("Home page");
    static Appender appender;


    public HomePage(WebDriver driver){
        PropertyConfigurator.configure("log.properties");
        element = null;
        this.driver = driver;
        log.info("----------------------Testing  Homepage  ");
    }

    public static void fillUserName(String name){
        element =  driver.findElement(By.id("edit-name"));
        element.sendKeys(name);
        log.info("Enter user name : "+ name);
    }

    public static void fillUserPassword(String password){
        element = driver.findElement(By.id("edit-pass"));
        element.sendKeys(password);
        log.info("Enter user password : "+password);
    }

    public static void clickSubmit(){
        element = driver.findElement(By.id("edit-submit"));
        element.click();
        log.info("Click submit");
    }

    public static String getCurrentLoginUser(){

        String loginUser =  driver.findElement(By.cssSelector(".account > a:nth-child(1) > strong:nth-child(1)")).getText();
        log.info("Get current login user = "+ loginUser);
        return loginUser;

    }

    public static void logOutUser(){
        element = driver.findElement(By.linkText("Log out"));
        element.click();
        log.info("log out current user");
    }

    public static boolean LoginUser(String name, String pass){
       fillUserName(name);
        fillUserPassword(pass);
        clickSubmit();
        if(getCurrentLoginUser().equals(name)){
            log.info("user "+name+ " login success  ----- test passed");
            return true;
        }
        else {
            log.info("haven't found user  "+name+"   ----- test failed");
            return false;
        }
    }
}
