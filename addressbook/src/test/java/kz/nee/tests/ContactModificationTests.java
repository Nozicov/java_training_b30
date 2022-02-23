package kz.nee.tests;

import kz.nee.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class ContactModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.contact().list().size() == 0){
      app.goTo().contactCreation();
      app.contact().create(new ContactData()
              .withFirstname("Yevgeniy")
              .withLastname("Nozikov")
              .withAddress("Almaty")
              .withEmail("nee@nee.kz")
              .withMobile("+77075555555")
              .withGroup("Group name")
      );
      app.contact().returnToHomePage();
    }
  }

  @Test
  public void testContactModification(){

    Set<ContactData> before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();

    ContactData contact = new ContactData()
            .withId(modifiedContact.getId())
            .withFirstname("Yevgeniy - up")
            .withLastname("Nozikov - up")
            .withAddress("Almaty - up")
            .withEmail("nee@nee-up.kz")
            .withMobile("+77777777777");

    app.contact().modify(contact);

    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size(), "Изменилось количество контактов!");

    before.remove(modifiedContact);
    before.add(contact);
    Assert.assertEquals(before, after, "Сравнение множества контактов прошло не успешно!");
  }

}
