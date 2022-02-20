package kz.nee.tests;

import kz.nee.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase{

  @Test
  public void testContactModification(){
    if (! app.getContactHelper().isThereAContact()){
      app.getNavigationHelper().gotoContactCreation();
      app.getContactHelper().creatContact(new ContactData("Yevgeniy", "Nozikov", "Almaty", "nee@nee.kz", "+77075555555", "Group name"));
      app.getNavigationHelper().returnToHomePage();
    }

    int before = app.getContactHelper().getContactCount();

    app.getContactHelper().selectedContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Yevgeniy - up", "Nozikov - up", "Almaty - up", "nee@nee-up.kz", "+77777777777", null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToHomePage();

    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before, "Изменилось количество контактов!");
  }
}
