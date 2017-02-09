package com.company;
import org.apache.log4j.Appender;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YeJiang on 2/8/17.
 *
 * pre-request : User already login successfully
 */
public class PostCommentTest {


    public static WebElement element;
    public static WebDriver driver;
    static Logger log = Logger.getLogger("SearchFunction");
    static String subject;
    static String content;
    static String article;
    static Appender appender;

    public PostCommentTest(WebDriver driver, String subject, String content, String article){
        PropertyConfigurator.configure("log.properties");
        element = null;
        this.subject = subject;
        this.content = content;
        this.article = article;
        this.driver = driver;
        log.info("----------------------Testing posting comment");
    }

    public void test(){
        navigateToArticle();
        inputSubject();
        inputComment();
        submitComment();
        testCommentExist();
    }

    public void navigateToArticle(){
        element = driver.findElement(By.linkText(article));
        element.click();
        log.info("navigate to "+ article + "page");
    }

    public void inputSubject(){
        element = driver.findElement(By.id("edit-subject"));
        element.sendKeys(subject);
        log.info("input subject : " + subject);
    }

    public void inputComment(){
        element = driver.findElement(By.id("edit-comment-body-und-0-value"));
        element.sendKeys(content);
        log.info("input comment : " + content);
    }

    public void submitComment(){

        element = driver.findElement(By.id("edit-submit"));
        element.click();
        log.info("submit this comment");
    }

    public boolean testCommentExist(){
        List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"comments\"]//p"));
        List<String> comments = new ArrayList<>();

        for(WebElement comment : elements)comments.add(comment.getText());

        for(String text : comments)if(text.equals(content)){
            log.info("found the comment we just posted  --- test passed");
            return true;
        }
        log.info("haven't found the content we just posted -- test failed");
        return false;
    }
}
