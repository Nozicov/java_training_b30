package kz.nee.tests;

import kz.nee.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class GroupDeletionTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.group().gotoPage();
    if (app.group().list().size() == 0){
      app.group().create(new GroupData()
              .withName("Group name")
              .withHeader("Group header")
              .withFooter("Group footer")
      );
    }
  }

  @Test
  public void testGroupDeletion() {

    Set<GroupData> before = app.group().all();
    GroupData deletedGroup = before.iterator().next();

    app.group().delete(deletedGroup);

    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size() - 1, "Количество групп не уменьшилось!");

    before.remove(deletedGroup);
    Assert.assertEquals(before, after, "Сравнение множеств групп прошло не успешно!");
  }


}
