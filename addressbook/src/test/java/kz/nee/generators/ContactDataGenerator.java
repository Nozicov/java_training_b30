package kz.nee.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import kz.nee.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Contact file")
  public String file;

  @Parameter(names = "-d", description = "Contact format file")
  public String format;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex){
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> groups = generatorContacts(count);
    if (format.equals("csv")){
      saveAsCsv(groups, new File(file));
    } else if (format.equals("xml")){
      saveAsXml(groups, new File(file));
    } else if (format.equals("json")){
      saveAsJson(groups, new File(file));
    } else {
      System.out.println("Unrecognized format " + format);
    }
  }

  private List<ContactData> generatorContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i =0; i < count; i++){
      contacts.add(new ContactData()
              .withFirstname(String.format("Firstname %s", i))
              .withLastname(String.format("Lastname %s", i))
              .withAddress(String.format("Address %s", i))
              .withEmail(String.format("email%s@mail.ru", i))
              .withPhoneMobile(String.format("+77070000%s", i))
              .withPhoneHome(String.format("+77770000%s", i))
              .withPhoneWork(String.format("+77570000%s", i))
              .withGroup(String.format("Group %s", i)));
    }
    return contacts;
  }


  private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    try(Writer writer = new FileWriter(file)){
      writer.write(json);
    }
  }

  private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    String xml = xstream.toXML(contacts);
    try(Writer writer = new FileWriter(file)) {
      writer.write(xml);
    }

  }

  private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
    try(Writer writer = new FileWriter(file)) {
      for (ContactData contact : contacts) {
        writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s\n",
                contact.getFirstname(),
                contact.getLastname(),
                contact.getAddress(),
                contact.getEmail(),
                contact.getPhoneMobile(),
                contact.getPhoneHome(),
                contact.getPhoneWork(),
                contact.getGroup()));
      }
    }
  }

}
