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
    if (app.db().contacts().size() == 0){
      app.goTo().contactCreation();
      app.contact().create(new ContactData()
              .withFirstname("Yevgeniy")
              .withLastname("Nozikov")
              .withAddress("Almaty")
              .withEmail("nee@nee.kz")
              .withPhoneMobile("+77075555555")
              .withPhoneWork("+77075555588")
              .withPhoneHome("+77075555577")
      );
    }
  }

  @Test
  public void testContactDeletion(){

    Contacts before = app.db().contacts();;
    ContactData deletedContact = before.iterator().next();

    app.contact().delete(deletedContact);

    assertThat(app.contact().count(), equalTo(before.size() - 1));

    Contacts after = app.db().contacts();

    assertThat(after, equalTo(before.withOut(deletedContact)));

    verifyGroupListInUI();
  }

}
