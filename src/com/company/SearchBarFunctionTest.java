package com.company;
import org.openqa.selenium.By;
import org.apache.log4j.Appender;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by YeJiang on 2/9/17.
 *
 * pre-request user log in otherwise there is no search bar on our website
 */
public class SearchBarFunctionTest {

    public static WebElement element;
    public static WebDriver driver;
    static Logger log = Logger.getLogger("PostComment");
    static String query;
    static Appender appender;

    public SearchBarFunctionTest(WebDriver driver, String query){
        PropertyConfigurator.configure("log.properties");
        element = null;
        this.query = query;
        this.driver = driver;
        log.info("----------------------Testing search function");
    }
    public void test(){
        inputSearchContent();
        testGetResult();
    }

    public void inputSearchContent(){
        // input query
        element = driver.findElement(By.id("edit-search-block-form--2"));
        element.sendKeys(query);
        // click search button
        element = driver.findElement(By.id("edit-submit"));
        element.click();
        log.info("search : "+query + " on main search bar");
        //wait 2s for our website response
        try{Thread.sleep(2000);}catch(Exception e){}
    }
    public void testGetResult(){
        try{
            element = driver.findElement(By.partialLinkText(query));
            if(element != null){
                log.info("found related  article  ----- test passed");
            }
        }catch(Exception e)
        {
            log.info("haven't found related content for query : " + query);
        }
    }
}
