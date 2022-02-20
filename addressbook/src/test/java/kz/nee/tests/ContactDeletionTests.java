package kz.nee.tests;

import kz.nee.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ContactDeletionTests extends TestBase{

  @Test
  public void testContactDeletion(){

    if (! app.getContactHelper().isThereAContact()){
      app.getNavigationHelper().gotoContactCreation();
      app.getContactHelper().creatContact(new ContactData("Yevgeniy", "Nozikov", "Almaty", "nee@nee.kz", "+77075555555", "Group name"));
      app.getNavigationHelper().returnToHomePage();
    }

    List<ContactData> before = app.getContactHelper().getContactList();

    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteSelectedContacts();
    app.getNavigationHelper().gotoHomePage();

    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1, "Количество контактов не уменьшилось!");

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }
}
