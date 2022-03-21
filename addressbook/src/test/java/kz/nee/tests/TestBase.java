package kz.nee.tests;

import kz.nee.appmanager.ApplicationManager;
import kz.nee.model.GroupData;
import kz.nee.model.Groups;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class TestBase {

  Logger logger = LoggerFactory.getLogger(TestBase.class);

  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite
  public void tearDown() throws Exception {
    app.stop();
  }

  @BeforeMethod(alwaysRun = true)
  public void logTestStart(Method method, Object[] p){
    logger.info("Start test " + method.getName() + " with parameters " + Arrays.asList(p));
  }

  @AfterMethod(alwaysRun = true)
  public void logTestStop(Method method, Object[] p){
    logger.info("Stop test " + method.getName() + " with parameters " + Arrays.asList(p));
  }

  public void verifyGroupListInUI() {
    if (Boolean.getBoolean("verifyUI")){
      Groups dbGroups = app.db().groups();
      Groups uiGroups = app.group().all();
      assertThat(uiGroups, equalTo(dbGroups.stream()
              .map((g) -> new GroupData()
                      .withId(g.getId())
                      .withName(g.getName()))
              .collect(Collectors.toSet())));
    }
  }

}
