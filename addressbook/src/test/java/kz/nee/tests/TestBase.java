package kz.nee.tests;

import kz.nee.appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class TestBase {

  protected final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
    app.stop();
  }

}
