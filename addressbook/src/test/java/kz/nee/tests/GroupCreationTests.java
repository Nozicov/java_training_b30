package kz.nee.tests;

import kz.nee.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class GroupCreationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.group().gotoPage();
  }

  @Test
  public void testGroupCreation() throws Exception {

    Set<GroupData> before = app.group().all();

    GroupData group = new GroupData()
            .withName("Group name")
            .withHeader("Group header")
            .withFooter("Group footer");

    app.group().create(group);

    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size() + 1, "Количество групп не увеличилось!");

    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(group);
    Assert.assertEquals(before, after, "Сравнение множеств групп прошло не успешно!");
  }

}
