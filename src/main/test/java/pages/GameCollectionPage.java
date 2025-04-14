package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class GameCollectionPage {
    WebDriver driver;
    WebDriverWait wait;

    public GameCollectionPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    //Список с карточками
    @FindBy(css = ".ant-card")
    List<WebElement> gameCards;

    //Строка пагинации
    @FindBy(css = ".ant-pagination")
    WebElement pagination;

    //Список с элементами пагинации
    @FindBy(css = ".ant-pagination-item")
    List<WebElement> paginationItems;

    //Фильтр 'Filter by platform'
    @FindBy(css = ".ant-select-selector")
    WebElement platformDropdown;

    //Категория 'Browser' в фильтре 'Filter by platform'
    @FindBy(xpath = "//div[@class='ant-select-item-option-content' and text()='Browser']")
    WebElement browserOption;

    //Название игры в карточке
    @FindBy(css = ".ant-card-body h1.ant-typography")
    WebElement gameName;

    //Вторая страница пагинации
    @FindBy(xpath = "//li[@title='2']")
    WebElement secondPage;


    // Метод для клика по первой карточке игры
    public void openFirstGameCard() {
        wait.until(driver -> gameCards.size() > 0);
        WebElement firstCard = gameCards.get(0);
        wait.until(driver -> firstCard.isDisplayed() && firstCard.isEnabled());
        firstCard.click();
    }

    // Метод для получения значения последней страницы пагинации
    public int getLastPageValue() {
        wait.until(driver -> pagination.isDisplayed());
        if (!paginationItems.isEmpty()) {
            String text = paginationItems.get(paginationItems.size() - 1).getText();
            return Integer.parseInt(text);
        }
        return 0;
    }

    // Метод для выбора платформы "Browser" через выпадающий список
    public void selectPlatformBrowser() {
        wait.until(driver -> platformDropdown.isDisplayed());
        platformDropdown.click();
        wait.until(driver -> browserOption.isDisplayed() && browserOption.isEnabled());
        browserOption.click();
    }

    // Метод для получения названия игры из карточки
    public String getNameOfGame() {
        wait.until(ExpectedConditions.visibilityOf(gameName));
        return gameName.getText();
    }

    // Метод для перехода на вторую странцицу пагинации
    public void openSecondPage() {
        wait.until(ExpectedConditions.elementToBeClickable(secondPage));
        secondPage.click();
    }
}
