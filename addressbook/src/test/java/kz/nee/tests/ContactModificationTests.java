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

    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;

    ContactData contact = new ContactData()
            .withId(before.get(index).getId())
            .withFirstname("Yevgeniy - up")
            .withLastname("Nozikov - up")
            .withAddress("Almaty - up")
            .withEmail("nee@nee-up.kz")
            .withMobile("+77777777777");

    app.contact().modify(index, contact);

    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size(), "Изменилось количество контактов!");

    before.remove(index);
    before.add(contact);

    Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after, "Сравнение отсортированных списков контактов прошло не успешно!");
  }

}
