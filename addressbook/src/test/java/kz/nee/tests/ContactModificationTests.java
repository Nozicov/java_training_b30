package kz.nee.tests;

import kz.nee.model.ContactData;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase{

  @Test
  public void testContactModification(){
    app.getContactHelper().selectedContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Yevgeniy - up", "Nozikov - up", "Almaty - up", "nee@nee-up.kz", "+77777777777", null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToHomePage();
  }
}
