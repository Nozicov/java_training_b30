package kz.nee.tests;

import kz.nee.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupDeletionTest extends TestBase {

  @Test
  public void testGroupDeletion() {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().creatGroup(new GroupData("Group name", "Group header", "Group footer"));
    }

    int before = app.getGroupHelper().getGroupCount();

    app.getGroupHelper().selectedGroup();
    app.getGroupHelper().deleteSelectedGrops();
    app.getNavigationHelper().gotoGroupPage();

    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before - 1, "Количество групп не уменьшилось!");
  }

}
