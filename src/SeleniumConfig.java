import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SeleniumConfig {

    public static void main(String[] args) throws InterruptedException{

        System.setProperty("webdriver.chrome.driver", "/Users/Volha.Lysiakova/IdeaProjects/Drivers/chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        String baseUrl = "https://www.testandquiz.com/selenium/testing.html";
        driver.get(baseUrl);

        Dimension size = driver.manage().window().getSize();
        System.out.println(size);
        Dimension newSize = new Dimension(1200,800);
        driver.manage().window().setSize(newSize);
        driver.manage().window().maximize();



        //Generate Confirm Box

        WebElement confirm = driver.findElement(By.xpath("/html/body/div/div[12]/div/p[1]/button"));
        confirm.click();
        Thread.sleep(20);
        driver.switchTo().alert().accept();
        String resultOk = "You pressed OK!";
        WebElement ok = driver.findElement(By.xpath("//*[@id=\"demo\"]"));

        if(resultOk.equals(ok.getText())){
            System.out.println("Test OK - Generate confirm box OK");
        }else{
            System.out.println("Test failed");
        }

        confirm.click();
        Thread.sleep(20);
        driver.switchTo().alert().dismiss();
        String resultNotOk = "You pressed Cancel!";
        if(resultNotOk.equals(ok.getText())){
            System.out.println("Test OK - Generate confirm box Cancel");
        }else{
            System.out.println("Test failed");
        }

        // Link

        WebElement link = driver.findElement(By.xpath("/html/body/div/div[4]/div/p/a"));
        link.click();
        driver.navigate().back();

        // Text Box and Confirm Button

        WebElement textBox = driver.findElement(By.id("fname"));
        textBox.clear();
        textBox.sendKeys("I need to go to Bali immediately");
        WebElement buttonSubmit = driver.findElement(By.id("idOfButton"));
        buttonSubmit.click();
        buttonSubmit.getAttribute("style");
        System.out.println(buttonSubmit.getAttribute("style"));
        String backgroundGreen = "background: green;";

        if(buttonSubmit.getAttribute("style").equals(backgroundGreen)){
            System.out.println("Test OK - Button green");
        }else{
            System.out.println("Test failed");
        }

        System.out.println(buttonSubmit.getCssValue("background-color"));
        String color = buttonSubmit.getCssValue("background-color");
        if(color.equals("rgba(0, 128, 0, 1)")){
            System.out.println("Test OK - Button green");
        }else{
            System.out.println("Test failed");
        }

        // Button size and location

        Dimension sizeButton = buttonSubmit.getSize();
        System.out.println("Size of Submit button is: " + sizeButton);

        Point location = buttonSubmit.getLocation();
        System.out.println("Location of Submit button is: " + location);


        // Radio button

        WebElement radioButtonMale = driver.findElement(By.id("male"));
        WebElement radioButtonFemale = driver.findElement(By.id("female"));
        radioButtonMale.click();
        if(radioButtonMale.isSelected() & radioButtonFemale.isEnabled()){
            System.out.println("Test OK - Radio button Male is selected");
        }else{
            System.out.println("Test failed - Radio button Male is selected");
        }

        radioButtonFemale.click();
        if(radioButtonFemale.isSelected() & radioButtonMale.isEnabled()){
            System.out.println("Test OK - Radio button Female is selected");
        }else{
            System.out.println("Test failed - Radio button Female is selected");
        }

        //Drop down

        WebElement dropDown = driver.findElement(By.id("testingDropdown"));
        Select dropSelect = new Select(dropDown);
        List<WebElement> dropDownElements = driver.findElements(By.tagName("option"));

        for(int i = 0; i<4; i++){
            dropSelect.selectByIndex(i);
            String dropDownAttribute = dropDown.getAttribute("value");
            if(dropDownAttribute.equals(dropDownElements.get(i).getAttribute("value"))){
                System.out.println("Test OK - " +dropDownAttribute + " drop down");
            }else{
                System.out.println("Test failed - drop down");
            }
        }

        //Drag and Drop

        WebElement from = driver.findElement((By.id("sourceImage")));
        WebElement to = driver.findElement((By.id("targetDiv")));
        Actions act = new Actions(driver);
        act
                .dragAndDrop(from,to)
                .build()
                .perform();

        // Double click

        Actions actionClick = new Actions(driver);
        WebElement doubleClick = driver.findElement(By.id("dblClkBtn"));
        actionClick.doubleClick(doubleClick).perform();
        driver.switchTo().alert().accept();







       // driver.close();

    }
}
