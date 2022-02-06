package kz.nee;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("Group name", "Group header", "Group footer"));
    submitGroupCreation();
    returnToGroupPage();
  }

}
