package kz.nee.tests;

import kz.nee.model.GroupData;
import kz.nee.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.group().gotoPage();
  }

  @Test
  public void testGroupCreation() throws Exception {

   Groups before = app.group().all();

    GroupData group = new GroupData()
            .withName("Group name")
            .withHeader("Group header")
            .withFooter("Group footer");

    app.group().create(group);

    Groups after = app.group().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt())))
    );
  }

}
