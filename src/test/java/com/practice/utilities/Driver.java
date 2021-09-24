package com.practice.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Driver {

    /*
      Creating private constructor so its class' object is not reachable from outside
       */
    private Driver() {
    }

    /*
   Making webdriver private so, it's not reachable from outside the class
   Making it static so, we can run it before everything else and, we can use it in static method
    */
    private static ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();

    /*
   Creating re-usable utility method that will return same 'driver' instance everytime we call it.
    */
    public static WebDriver getDriver() {

        if (driverPool.get() == null) {
            synchronized (Driver.class) {

            /*
            We read the browser type from configuration.properties file using .getProperty method we are creating
            in ConfigurationReader class
             */
                String browserType = com.practice.utilities.ConfigurationReader.getProperty("browser");

            /*
            Depending on the browser type our switch statement will open specific type of driver
             */
                switch (browserType) {
                    case "chrome":
                        WebDriverManager.chromedriver().setup();
                        ChromeOptions chromeOptions = new ChromeOptions();
                        chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                        chromeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
                        driverPool.set(new ChromeDriver(chromeOptions));
                        driverPool.get().manage().window().maximize();
                        driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                        break;

                    case "remote-chrome":
                        try {
                            String ipAddress = "3.83.189.248";
                            URL url = new URL("http://" + ipAddress + ":4444/wd/hub");
                            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                            desiredCapabilities.setBrowserName("chrome");
                            driverPool.set(new RemoteWebDriver(url, desiredCapabilities));
                            driverPool.get().manage().window().maximize();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;

                    case "remoteChromeSSL":

                        WebDriverManager.chromedriver().setup();
                        ChromeOptions chromeOptions1 = new ChromeOptions();
                        chromeOptions1.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                        chromeOptions1.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
                        try {
                            String ipAddress = "3.83.189.248";
                            URL url = new URL("http://" + ipAddress + ":4444/wd/hub");
                            DesiredCapabilities desiredCapabilities = new DesiredCapabilities(chromeOptions1);
                            desiredCapabilities.setBrowserName("chrome");
                            driverPool.set(new RemoteWebDriver(url, desiredCapabilities));
                            driverPool.get().manage().window().maximize();
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "firefox":
                        WebDriverManager.firefoxdriver().setup();
                        driverPool.set(new FirefoxDriver());
                        driverPool.get().manage().window().maximize();
                        driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                        break;
                }
            }
        }
        return driverPool.get();
    }

    public static void closeDriver() {
        if (driverPool.get() != null) {
            driverPool.get().quit();
            driverPool.remove();
        }
    }
}
