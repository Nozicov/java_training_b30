package kz.nee.tests;

import kz.nee.model.ContactData;
import kz.nee.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().contactCreation();
    GroupData groupData = new GroupData("Group name", "Group header", "Group footer");
    if (!app.contact().findGroup(groupData.getName())) {
      app.group().create(groupData);
    }
  }

  @Test
  public void testContactCreation() throws Exception {

    app.goTo().home();

    List<ContactData> before = app.contact().list();

    ContactData contact = new ContactData("Yevgeniy", "Nozikov", "Almaty", "nee@nee.kz", "+77075555555", "Group name");
    int index = before.size() + 1;

    app.contact().create(contact);

    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), index, "Количество контактов не увеличилось!");

    before.add(contact);
    Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after, "Сравнение отсортированных списков контактов прошло не успешно!");
  }

}
