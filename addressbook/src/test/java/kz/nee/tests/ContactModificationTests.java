package kz.nee.tests;

import kz.nee.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    if (! app.getContactHelper().isThereAContact()){
      app.getNavigationHelper().gotoContactCreation();
      app.getContactHelper().createContact(new ContactData("Yevgeniy", "Nozikov", "Almaty", "nee@nee.kz", "+77075555555", "Group name"));
      app.getContactHelper().returnToHomePage();
    }
  }

  @Test
  public void testContactModification(){

    List<ContactData> before = app.getContactHelper().getContactList();
    int index = before.size() - 1;
    ContactData contact = new ContactData(before.get(index).getId(), "Yevgeniy - up", "Nozikov - up", "Almaty - up", "nee@nee-up.kz", "+77777777777", null);

    app.getContactHelper().modifyContact(index, contact);

    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size(), "Изменилось количество контактов!");

    before.remove(index);
    before.add(contact);

    Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after, "Сравнение отсортированных списков контактов прошло не успешно!");
  }

}
