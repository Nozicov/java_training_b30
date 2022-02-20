package kz.nee.tests;

import kz.nee.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();

    int before = app.getGroupHelper().getGroupCount();

    app.getGroupHelper().creatGroup(new GroupData("Group name", "Group header", "Group footer"));

    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before + 1, "Количество групп не увеличилось!");
  }

}
