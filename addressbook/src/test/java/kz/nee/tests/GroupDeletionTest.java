package kz.nee.tests;

import kz.nee.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GroupDeletionTest extends TestBase {

  @Test
  public void testGroupDeletion() {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().creatGroup(new GroupData("Group name", "Group header", "Group footer"));
    }

    List<GroupData> before = app.getGroupHelper().getGroupList();

    app.getGroupHelper().selectedGroup(before.size() - 1);
    app.getGroupHelper().deleteSelectedGrops();
    app.getNavigationHelper().gotoGroupPage();

    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() - 1, "Количество групп не уменьшилось!");

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }

}
