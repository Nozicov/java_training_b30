package kz.nee.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import kz.nee.model.GroupData;
import kz.nee.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.group().gotoPage();
  }

  @DataProvider
  public Iterator<Object[]> validGroupsFromXml() throws IOException {
    try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")))){
      String xml = "";
      String line = reader.readLine();
      while (line != null){
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.allowTypes(new Class[]{ GroupData.class });
      xstream.processAnnotations(GroupData.class);
      List<GroupData> groups = (List<GroupData>)  xstream.fromXML(xml);
      return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validGroupsFromJson() throws IOException {
    try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")))){
      String json = "";
      String line = reader.readLine();
      while (line != null){
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>(){}.getType());
      return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "validGroupsFromJson")
  public void testGroupCreation(GroupData group) {

    Groups before = app.db().groups();

    app.group().create(group);

    assertThat(app.group().count(), equalTo(before.size() + 1));

    Groups after = app.db().groups();

    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt())))
    );

    verifyGroupListInUI();
  }

}
