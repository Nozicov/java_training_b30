package kz.nee.tests;

import kz.nee.model.ContactData;
import kz.nee.model.Contacts;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.contact().all().size() == 0){
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

    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();

    app.contact().delete(deletedContact);

    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size() - 1, "Количество контактов не уменьшилось!");
    MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.withOut(deletedContact)));
  }

}
