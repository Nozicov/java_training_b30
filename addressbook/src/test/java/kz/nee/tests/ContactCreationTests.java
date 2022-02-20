package kz.nee.tests;

import kz.nee.model.ContactData;
import kz.nee.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

    List<ContactData> before = app.getContactHelper().getContactList();

    app.getNavigationHelper().gotoContactCreation();

    GroupData groupData = new GroupData("Group name", "Group header", "Group footer");

    if (!app.getContactHelper().findGroupInContact(groupData.getName())) {
      app.getNavigationHelper().gotoGroupPage();
      app.getGroupHelper().creatGroup(groupData);
      app.getNavigationHelper().gotoContactCreation();
    }

    ContactData contact = new ContactData("Yevgeniy", "Nozikov", "Almaty", "nee@nee.kz", "+77075555555", "Group name");
    app.getContactHelper().fillContactForm(contact, true);
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().returnToHomePage();

    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1, "Количество контактов не увеличилось!");

    int max = 0;
    for (ContactData g: after){
      if (g.getId() > max){
        max = g.getId();
      }
    }
    contact.setId(max);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after), "Сравнение множества групп прошло не успешно!");
  }

}
