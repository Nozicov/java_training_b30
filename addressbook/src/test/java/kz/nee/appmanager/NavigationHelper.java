package kz.nee.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends BaseHelper {

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void gotoGroupPage() {
    click(By.linkText("groups"));
  }

  public void gotoContactCreation() {
    click(By.linkText("add new"));
  }
  public void returnToHomePage() {
    click(By.linkText("home page"));
  }
}
