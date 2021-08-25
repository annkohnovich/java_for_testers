package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

public class SoapTests extends TestBase{

    @Test
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soap().getProjects();
        for (Project project: projects) {
            System.out.println(project.getName());
        }
    }

//    JsonElement obj= issue_data .getAsJsonArray().get(0);
//
//    а потом уже obj.getAsJsonObject().get("state_name").getAsString();
//    return !issue_state.equals("Closed")


}
