package tinder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class tinder {

    public static void main(String[] args)
            throws InterruptedException, FileNotFoundException {

        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\Nicholas\\Desktop\\selenium\\chromedriver_win32\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("http://www.tinder.com/");
        driver.manage().window().maximize();
        Thread.sleep(2000);

        // get second button element
        WebElement secondButtton = driver.findElement(By.xpath(
                "//*[@id=\"modal-manager\"]/div/div/div/div/div[3]/span/div[2]/button"));

        // print text
        System.out.println(secondButtton.getAttribute("aria-label"));
        String temp1 = secondButtton.getAttribute("aria-label");
        String temp2 = "Log in with Facebook";

        // check text
        if (temp1.equalsIgnoreCase(temp2)) {
            // click second button
            driver.findElement(By.xpath(
                    "//*[@id=\"modal-manager\"]/div/div/div/div/div[3]/span/div[2]/button"))
                    .click();
        } else {
            // more info
            driver.findElement(By.xpath(
                    "//*[@id=\"modal-manager\"]/div/div/div/div/div[3]/span/button"))
                    .click();

            // third button
            driver.findElement(By.xpath(
                    "//*[@id=\"modal-manager\"]/div/div/div/div/div[3]/span/div[3]/button"))
                    .click();
        }

        String tinder = driver.getWindowHandle();
        for (String handle1 : driver.getWindowHandles()) {
            driver.switchTo().window(handle1);
        }
        Thread.sleep(2000);

        // jar
        Scanner scan = new Scanner(new File("facebook_login.txt"));

        // local
//        Scanner scan = new Scanner(new File("src/tinder/facebook_login.txt"));
        Thread.sleep(2000);

        driver.findElement(By.xpath("//*[@id=\"email\"]"))
                .sendKeys(scan.next());
        driver.findElement(By.xpath("//*[@id=\"pass\"]")).sendKeys(scan.next());

        driver.findElement(By.id("u_0_0")).click();

        driver.switchTo().window(tinder);
        Thread.sleep(2000);

        // i accept
        System.out.println("accept");

        driver.findElement(By
                .xpath("//*[@id=\"content\"]/div/div[2]/div/div/div[1]/button"))
                .click();
        Thread.sleep(2000);

        // some how this fixes the bug tf idfk
        // if i comment this out there will be error line 57
        List<WebElement> listOfElements = driver
                .findElements(By.tagName("button"));
        System.out.println(listOfElements.size());
        Thread.sleep(2000);

        // allow
        System.out.println("allow");
        driver.findElement(By.xpath(
                "//*[@id=\"modal-manager\"]/div/div/div/div/div[3]/button[@aria-label='Allow']"))
                .click();
        Thread.sleep(2000);

        // enable
        System.out.println("enable");
        driver.findElement(By.xpath(
                "//*[@id=\"modal-manager\"]/div/div/div/div/div[3]/button[@aria-label='Enable']"))
                .click();
        Thread.sleep(2000);

        // no thanks
        System.out.println("no thanks");
        driver.findElement(By.xpath(
                "//*[@id=\"modal-manager\"]/div/div/div[2]/button[@aria-label='Close']"))
                .click();
        Thread.sleep(2000);

        for (int i = 0; i < 100; i++) {
            try {
                // like button
                driver.findElement(By.xpath(
                        "//*[@id=\"content\"]/div/div[1]/div/main/div[1]/div/div/div[1]/div[1]/div[2]/div[4]/button"))
                        .click();
            } catch (Exception e) {
                try {
                    // dismiss pop up
                    driver.findElement(By.xpath(
                            "//*[@id=\"modal-manager\"]/div/div/div[2]/button[2]"))
                            .click();
                } catch (Exception f) {
                    try {
                        // dismiss match
                        driver.findElement(By.xpath(
                                "//*[@id=\"modal-manager-canvas\"]/div/div/div[1]/div/div[3]/a"))
                                .click();
                    } catch (Exception g) {
                        System.out.println("riperoni error - out of likes?");
                        break;
                    }
                }
            }
            Thread.sleep(1500);
        }
        scan.close();
    }
}
