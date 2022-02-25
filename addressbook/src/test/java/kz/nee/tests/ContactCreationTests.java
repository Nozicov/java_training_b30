package kz.nee.tests;

import kz.nee.model.ContactData;
import kz.nee.model.Contacts;
import kz.nee.model.GroupData;
import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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

    Contacts before = app.contact().all();

    ContactData contact = new ContactData()
            .withFirstname("Yevgeniy")
            .withLastname("Nozikov")
            .withAddress("Almaty")
            .withEmail("nee@nee.kz")
            .withMobile("+77075555555")
            .withGroup("Group name");

    app.contact().create(contact);

    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, CoreMatchers.equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

}
