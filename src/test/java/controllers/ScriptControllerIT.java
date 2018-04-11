package controllers;

import entity.Script;
import exception.DataNotFoundException;
import org.junit.Before;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;
import services.DownloadService;
import services.script.ScriptDownloadService;
import services.script.ScriptFileService;
import services.script.ScriptRepository;
import services.script.ScriptService;
import services.script.ScriptUploadService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
@EnableMongoRepositories(basePackages = {"services"})
@ContextConfiguration(classes = {
        ScriptRepository.class,
        ScriptService.class,
        ScriptFileService.class,
        ScriptDownloadService.class,
        ScriptRepository.class,
        ScriptController.class,
        ScriptUploadService.class,
        DownloadService.class
})
public class ScriptControllerIT {

    @Mock
    private ScriptController scriptController;

    @Autowired
    private ScriptRepository scriptRepository;

    @Autowired
    WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }


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

    @Test(expected = NestedServletException.class) //TODO
    public void isRespondToTheNonExistingScriptSendingException() throws Exception {
        this.mockMvc
                .perform(get("/scripts/1"))
                .andExpect(status().is(204))
                .andDo(print());
    }

    @Test
    public void isRequestToTheExistingScriptGeneratedAsExpected() throws Exception {
        String existingScript = "first";

        this.mockMvc
                .perform(get("/scripts/" + existingScript))
                .andDo(print())
                .andExpect(content().contentType("application/octet-stream"))
                .andExpect(content().string("a"));
    }
}
