package kz.nee.appmanager;

import kz.nee.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("email"), contactData.getEmail());
    type(By.name("mobile"), contactData.getMobile());

    if (creation) {
      try {
        selectList(By.name("new_group"), contactData.getGroup());
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
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    selectedContactModification(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    returnToHomePage();
  }

  public void delete(ContactData contact) {
    selectContact(contact.getId());
    deleteSelectedContacts();
    returnHomePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//img[@title='Details']"));
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

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
    for (WebElement element: elements){
      List<WebElement> cells = element.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("id"));
      String lastname  = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String address = cells.get(3).getText();
      String email = cells.get(4).findElement(By.tagName("a")).getText();
      String mobile = cells.get(5).getText();
      ContactData contact = new ContactData()
              .withId(id)
              .withFirstname(firstname)
              .withLastname(lastname)
              .withAddress(address)
              .withEmail(email)
              .withMobile(mobile);
      contacts.add(contact);
    }
    return contacts;
  }

  public Set<ContactData> all() {
    Set<ContactData> contacts = new HashSet<ContactData>();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
    for (WebElement element: elements){
      List<WebElement> cells = element.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("id"));
      String lastname  = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String address = cells.get(3).getText();
      String email = cells.get(4).findElement(By.tagName("a")).getText();
      String mobile = cells.get(5).getText();
      ContactData contact = new ContactData()
              .withId(id)
              .withFirstname(firstname)
              .withLastname(lastname)
              .withAddress(address)
              .withEmail(email)
              .withMobile(mobile);
      contacts.add(contact);
    }
    return contacts;
  }
}
