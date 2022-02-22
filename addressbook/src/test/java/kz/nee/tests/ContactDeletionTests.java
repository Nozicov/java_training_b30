package kz.nee.tests;

import kz.nee.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactDeletionTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    if (! app.getContactHelper().isThereAContact()){
      app.getNavigationHelper().gotoContactCreation();
      app.getContactHelper().createContact(new ContactData("Yevgeniy", "Nozikov", "Almaty", "nee@nee.kz", "+77075555555", "Group name"));
    }
  }

  @Test
  public void testContactDeletion(){

    List<ContactData> before = app.getContactHelper().getContactList();
    int index = before.size() - 1;

    app.getContactHelper().deleteGroup(index);

    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), index, "Количество контактов не уменьшилось!");

    before.remove(index);
    Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after, "Сравнение отсортированных списков контактов прошло не успешно!");
  }

}
