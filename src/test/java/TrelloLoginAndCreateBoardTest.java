import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by sunny on 16/04/18.
 */
public class TrelloLoginAndCreateBoardTest {
    WebDriver driver;
    Logger logger = null;
    private String addDescriptionToCard = "";

    @Test
    /*
    This block of code will login and create a new board
     */
    public void TrelloLogin(){
        logger = Logger.getLogger(this.getClass());
        System.setProperty("webdriver.chrome.driver","/Users/sunny/Documents/Selenium_Demo/chromedriver");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String url = "https://trello.com/";
        logger.info(url);
        driver.get(url);
        logger.info("Click on the Login Button");
        driver.findElement(By.linkText("Log In")).click();

        String userNameLocator = "user";
        String passwordLocator = "password";
        String userName = "xyz@gmail.com";
        String password = "password";
        String loginButtonLocator = "login";

        driver.findElement(By.id(userNameLocator)).click();
        driver.findElement(By.id(userNameLocator)).clear();

        logger.info("Enter UserName "+ userName);
        driver.findElement(By.id(userNameLocator)).sendKeys(userName);
        driver.findElement(By.id(passwordLocator)).sendKeys(password);
        driver.findElement(By.id(loginButtonLocator)).click();

        logger.info("Click on the Create New Board");
        String createNewBoardLocator = ".board-tile.mod-add>span";
        driver.findElement(By.cssSelector(createNewBoardLocator)).click();

        String addBoardTittle = ".subtle-input";
        String test = "Testing";
        logger.info("Adding Board Title As "+test);
        driver.findElement(By.cssSelector(addBoardTittle)).sendKeys(test);

        logger.info("Click on the button To Select Private Or Public");
        driver.findElement(By.cssSelector(".subtle-chooser-trigger.unstyled-button.vis-chooser-trigger")).click();
        String selectAsPublicLocaotr = ".//*[@id='classic']/div[5]/div/div[2]/div/ul/li[2]/a/span[3]";
        driver.findElement(By.xpath(selectAsPublicLocaotr)).click();

        logger.info("Click on Create Board");
        String createBoardLocator = ".primary";
//        driver.findElement(By.cssSelector(createBoardLocator)).click();

    }


    @Test
    /*
    This block of code will add or create a new card
     */
    public void addCard(){

        String toDoLocator = ".//*[@id='board']/div[1]/div/a";
        logger.info("Click on Add a card link of ToDo Table");
        driver.findElement(By.xpath(toDoLocator)).click();

        String addDescriptionToCardLocator = ".list-card-composer-textarea.js-card-title";
        addDescriptionToCard = "Create Card";
        logger.info("Writing description "+addDescriptionToCard+" to card");
        driver.findElement(By.cssSelector(addDescriptionToCardLocator)).sendKeys(addDescriptionToCard);

        String addLocator = ".primary.confirm.mod-compact.js-add-card";
        logger.info("Click on Add Button After writing a description to card");
        driver.findElement(By.cssSelector(addLocator)).click();
        String closeAddPopUp = ".icon-lg.icon-close.dark-hover.js-cancel";
        driver.findElement(By.cssSelector(closeAddPopUp)).click();

    }


    @Test
    /*
    This block of code drag item from one table to ther
     */
    public void moveCardFromOneListToOther(){
        logger.info("Drag option :-"+addDescriptionToCard);
        WebElement dragFrom = driver.findElement(By.linkText(addDescriptionToCard));
        WebElement dragTo = driver.findElement(By.xpath(".//*[@id='board']/div[2]"));

        Actions builder = new Actions(driver);
        Action drapAndDrop = builder.clickAndHold(dragFrom)
                .moveToElement(dragTo)
                .release(dragTo).build();
        drapAndDrop.perform();

    }

    @Test
    public void showMenuAndInvite(){
        String showMenuLinkLocator = "Show Menu";
        String closeShowMenuLinkLocator = ".board-menu-header-close-button.icon-lg.icon-close.js-hide-sidebar";
        String inviteLocator = ".button-link.mod-full.js-simple-add-members.js-open-manage-board-members";
        String searchMemberLocator = ".js-search-input.js-autofocus";
        String searchMember = "sunny menghani";

        logger.info("Click on link:- "+showMenuLinkLocator);
        driver.findElement(By.linkText(showMenuLinkLocator)).click();

        logger.info("Click on Invite Link");
        driver.findElement(By.cssSelector(inviteLocator)).click();

        logger.info("Searching Member "+searchMember);
        driver.findElement(By.cssSelector(searchMemberLocator)).click();
        driver.findElement(By.cssSelector(searchMemberLocator)).clear();
        driver.findElement(By.cssSelector(searchMemberLocator)).sendKeys(searchMember);



    }


    @AfterClass
    public void closeBrowser(){
        logger.info("Closing the browser");
        driver.quit();
    }


}
