package kz.nee.appmanager;

import kz.nee.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupHelper extends BaseHelper {

  public GroupHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToGroupPage() {
    click(By.linkText("group page"));
  }

  public void gotoPage() {
    if (isElementPresent(By.tagName("h1"))
            && wd.findElement(By.tagName("h1")).getText().equals("Groups")
            && isElementPresent(By.name("new"))){
      return;
    }
    click(By.linkText("groups"));
  }

  public void submitGroupCreation() {
    click(By.name("submit"));
  }

  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getName());
    type(By.name("group_header"), groupData.getHeader());
    type(By.name("group_footer"), groupData.getFooter());
  }

  public void initGroupCreation() {
    click(By.name("new"));
  }

  public void deleteSelectedGrops() {
    click(By.name("delete"));
  }

  public void select(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void initGroupModification() {
    click(By.name("edit"));
  }

  public void submitGroupModification() {
    click(By.name("update"));
  }

  public void create(GroupData group) {
    gotoPage();
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    returnToGroupPage();
  }

  public void modify(GroupData group) {
    select(group.getId());
    initGroupModification();
    fillGroupForm(group);
    submitGroupModification();
    returnToGroupPage();
  }

  public void delete(int index) {
    select(index);
    deleteSelectedGrops();
    gotoPage();
  }

  public void delete(GroupData group) {
    select(group.getId());
    deleteSelectedGrops();
    gotoPage();
  }

  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<GroupData> list() {
    List<GroupData> groups = new ArrayList<GroupData>();
    List<WebElement> elements = wd.findElements(By.xpath("//span[@class=\"group\"]"));
    for (WebElement element: elements){
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      GroupData group = new GroupData()
              .withId(id)
              .withName(name);
      groups.add(group);
    }
    return groups;
  }

  public Set<GroupData> all() {
    Set<GroupData> groups = new HashSet<GroupData>();
    List<WebElement> elements = wd.findElements(By.xpath("//span[@class=\"group\"]"));
    for (WebElement element: elements){
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      GroupData group = new GroupData()
              .withId(id)
              .withName(name);
      groups.add(group);
    }
    return groups;
  }

}
