package kz.nee.tests;

import kz.nee.model.GroupData;
import kz.nee.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupDeletionTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.group().gotoPage();
    if (app.group().all().size() == 0){
      app.group().create(new GroupData()
              .withName("Group name")
              .withHeader("Group header")
              .withFooter("Group footer")
      );
    }
  }

  @Test
  public void testGroupDeletion() {

    Groups before = app.group().all();
    GroupData deletedGroup = before.iterator().next();

    app.group().delete(deletedGroup);

    Groups after = app.group().all();
    assertEquals(after.size(), before.size() - 1, "Количество групп не уменьшилось!");
    assertThat(after, equalTo(before.withOut(deletedGroup)));
  }

}
