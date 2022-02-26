package kz.nee.tests;

import kz.nee.model.ContactData;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase{

  @Test
  public void testContactEmails(){
    app.goTo().home();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFormEditProm = app.contact().infoFormEditProm(contact);

    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFormEditProm)));
  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream()
            .filter(s -> ! s.equals(""))
            .collect(Collectors.joining("\n"));
  }

}
