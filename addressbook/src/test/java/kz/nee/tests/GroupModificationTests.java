package kz.nee.tests;

import kz.nee.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.group().gotoPage();
    if (app.group().list().size() == 0){
      app.group().create(new GroupData()
              .withName("Group name")
              .withHeader("Group header")
              .withFooter("Group footer"));
    }
  }

  @Test
  public void testGroupModification(){

    Set<GroupData> before = app.group().all();
    GroupData modifiedGroup = before.iterator().next();

    GroupData group = new GroupData()
            .withId(modifiedGroup.getId())
            .withName("Group name - up")
            .withHeader("Group header - up")
            .withFooter("Group footer - up");

    app.group().modify(group);

    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size(), "Изменилось количество групп!");

    before.remove(modifiedGroup);
    before.add(group);
    Assert.assertEquals(before, after, "Сравнение множеств групп прошло не успешно!");
  }

}
