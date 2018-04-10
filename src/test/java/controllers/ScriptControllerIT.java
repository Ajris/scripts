package controllers;

import entity.Script;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import services.ScriptRepository;

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
    private ScriptController scriptController;

    @Autowired
    private ScriptRepository scriptRepository;

    @Test
    public void checkIfNonExistingScriptSaves() {
        Script script = new Script("1", "1");
        doNothing().when(scriptController).uploadScript(script.getTitle(), script.getText());
        if (!scriptRepository.findByTitle(script.getTitle()).isPresent()) {
            scriptController.uploadScript(script.getTitle(), script.getText());
        }
        verify(scriptController, times(1)).uploadScript(script.getTitle(), script.getText());
    }

    @Test(expected = DuplicateKeyException.class)
    public void checkIfExistingScriptThrowAnException() {
        Script script = new Script("first", "A");
        if (scriptRepository.findByTitle(script.getTitle()).isPresent()) {
            when(scriptRepository.save(script)).thenThrow(DuplicateKeyException.class);
        }
        scriptController.uploadScript(script.getTitle(), script.getText());
    }
}
