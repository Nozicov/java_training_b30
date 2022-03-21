package kz.nee.tests;

import kz.nee.model.GroupData;
import kz.nee.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().groups().size() == 0){
      app.group().gotoPage();
      app.group().create(new GroupData()
              .withName("Group name")
              .withHeader("Group header")
              .withFooter("Group footer")
      );
    }
  }

  @Test
  public void testGroupDeletion() {

    Groups before = app.db().groups();

    GroupData deletedGroup = before.iterator().next();

    app.group().gotoPage();
    app.group().delete(deletedGroup);

    assertThat(app.group().count(), equalTo(before.size() - 1));

    Groups after = app.db().groups();

    assertThat(after, equalTo(before.withOut(deletedGroup)));

    verifyGroupListInUI();
  }

}
