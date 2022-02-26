package kz.nee.tests;

import kz.nee.model.ContactData;
import kz.nee.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.contact().count() == 0){
      app.goTo().contactCreation();
      app.contact().create(new ContactData()
              .withFirstname("Yevgeniy")
              .withLastname("Nozikov")
              .withAddress("Almaty")
              .withEmail("nee@nee.kz")
              .withPhoneMobile("+77075555555")
              .withGroup("Group name")
      );
    }
  }

  @Test
  public void testContactDeletion(){

    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();

    app.contact().delete(deletedContact);

    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withOut(deletedContact)));
  }

}
