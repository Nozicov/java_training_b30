package kz.nee.appmanager;

import kz.nee.model.ContactData;
import kz.nee.model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class ContactHelper extends BaseHelper {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contact, boolean creation) {
    type(By.name("firstname"), contact.getFirstname());
    type(By.name("lastname"), contact.getLastname());
    type(By.name("address"), contact.getAddress());
    type(By.name("email"), contact.getEmail());
    type(By.name("home"), contact.getPhoneHome());
    type(By.name("mobile"), contact.getPhoneMobile());
    type(By.name("work"), contact.getPhoneWork());

    if (creation) {
      try {
        selectList(By.name("new_group"), contact.getGroup());
      } catch (NoSuchElementException ex) {
        try {
          selectList(By.name("new_group"), "[none]");
        } catch (NoSuchElementException e) {
          Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void selectContact(int id) {
    wd.findElement(By.cssSelector("input[id='"+ id + "']")).click();
  }

  public void deleteSelectedContacts() {
    click(By.xpath("//input[@value='Delete']"));
    wd.switchTo().alert().accept();
  }

  public void selectedContactModification(int id) {
    wd.findElement(By.xpath("//a[@href='edit.php?id=" + id + "']")).click();
  }

  public void submitContactModification() {
    click(By.xpath("//div[@id='content']/form/input[22]"));
  }

  public void create(ContactData contact) {
    gotoContactCreation();
    fillContactForm(contact, true);
    submitContactCreation();
    contactCache = null;
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    selectedContactModification(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    contactCache = null;
    returnToHomePage();
  }

  public void delete(ContactData contact) {
    selectContact(contact.getId());
    deleteSelectedContacts();
    contactCache = null;
    returnHomePage();
  }

  public boolean findGroup(String name) {
    return selectList(By.name("new_group"), name);
  }

  public void gotoContactCreation() {
    click(By.linkText("add new"));
  }

  public void returnHomePage() {
    if (isElementPresent(By.id("maintable"))){
      return;
    }
    click(By.linkText("home"));
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null){
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
    for (WebElement element: elements){
      List<WebElement> cells = element.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("id"));
      String lastname  = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String address = cells.get(3).getText();
      String email = null;
      if (cells.get(4).findElements(By.tagName("a")).size() > 0){
        email = cells.get(4).findElement(By.tagName("a")).getText();
      }
      String allPhones = cells.get(5).getText();

      ContactData contact = new ContactData()
              .withId(id)
              .withFirstname(firstname)
              .withLastname(lastname)
              .withAddress(address)
              .withEmail(email)
              .withAllPhones(allPhones);

      contactCache.add(contact);
    }
    return new Contacts(contactCache);
  }

  public int count(){
    return wd.findElements(By.name("selected[]")).size();
  }

  public ContactData infoFormEditProm(ContactData contact) {
    initContactModificationById(contact.getId());
    String lastname  = wd.findElement(By.name("lastname")).getAttribute("value");
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String phoneHome = wd.findElement(By.name("home")).getAttribute("value");
    String phoneMobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String phoneWork = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");

    return new ContactData()
            .withId(contact.getId())
            .withLastname(lastname)
            .withFirstname(firstname)
            .withAddress(address)
            .withPhoneHome(phoneHome)
            .withPhoneMobile(phoneMobile)
            .withPhoneWork(phoneWork)
            .withEmail(email)
            .withEmail2(email2)
            .withEmail3(email3);
  }

  private void initContactModificationById(int id){
//    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[id='%s']", id)));
//    WebElement row = checkbox.findElement(By.xpath("./../.."));
//    List<WebElement> cells = row.findElements(By.tagName("td"));
//    cells.get(7).findElement(By.tagName("a")).click();
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
  }
}
