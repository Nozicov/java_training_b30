package kz.nee.tests;

import kz.nee.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

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

    List<GroupData> before = app.group().list();
    int index = before.size() - 1;

    GroupData group = new GroupData()
            .withName("Group name - up")
            .withHeader("Group header - up")
            .withFooter("Group footer - up");

    app.group().modify(index, group);

    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), before.size(), "Изменилось количество групп!");

    before.remove(index);
    before.add(group);

    Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after, "Сравнение отсортированных списков групп прошло не успешно!");
  }

}
