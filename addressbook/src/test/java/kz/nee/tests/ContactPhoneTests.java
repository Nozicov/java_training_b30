package kz.nee.tests;

import kz.nee.model.ContactData;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase{

  @Test
  public void testContactPhones(){
    app.goTo().home();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFormEditProm = app.contact().infoFormEditProm(contact);

    assertThat(contact.getPhoneHome(), equalTo(cleaned(contactInfoFormEditProm.getPhoneHome())));
    assertThat(contact.getPhoneMobile(), equalTo(cleaned(contactInfoFormEditProm.getPhoneMobile())));
    assertThat(contact.getPhoneWork(), equalTo(cleaned(contactInfoFormEditProm.getPhoneWork())));
  }

  public String cleaned(String phone){
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }

}
