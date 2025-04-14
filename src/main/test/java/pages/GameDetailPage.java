package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class GameDetailPage {
    WebDriver driver;

    public GameDetailPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Метод валидации: проверка, что URL содержит "game"
    public boolean isPageOpened() {
        return driver.getCurrentUrl().contains("game");
    }
}
