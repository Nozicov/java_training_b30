package kz.nee;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    gotoContactCreation();
    fillContactCreation(new ContactData("Yevgeniy", "Nozikov", "Almaty", "nee@nee.kz", "+77075555555"));
    submitContactCreation();
    returnToHomePage();
  }

}
