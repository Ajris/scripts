package controllers;

import entity.Script;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@EnableAutoConfiguration
@EnableMongoRepositories(basePackages = {"services"})
public class ScriptControllerIT {

    @Mock
    ScriptController scriptController;

    @Test
    public void checkIfUnusedScriptIsSaved(){
        Script script = new Script("1", "1");
        Mockito.doNothing().when(scriptController).uploadScript(script.getTitle(), script.getText());
        scriptController.uploadScript(script.getTitle(), script.getText());

        Mockito.verify(scriptController, Mockito.times(1)).uploadScript("1","1");

    }
}
