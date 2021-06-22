import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Classe di test che consente test automatizzati su varie piattaforme e varie
 * browser.
 * 
 * @author Simone Lungarella
 */
public class BrowserStackSampleTest {

  /**
   * Nome utente.
   */
  public static final String USERNAME = "simonelungarella1";

  /**
   * Password test automatizzati.
   */
  public static final String AUTOMATE_KEY = "Tuhsvga1P7Ksy1mmYUkK";

  /**
   * ULR raggiungimento servizio di test.
   */
  public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

  /**
   * Esegue le azioni prima del test.
   */
  @Before
  public final void beforeTest() {
    // Non occorre fare niente.
  }

  /**
   * Esegue le azioni a valle dell'esecuzione del test.
   */
  @After
  public final void afterTest() {
    // Non occorre fare niente .
  }

  /**
   * Effettua test su combinazioni diverse di browser e dispositivi.
   * 
   * @throws Exception
   */
  @Test
  public void stressTest() throws Exception {
   
    DesiredCapabilities caps = new DesiredCapabilities();

    caps.setCapability("os", "Windows");
    caps.setCapability("os_version", "10");
    caps.setCapability("browser", "Chrome");
    caps.setCapability("browser_version", "80");
    caps.setCapability("name", "simonelungarella1's First Test");

    WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
    driver.get("http://www.google.com");
    WebElement element = driver.findElement(By.name("q"));

    element.sendKeys("BrowserStack");
    element.submit();

    System.out.println(driver.getTitle());
    driver.quit();
  }
}