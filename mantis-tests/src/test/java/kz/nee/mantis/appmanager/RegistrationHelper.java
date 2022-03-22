package kz.nee.mantis.appmanager;

import org.openqa.selenium.By;

public class RegistrationHelper extends BaseHelper {

  public RegistrationHelper(ApplicationManager app) {
    super(app);
  }

  public void start(String username, String email) {
    wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
    type(By.name("username"), username);
    type(By.name("email"), email);
    click(By.cssSelector("input[type='submit']"));
  }

  public void finish(String confirmmationLink, String password) {
    wd.get(confirmmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.className("btn-success"));
  }
}
