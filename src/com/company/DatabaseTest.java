package com.company;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.sql.*;

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
    static Logger log = Logger.getLogger("DatebaseTest");

    public void test(){
        try {
            setUp();
            testUserName();
            close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void setUp(){
        try{
            PropertyConfigurator.configure("log.properties");
            loginUserName = "Ye Jiang";
            //hard code user name for the purpose of this test
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
    public void testUserName()throws SQLException {
        String query = "select * from users";

        try{
            results = sta.executeQuery(query);
            while(results.next()){
                int id = results.getInt("uid");
                if(id == 1) {
                    String DBname = results.getString("name");

                   if(DBname.equals(loginUserName))log.info("--------------DB test passed");
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
