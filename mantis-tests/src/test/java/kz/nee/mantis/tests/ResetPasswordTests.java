package kz.nee.mantis.tests;

import kz.nee.mantis.model.MailMessage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTests extends TestBase{

  public static long now = System.currentTimeMillis();
  public static String updateUsername = String.format("user%s", now);
  public static String updateUserPass = String.format("pass%s", now);
  public static String updateUserEmail = String.format("user%s@host.local", now);

//  public static String adminLogin = app.getProperty("web.adminLogin");
  public static String adminLogin = "administrator";
//  public static String adminPassword = app.getProperty("web.adminPassword");
  public static String adminPassword = "root";


  @BeforeMethod
  public void startMailServer() throws MessagingException, IOException, InterruptedException {
    app.mail().start();

    /*Create new User*/
    app.registration().start(updateUsername, updateUserEmail);
    List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
    String confirmationLink = app.registration().findConfirmationLink(mailMessages, updateUserEmail);
    System.out.println(confirmationLink);

    /*Mail approve link User*/
    app.registration().finish(confirmationLink, updateUserPass);
    assertTrue(app.newSession().login(updateUsername, updateUserPass));
    app.reset().logoutPanel();

  }

  @Test
  public void testResetPassword() throws IOException, MessagingException, InterruptedException {

    /*UI Admin*/
    app.reset().login(adminLogin, adminPassword);
    String email = app.reset().resetPasswordUser(updateUsername);

    /*Mail approve link resetPassword User*/
    List<MailMessage> mailMessages = app.mail().waitForMail(4, 30000);
    String confirmationLink = app.reset().findConfirmationLink(mailMessages, email);
    System.out.println(confirmationLink);

    /*IU User - goTo link resetPassword User*/
    app.registration().finish(confirmationLink, updateUserPass);
    /*IU User - login User*/
    app.reset().login(updateUsername, updateUserPass);
    assertTrue(app.newSession().login(updateUsername, updateUserPass));

  }


  @AfterMethod(alwaysRun = true)
  public void stopMailServer(){
    app.mail().stop();
  }

}
