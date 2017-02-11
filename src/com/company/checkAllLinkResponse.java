package com.company;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;

/**
 * Created by YeJiang on 2/7/17.
 */
public class checkAllLinkResponse {
    private static WebDriver driver;
    static Logger log = Logger.getLogger("checkAllLinkStatus");

    public checkAllLinkResponse(WebDriver driver){
        driver.get("http://52.11.193.136/");
        PropertyConfigurator.configure("log.properties");
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        log.info("-----------------check all link status ");
    }

    public void testLinkResponse(){
        List<WebElement> Links = getClickableLinks();
        for(WebElement link : Links){
            String absUrl = link.getAttribute("href");
            try{
                log.info(absUrl+" returned : "+ linkStatus(new URL(absUrl)));
            }
            catch(Exception e){
                log.warn(e.getMessage());
            }

        }
      // driver.quit();
    }

    public static List<WebElement> getClickableLinks(){
        List<WebElement> linksToClick = new ArrayList<>();
        List<WebElement> elements = driver.findElements(By.tagName("a"));
        elements.addAll(driver.findElements(By.tagName("img")));

        for(WebElement e : elements){

            if(e.getAttribute("href") != null)linksToClick.add(e);
        }
        return linksToClick;
    }

    public static String linkStatus(URL url){
        try{
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            http.connect();
            String response = http.getResponseMessage();
            http.disconnect();
            return response;
        }
        catch(Exception e){
            return e.getMessage();
        }
    }
}
