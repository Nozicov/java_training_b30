package kz.nee.tests;

import kz.nee.model.ContactData;
import kz.nee.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

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

    app.getContactHelper().fillContactForm(new ContactData("Yevgeniy", "Nozikov", "Almaty", "nee@nee.kz", "+77075555555", "Group name"), true);
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().returnToHomePage();

    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1, "Количество контактов не увеличилось!");
  }

}
