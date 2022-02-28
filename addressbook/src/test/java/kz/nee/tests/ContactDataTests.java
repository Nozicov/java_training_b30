package kz.nee.tests;

import kz.nee.model.ContactData;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDataTests extends TestBase{

  @Test
  public void testContactPhones(){
    app.goTo().home();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFormEditProm = app.contact().infoFormEditProm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFormEditProm)));
    assertThat(contact.getAddress(), equalTo(contactInfoFormEditProm.getAddress()));
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFormEditProm)));
  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getPhoneHome(), contact.getPhoneMobile(), contact.getPhoneWork())
            .stream()
            .filter(s -> ! s.equals(""))
            .map(ContactDataTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream()
            .filter(s -> ! s.equals(""))
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone){
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }

}
