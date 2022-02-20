package kz.nee.tests;

import kz.nee.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();

    List<GroupData> before = app.getGroupHelper().getGroupList();

    app.getGroupHelper().creatGroup(new GroupData("Group name", "Group header", "Group footer"));

    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() + 1, "Количество групп не увеличилось!");
  }

}
