package kz.nee.tests;

import kz.nee.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

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

    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;

    app.contact().delete(index);

    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() - 1, "Количество контактов не уменьшилось!");

    before.remove(index);
    Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after, "Сравнение отсортированных списков контактов прошло не успешно!");
  }

}
