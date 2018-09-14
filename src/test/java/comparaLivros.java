package CompararLivros;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;

public class comparaLivros {
    @Test
    public void testCompararLivros() throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "C:\\Driver\\gecko\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.submarino.com.br/categoria/livros");

        driver.findElement(By.className("card-product-figure")).click();
        String autorSubmarino = driver.findElement(By.xpath("//div[@id='info-section']/div[2]/section/div/div/div[3]/table/tbody/tr[3]/td[2]")).getText();
        String isbnLivro = driver.findElement(By.xpath("//div[@id='info-section']/div[2]/section/div/div/div[3]/table/tbody/tr[5]/td[2]")).getText();

        System.out.println("O autor é: " + autorSubmarino);
        driver.quit();

        driver = new FirefoxDriver();
        driver.get("https://www.amazon.com.br/Livros/b/ref=topnav_storetab_b?ie=UTF8&node=6740748011");

        driver.findElement(By.linkText("Pesquisa avançada")).click();
        driver.findElement(By.id("field-isbn")).click();
        driver.findElement(By.id("field-isbn")).sendKeys(isbnLivro);
        driver.findElement(By.name("Adv-Srch-Books-Submit")).submit();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        String autorAmazon = driver.findElement(By.xpath("//div[2]/div/div/div/div/div/div/span[2]")).getText();
        assertEquals(autorSubmarino, autorAmazon);

        driver.quit();

        driver = new FirefoxDriver();
        driver.get("https://www.americanas.com.br/busca/" + isbnLivro);

        driver.findElement(By.xpath("//div[@id='content-middle']/div[5]/div/div/div/div/div/section/a/div/figure/div/div/picture/img")).click();
        String autorAmericanas = driver.findElement(By.xpath("//div[@id='info-section']/div[2]/section/div/div/div[3]/table/tbody/tr[3]/td[2]")).getText();

        assertEquals(autorSubmarino, autorAmericanas);

        driver.quit();
    }
}


