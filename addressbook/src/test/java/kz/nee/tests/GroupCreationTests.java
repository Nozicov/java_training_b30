package kz.nee.tests;

import kz.nee.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.group().gotoPage();
  }

  @Test
  public void testGroupCreation() throws Exception {

    List<GroupData> before = app.group().list();

    GroupData group = new GroupData()
            .withName("Group name")
            .withHeader("Group header")
            .withFooter("Group footer");

    app.group().create(group);

    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), before.size() + 1, "Количество групп не увеличилось!");

    before.add(group);
    Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after, "Сравнение отсортированных списков групп прошло не успешно!");
  }

}
