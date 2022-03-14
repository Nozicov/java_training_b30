package kz.nee.tests;

import kz.nee.model.GroupData;
import kz.nee.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.group().gotoPage();
  }

  @DataProvider
  public Iterator<Object[]> validGroups(){
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[]{
            new GroupData().withName("Group name 1").withHeader("Group header 1").withFooter("Group footer 1")
    });
    list.add(new Object[]{
            new GroupData().withName("Group name 2").withHeader("Group header 2").withFooter("Group footer 2")
    });
    list.add(new Object[]{
            new GroupData().withName("Group name 3").withHeader("Group header 3").withFooter("Group footer 3")
    });
    return list.iterator();
  }

  @Test(dataProvider = "validGroups")
  public void testGroupCreation(GroupData group) throws Exception {

   Groups before = app.group().all();

    app.group().create(group);

    assertThat(app.group().count(), equalTo(before.size() + 1));
    Groups after = app.group().all();
    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt())))
    );
  }

}
