package kz.nee.mantis.tests;

import kz.nee.mantis.appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;


public class TestBase {

  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
    app.ftp().upload(new File("src/test/resources/config_defaults_inc.php"), "config_defaults_inc.php", "config_defaults_inc.php.bak");
  }

  @AfterSuite
  public void tearDown() throws Exception {
    app.ftp().restore("config_defaults_inc.php.bak", "config_defaults_inc.php");
    app.stop();
  }

}
