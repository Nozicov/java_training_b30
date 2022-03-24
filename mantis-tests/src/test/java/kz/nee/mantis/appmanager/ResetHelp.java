package kz.nee.mantis.appmanager;

import kz.nee.mantis.model.MailMessage;
import org.openqa.selenium.By;
import ru.lanwen.verbalregex.VerbalExpression;

import java.util.List;

import static java.lang.Thread.sleep;

public class ResetHelp extends BaseHelper{

  public ResetHelp(ApplicationManager app) {
    super(app);
  }

  public void loginNew(String username, String password){
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), username);
    click(By.cssSelector("input[type='submit']"));
    type(By.name("password"), password);
    click(By.cssSelector("input[type='submit']"));
  }

  public void login(String username, String password) {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), username);
    click(By.cssSelector("input[type='submit']"));
    type(By.name("password"), password);
    click(By.cssSelector("input[type='submit']"));
  }

  public void gotoManagerUsers() {
    if (isElementPresent(By.id("menu-toggler"))){
      click(By.id("menu-toggler"));
      click(By.className("fa-gears"));
    }
    else {
      click(By.className("fa-gears"));
    }
    click(By.xpath("//a[contains(@href, '/manage_user_page.php')]"));
  }

  public void gotoManagerUsersOld() {
    wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
  }

  public String searchAndSelectedUser(String selectUsername) {
    type(By.id("search"), selectUsername);
    click(By.xpath("//form[@id='manage-user-filter']//input[@type='submit']"));
    click(By.cssSelector("td:nth-of-type(1) > a"));
    return wd.findElement(By.xpath("//input[@id='email-field']")).getAttribute("value");
  }

  public String searchAndSelectedUserOld(String selectUsername) {
    type(By.id("username"), selectUsername);
    click(By.xpath("(//input[@type='submit'])[5]"));
    //click(By.cssSelector("td:nth-of-type(1) > a"));
    return wd.findElement(By.id("email-field")).getAttribute("value");
  }

  public void resetPassword()  {
    click(By.xpath("//form[@id='manage-user-reset-form']//input[@type='submit']"));
  }

  public void resetPasswordOld()  {
    click(By.xpath("//form[@id='manage-user-reset-form']//input[@type='submit']"));
  }

  public void logoutPanel()  {
    click(By.className("user-info"));
    click(By.className("fa-sign-out"));
  }

  public void logoutPanelOld() throws InterruptedException {
    click(By.id("logout-link"));
    sleep(5000);
  }

  public String resetPasswordUser(String updateUsername) throws InterruptedException {
    gotoManagerUsers();
    String email = searchAndSelectedUser(updateUsername);
    resetPassword();
    logoutPanel();
    return email;
  }

  public String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    System.out.println(mailMessage.text);
    return regex.getText(mailMessage.text);
  }


  public void getUrl(String confirmationLink) {
    wd.get(confirmationLink);
  }
}
