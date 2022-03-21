package kz.nee.tests;

import kz.nee.model.ContactData;
import kz.nee.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().contacts().size() == 0){
      app.goTo().contactCreation();
      app.contact().create(new ContactData()
              .withFirstname("Yevgeniy")
              .withLastname("Nozikov")
              .withAddress("Almaty")
              .withEmail("nee@nee.kz")
              .withEmail2("nee3@nee.kz")
              .withEmail3("nee3@nee.kz")
              .withPhoneMobile("+77777777771")
              .withPhoneWork("+7555555551")
              .withPhoneHome("+78888888881")

      );
    }
  }

  @Test
  public void testContactModification(){

    Contacts before = app.db().contacts();

    app.goTo().home();

    ContactData modifiedContact = before.iterator().next();

    ContactData contact = new ContactData()
            .withId(modifiedContact.getId())
            .withFirstname("Yevgeniy - up")
            .withLastname("Nozikov - up")
            .withAddress("Almaty - up")
            .withEmail("nee@nee-up.kz")
            .withEmail2("nee3@nee-up.kz")
            .withEmail3("nee3@nee-up.kz")
            .withPhoneMobile("+77777777777")
            .withPhoneWork("+7555555555")
            .withPhoneHome("+78888888888");

    app.contact().modify(contact);

    assertThat(app.contact().count(), equalTo(before.size()));

    Contacts after = app.db().contacts();

    assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));

    verifyGroupListInUI();
  }

}
