package com.company;
import org.apache.log4j.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class Main {
     static Logger log = Logger.getLogger("Main testing");
    static Appender append;
    static WebDriver driver = new ChromeDriver();
    static String baseUrl;

    public static void main(String[] args) {
        boolean Adminlogin = true;
        boolean db = true;
        boolean postComment = true;
        boolean addNewArticle = true;
        boolean searchFunciton = true;
        boolean checkAllLinkStatus = true;
        if(Adminlogin) {
            setUp();
            Login home = new Login(driver);
            home.LoginUser("Ye Jiang", "sung753JY");
        }
        if(checkAllLinkStatus){
            checkAllLinkResponse checkHomeLinks = new checkAllLinkResponse(driver);
            checkHomeLinks.testLinkResponse();
            backToHomepage();
        }
        if(postComment){
            PostCommentTest postingComment = new PostCommentTest(driver,"Comment","test comment here","Test Title");
            postingComment.test();
            backToHomepage();
        }
        if(addNewArticle){
            AddContentTest addContent = new AddContentTest(driver,"Here is something new"," Everything will be fine and move forward");
            addContent.test();
            backToHomepage();
        }
        if(searchFunciton){
            SearchBarFunctionTest searchTest = new SearchBarFunctionTest(driver,"Algorithm");
            searchTest.test();
            backToHomepage();
        }
        if(db) {
            DatabaseTest dbTester = new DatabaseTest();
            try {
                dbTester.test();
                backToHomepage();
            }catch(Exception e){
                e.printStackTrace();}
        }
     }

    public static void backToHomepage(){
        WebElement element = driver.findElement(By.xpath("//*[@id=\"toolbar-home\"]/li/a/span"));
        element.click();
        log.info("back to home page");
    }
     public static void setUp(){
         // set up configuration of our log file and baseURL for this test
         PropertyConfigurator.configure("log.properties");
         baseUrl = "http://52.11.193.136/";
         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
         driver.get(baseUrl);
     }
}
