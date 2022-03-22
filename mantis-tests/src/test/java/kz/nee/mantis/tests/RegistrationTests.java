package kz.nee.mantis.tests;

import org.testng.annotations.Test;

public class RegistrationTests extends TestBase {

  @Test
  public void testRegistration(){
    app.registration().start("newuser","newuser@newuser.newuser");
  }
}
