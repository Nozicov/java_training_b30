package kz.nee.tests;

import kz.nee.model.GroupData;
import kz.nee.model.Groups;
import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().groups().size() == 0){
      app.group().gotoPage();
      app.group().create(new GroupData()
              .withName("Group name")
              .withHeader("Group header")
              .withFooter("Group footer"));
    }
  }

  @Test
  public void testGroupModification(){

    Groups before = app.db().groups();

    GroupData modifiedGroup = before.iterator().next();

    GroupData group = new GroupData()
            .withId(modifiedGroup.getId())
            .withName("Group name - up")
            .withHeader("Group header - up")
            .withFooter("Group footer - up");

    app.group().gotoPage();
    app.group().modify(group);

    assertThat(app.group().count(), equalTo(before.size()));

    Groups after = app.db().groups();

    assertThat(after, CoreMatchers.equalTo(before.withOut(modifiedGroup).withAdded(group)));
  }

}
