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
    GroupData groupData = new GroupData()
            .withName("Group name")
            .withHeader("Group header")
            .withFooter("Group footer");
    if (!app.contact().findGroup(groupData.getName())) {
      app.group().create(groupData);
    }
  }

  @Test
  public void testContactCreation() throws Exception {

    app.goTo().home();

    List<ContactData> before = app.contact().list();

    ContactData contact = new ContactData()
            .withFirstname("Yevgeniy")
            .withLastname("Nozikov")
            .withAddress("Almaty")
            .withEmail("nee@nee.kz")
            .withMobile("+77075555555")
            .withGroup("Group name");

    app.contact().create(contact);

    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1, "Количество контактов не увеличилось!");

    before.add(contact);
    Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after, "Сравнение отсортированных списков контактов прошло не успешно!");
  }

}
