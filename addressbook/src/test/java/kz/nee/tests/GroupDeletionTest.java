package kz.nee.tests;

import org.testng.annotations.Test;

public class GroupDeletionTest extends TestBase {

  @Test
  public void testGroupDeletion() {
    app.gotoGroupPage();
    app.selectGroup();
    app.deleteSelectedGrops();
    app.gotoGroupPage();
  }

}
