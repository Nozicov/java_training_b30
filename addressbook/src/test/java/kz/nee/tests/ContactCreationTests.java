package kz.nee.tests;

import kz.nee.model.ContactData;
import kz.nee.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class ContactCreationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().contactCreation();
    GroupData group = new GroupData()
            .withName("Group name")
            .withHeader("Group header")
            .withFooter("Group footer");
    if (!app.contact().findGroup(group.getName())) {
      app.group().create(group);
    }
  }

  @Test
  public void testContactCreation() throws Exception {

    app.goTo().home();

    Set<ContactData> before = app.contact().all();

    ContactData contact = new ContactData()
            .withFirstname("Yevgeniy")
            .withLastname("Nozikov")
            .withAddress("Almaty")
            .withEmail("nee@nee.kz")
            .withMobile("+77075555555")
            .withGroup("Group name");

    app.contact().create(contact);

    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1, "Количество контактов не увеличилось!");

    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after, "Сравнение множества контактов прошло не успешно!");
  }

}
