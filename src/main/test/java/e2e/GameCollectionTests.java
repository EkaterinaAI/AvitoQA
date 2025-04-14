package e2e;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.GameCollectionPage;
import pages.GameDetailPage;

import static org.junit.jupiter.api.Assertions.*;

public class GameCollectionTests {

    WebDriver driver;
    GameCollectionPage collectionPage;
    GameDetailPage detailPage;

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-proxy-server", "--proxy-server='direct://'", "--proxy-bypass-list=*");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://makarovartem.github.io/frontend-avito-tech-test-assignment/");

        collectionPage = new GameCollectionPage(driver);
        detailPage = new GameDetailPage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            driver.quit();
        }
    }

    // Тест-кейс: Открытие карточки игры
    @Test
    public void testOpenGameCard() {
        collectionPage.openFirstGameCard();

        assertTrue(detailPage.isPageOpened(), "Карточка игры не открылась");
    }

    // Тест-кейс: Отображение разного количества карточек игр на странице поиска
    @Test
    public void testDisplayDifferentNumberOfGameCards() {
        int lastPageValueBefore = collectionPage.getLastPageValue();
        collectionPage.selectPlatformBrowser();
        int lastPageValueAfter = collectionPage.getLastPageValue();

        assertTrue(lastPageValueBefore > lastPageValueAfter, "Количество страниц не уменьшилось");
    }

    // Тест-кейс: Переход по страницам результата поиска с помощью пагинации
    @Test
    public void testPagination() {
        collectionPage.selectPlatformBrowser();
        String firstPageGameName = collectionPage.getNameOfGame();
        collectionPage.openSecondPage();
        String secondPageGameName = collectionPage.getNameOfGame();

        assertNotEquals(firstPageGameName, secondPageGameName, "Переход на вторую страницу пагинации не произошел");
    }
}
