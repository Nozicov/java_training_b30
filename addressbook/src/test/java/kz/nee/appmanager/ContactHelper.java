package kz.nee.appmanager;

import kz.nee.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends BaseHelper {

  public ContactHelper(WebDriver wd) {
    super(wd);
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

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
    //click(By.name("selected[]"));
  }

  public void deleteSelectedContacts() {
    click(By.xpath("//input[@value='Delete']"));
    wd.switchTo().alert().accept();
  }

  public void selectedContactModification(int index) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
    //click(By.xpath("//img[@alt='Edit']"));
  }

  public void submitContactModification() {
    click(By.xpath("//div[@id='content']/form/input[22]"));
  }

  public void creatContact(ContactData contact) {
    fillContactForm(contact, true);
    submitContactCreation();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//img[@title='Details']"));
  }

  public boolean findGroupInContact(String name) {
    return selectList(By.name("new_group"), name);
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
    for (WebElement element: elements){
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String id = cells.get(0).findElement(By.tagName("input")).getAttribute("id");
      String lastname  = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String address = cells.get(3).getText();
      String email = cells.get(4).findElement(By.tagName("a")).getText();
      String mobile = cells.get(5).getText();
//      String id = element.findElement(By.tagName("input")).getAttribute("id");
//      String lastname = element.findElement(By.xpath("//td[2]")).getText();
//      String firstname = element.findElement(By.xpath("//td[3]")).getText();
//      String address = element.findElement(By.xpath("//td[4]")).getText();
      ContactData contact = new ContactData(id, firstname, lastname, address, email, mobile, null);
      contacts.add(contact);
    }
    return contacts;
  }
}
