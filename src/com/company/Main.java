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
        boolean login = true;
        boolean db = false;
        boolean postComment = false;
        boolean addNewArticle = false;
        boolean searchFunciton = true;
        if(login) {
            PropertyConfigurator.configure("log.properties");
            baseUrl = "http://52.11.193.136/";
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            driver.get(baseUrl);
            HomePage home = new HomePage(driver);
            home.LoginUser("Ye Jiang", "sung753JY");

//            checkAllLinkResponse checkHomeLinks = new checkAllLinkResponse(driver, baseUrl);
//            checkHomeLinks.testLinkResponse();
        }
        if(db) {
            DatabaseTest dbTester = new DatabaseTest();
            try {
                dbTester.test();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        if(postComment){
            PostCommentTest postingComment = new PostCommentTest(driver,"Comment","test comment here","Test Title");
            postingComment.test();
        }
        if(addNewArticle){
            AddContentTest addContent = new AddContentTest(driver,"Here is something new"," Everything will be fine and move forward");
            addContent.test();
        }
        if(searchFunciton){
            SearchBarFunctionTest searchTest = new SearchBarFunctionTest(driver,"Algorithm");
            searchTest.test();
        }
     }
}
