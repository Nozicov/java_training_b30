package kz.nee.tests;

import kz.nee.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase{

  @Test
  public void testContactDeletion(){

    if (! app.getContactHelper().isThereAContact()){
      app.getNavigationHelper().gotoContactCreation();
      app.getContactHelper().creatContact(new ContactData("Yevgeniy", "Nozikov", "Almaty", "nee@nee.kz", "+77075555555", "Group name"));
      app.getNavigationHelper().returnToHomePage();
    }

    int before = app.getContactHelper().getContactCount();

    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContacts();
    app.getNavigationHelper().gotoHomePage();

    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before - 1, "Количество контактов не уменьшилось!");
  }
}
