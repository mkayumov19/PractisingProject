package com.practice.step_defs;

import com.practice.utilities.Driver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class SingleChromeDriverInitiation {

    @Test
    public void test1(){

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https:google.com");
        driver.findElement(By.xpath("//a[@class='gb_3 gb_4 gb_9d gb_3c']")).click();
        driver.findElement(By.id("identifierId")).sendKeys("makhmudik19@gmail.com"+ Keys.ENTER);




    }

}
