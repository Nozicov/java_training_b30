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
      app.contact().returnToHomePage();
    }
  }

  @Test
  public void testContactModification(){

    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();

    ContactData contact = new ContactData()
            .withId(modifiedContact.getId())
            .withFirstname("Yevgeniy - up")
            .withLastname("Nozikov - up")
            .withAddress("Almaty - up")
            .withEmail("nee@nee-up.kz")
            .withPhoneMobile("+77777777777");

    app.contact().modify(contact);

    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));
  }

}
