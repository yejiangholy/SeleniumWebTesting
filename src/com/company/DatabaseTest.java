package com.company;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by YeJiang on 2/8/17.
 */
public class DatabaseTest {

    static Connection conn = null;
    private static Statement sta;
    private static ResultSet results = null;

    public static String DB_URL =  "jdbc:mysql://drupal1.cjdyxtdazjt1.us-west-2.rds.amazonaws.com:3306/Drupal1";
    public static String DB_user = "Ye_Jiang";
    public static String DB_password = "sung753JY";
    public static String driver = "com.mysql.jdbc.Driver";
    public static WebDriver webDriver = new ChromeDriver();
    public static String loginUserName = "";

    public void setUp(){
        try{

            // Login set up
            String baseUrl = "http://52.11.193.136/";
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            webDriver.get(baseUrl);
            HomePage home = new HomePage(webDriver);
            home.LoginUser("Ye Jiang","sung753JY");
            loginUserName = home.getCurrentLoginUser();

            Class.forName(driver).newInstance();
            // get connect to database
            conn = DriverManager.getConnection(DB_URL,DB_user,DB_password);
           // create statement object
            sta = conn.createStatement();

            // WebDriver set up
            webDriver = new ChromeDriver();


        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


    public void test()throws SQLException {
        String query = "select * from users";

        try{
            results = sta.executeQuery(query);
            while(results.next()){
                int id = results.getInt("uid");
                if(id == 1) {
                    String DBname = results.getString("name");

                   if(DBname.equals(loginUserName))System.out.println("DB check success");
                }
            }
        }
        catch(SQLException se){
            se.printStackTrace();
        }
    }

    public void close(){
        try {results.close();}
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
