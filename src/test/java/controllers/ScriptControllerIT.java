package controllers;

import app.Application;
import entity.Script;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import services.ScriptRepository;

import java.util.Optional;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
@EnableMongoRepositories(basePackages = {"services"})
@ContextConfiguration(classes = {
        ScriptRepository.class
})
public class ScriptControllerIT {

    @Mock
    ScriptController scriptController;

    @Autowired
    ScriptRepository scriptRepository;

    @Before
    public void setUpScriptController(){
        scriptController = mock(ScriptController.class);
    }

    @Test
    public void checkIfNonExistingScriptSaves() {
        Script script = new Script("1", "1");
        if(!scriptRepository.findByTitle(script.getTitle()).isPresent()){
            scriptController.uploadScript(script.getTitle(), script.getText());
        }
        verify(scriptController, times(1)).uploadScript(script.getTitle(), script.getText());
    }

    @Test(expected = DuplicateKeyException.class)
    public void checkIfExistingScriptThrowAnException() {
        Script script = new Script("first", "A");

        if(scriptRepository.findByTitle(script.getTitle()).isPresent()){
            doThrow(DuplicateKeyException.class)
                    .when(scriptController)
                    .uploadScript(script.getTitle(), script.getText());
        } else{
            fail();
        }

        scriptController.uploadScript(script.getTitle(), script.getText());
    }


}
