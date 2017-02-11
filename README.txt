
Basic Selenium web testing framework for a Drupal site 

Dependencies
 1. Java core library 
 2. Apache : log4j 
 3. Selenium WebDriver library 
 4. Add Selenium Chrome Driver exe file in your build path 
 5. Java SQL library 
 6. Java. net and HttpURLConnection lib 
 
 Configuration

 1. There are 6 boolean value in the top of Main class, which represent 6 independent test cases. You could set them to true  --run this test, or false -- skip this test. 

 2. There is also a log.properties file, which you should put the same level with the src folder. You also want to change the third line    log= **  and type your the location where you want to put your log file ( ex : Users/***/Desktop) 
