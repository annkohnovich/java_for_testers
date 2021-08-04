package ru.stqa.ptf.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.stqa.ptf.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GroupDataGenerator {
    @Parameter (names = "-c", description = "Group Count")
    public int count;

    @Parameter (names = "-f", description = "Target File")
    public String pathToFile;

    @Parameter (names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        GroupDataGenerator generator = new GroupDataGenerator();
        JCommander commander = new JCommander(generator);
        try {
            commander.parse(args);
        } catch (ParameterException ex)
        {
            commander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List <GroupData> groups = generateGroups(count);
        if (format.equals("csv")) {
            saveAsCsv(groups, new File(pathToFile));
        }
        else if (format.equals("json")) {
            saveAsJson(groups, new File(pathToFile));
        }
        else {
            System.out.println("Unrecognized format");
        }

    }

    private void saveAsCsv(List<GroupData> groups, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (GroupData group :groups) {
            writer.write(String.format("%s;%s;%s\n", group.getName(), group.getHeader(), group.getFooter()));
        }
        writer.close();
    }

    private void saveAsJson(List<GroupData> groups, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(groups);
        Writer writer = new FileWriter(file);
        writer.write(json);
        writer.close();
    }

    private List<GroupData> generateGroups(int count) {
        List <GroupData> groups = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            groups.add(new GroupData().withName(String.format("test %s", i))
            .withHeader(String.format("header %s", i))
            .withFooter(String.format("footer %s", i)));
        }
        return groups;
    }
}
