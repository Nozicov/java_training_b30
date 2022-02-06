package kz.nee.tests;

import kz.nee.model.GroupData;
import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.gotoGroupPage();
    app.initGroupCreation();
    app.fillGroupForm(new GroupData("Group name", "Group header", "Group footer"));
    app.submitGroupCreation();
    app.returnToGroupPage();
  }

}
