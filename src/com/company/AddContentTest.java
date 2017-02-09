package com.company;
import org.apache.log4j.Appender;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

/**
 * Created by YeJiang on 2/8/17.
 *
 * pre - request user already login
 */
public class AddContentTest {
    public static WebElement element;
    public static WebDriver driver;
    static Logger log = Logger.getLogger("AddNewContent");
    static String title;
    static String content;
    static Appender appender;



    public AddContentTest(WebDriver driver, String title, String content){
        PropertyConfigurator.configure("log.properties");
        element = null;
        this.title = title;
        this.content = content;
        this.driver = driver;
        log.info("----------------------Testing adding new content");
    }
// post a content  --> enter save --> back to homepage --> go to content page --> check article exist
    public  void test(){
        addNewArticle();
        backToHomepage();
        testShowNewArticle();
    }
    public  void addNewArticle(){
        element = driver.findElement(By.linkText("Add content"));
        element.click();
        element = driver.findElement(By.linkText("Article"));
        element.click();

        // this step is very crucial, because if we do not switch to this frame, web driver unable to find any related elements
        element = driver.findElement(By.xpath("//*[@id=\"overlay-container\"]/iframe[1]"));
        driver.switchTo().frame(element);

        element = driver.findElement(By.id("edit-title"));
        element.sendKeys(title);

        element = driver.findElement(By.id("edit-field-tags-und"));
        element.sendKeys("Test");

        element = driver.findElement(By.id("edit-body-und-0-value"));
        element.sendKeys(content);

        element = driver.findElement(By.id("edit-submit"));
        element.click();

        try{Thread.sleep(3000);}catch(Exception e){} // add new content do need some response time, web Driver always goes to fast
        //driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); --> I tried implicit wait, which didn't work

        // we also need to switch back to default frame, in order to click the home button
        driver.switchTo().defaultContent();
        log.info("Add a new article with title : "+title );
        log.info("with content : "+ content);
    }

    public  void backToHomepage(){
        element = driver.findElement(By.xpath("//*[@id=\"toolbar-home\"]/li/a/span"));
        element.click();
        log.info("back to home page");
    }
    public  boolean testShowNewArticle(){
        try{Thread.sleep(1000);}catch(Exception e){}
        element = driver.findElement(By.id("toolbar-link-admin-content"));
        element.click();

        element = driver.findElement(By.xpath("//*[@id=\"overlay-container\"]/iframe[2]"));
        driver.switchTo().frame(element);
        try{
            element = driver.findElement(By.linkText(title));
            if(element != null){
                log.info("found newly added article    ----- test passed");
                return true;
            }
        }catch(Exception e)
        {
            log.info("haven't found newly added article  ----- test failed");
        }
        return false;
    }
}
