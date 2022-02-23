package kz.nee.tests;

import kz.nee.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class ContactDeletionTests extends TestBase{

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
    }
  }

  @Test
  public void testContactDeletion(){

    Set<ContactData> before = app.contact().all();
    ContactData deletedContact = before.iterator().next();

    app.contact().delete(deletedContact);

    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() - 1, "Количество контактов не уменьшилось!");

    before.remove(deletedContact);
    Assert.assertEquals(before, after, "Сравнение множества контактов прошло не успешно!");
  }

}
