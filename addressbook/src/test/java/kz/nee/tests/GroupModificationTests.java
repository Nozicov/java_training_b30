package kz.nee.tests;

import kz.nee.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification(){
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().creatGroup(new GroupData("Group name", "Group header", "Group footer"));
    }

    int before = app.getGroupHelper().getGroupCount();

    app.getGroupHelper().selectedGroup();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("Name - up", "Header - up", "Footer - up"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();

    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before, "Изменилось количество групп!");
  }
}
