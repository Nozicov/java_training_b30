package kz.nee.tests;

import kz.nee.model.GroupData;
import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().creatGroup(new GroupData("Group name", "Group header", "Group footer"));
  }

}
