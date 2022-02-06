package kz.nee.tests;

import kz.nee.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.gotoContactCreation();
    app.fillContactCreation(new ContactData("Yevgeniy", "Nozikov", "Almaty", "nee@nee.kz", "+77075555555"));
    app.submitContactCreation();
    app.returnToHomePage();
  }

}
