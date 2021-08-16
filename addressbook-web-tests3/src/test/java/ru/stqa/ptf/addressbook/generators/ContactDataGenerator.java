package ru.stqa.ptf.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.GroupData;
import ru.stqa.ptf.addressbook.model.Groups;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import static ru.stqa.ptf.addressbook.tests.TestBase.app;

public class ContactDataGenerator {
    @Parameter (names = "-c", description = "Contact Count")
    public int count;

    @Parameter (names = "-f", description = "Target File")
    public String pathToFile;

    @Parameter (names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jcommander = new JCommander(generator);
        try {
            jcommander.parse(args);
        } catch (ParameterException ex) {
            jcommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if (format.equals("json")){
            saveAsJson(contacts, new File(pathToFile));
        } else {
            System.out.println("Unrecognized format");
        }

    }

    private void saveAsJson(List<ContactData> contacts, File pathToFile) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        Writer writer = new FileWriter(pathToFile);
        writer.write(json);
        writer.close();
    }


    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData()
                    .withFirstName(String.format("name %s", i)).withLastName(String.format("Surname %s", i))
                    .withHomePhone(String.format("556035%s", i)).withMobilePhone(String.format("029-365-25-4%s", i))
                    .withWorkPhone(String.format("5560666%s", i)).withAddress(String.format("yl Lenina %s", i))
                    .withEmail(String.format("ann%s@gmail.com", i)));
        }
        return contacts;
    }

}
