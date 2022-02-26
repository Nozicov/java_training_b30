package kz.nee.tests;

import kz.nee.model.ContactData;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase{

  @Test
  public void testContactAddress(){
    app.goTo().home();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFormEditProm = app.contact().infoFormEditProm(contact);

    assertThat(contact.getAddress(), equalTo(contactInfoFormEditProm.getAddress()));
  }

}
