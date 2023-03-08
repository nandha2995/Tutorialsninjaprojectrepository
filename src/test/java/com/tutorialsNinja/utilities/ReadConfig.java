package com.tutorialsNinja.utilities;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

Properties pro;

public ReadConfig()
{
File src = new File("./resources/config.properties");

try {
FileInputStream fis = new FileInputStream(src);
pro = new Properties();
pro.load(fis);
} catch (Exception e) {
System.out.println("Exception is " + e.getMessage());
}
}
public String getBrowserName()
{
String browserName=pro.getProperty("browser");
return browserName;
}
public String getApplicationURL()
{
String url=pro.getProperty("baseUrl");
return url;
}


public String getTestDataPath()
{
String testDataPath=pro.getProperty("testDataPath");
return testDataPath;
}



}