package controllers;

import controllers.advice.ScriptControllerAdvice;
import entity.Script;
import exception.DataNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
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

import javax.xml.crypto.Data;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @Mock
    private ScriptControllerAdvice scriptControllerAdvice;

    @Mock
    private ScriptFileService scriptFileService;

    @Autowired
    private ScriptRepository scriptRepository;

    @Autowired
    WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }


    @Ignore
    @Test
    public void checkIfNonExistingScriptSaves() throws Exception{
        Script script = new Script("xxx1", "1", "1");
        if (!scriptRepository.findByTitle(script.getTitle()).isPresent()) {
            mockMvc.perform(post("/scripts/" + script.getTitle())
                    .param("scriptTitle", script.getTitle())
                    .param("scriptDescription", script.getDescription())
                    .param("scriptText", script.getText()))
                    .andExpect(status().isOk());
        }
        Assert.assertEquals(script, scriptRepository.findByTitle(script.getTitle()).get());
    }

    @Test(expected = DuplicateKeyException.class)
    public void checkIfExistingScriptThrowAnException() {
        Script script = new Script("title", "A", "1");
        if (scriptRepository.findByTitle(script.getTitle()).isPresent()) {
            doNothing().when(scriptRepository.save(script));
        }
        scriptController.uploadScript(script.getTitle(), script.getText(), script.getDescription());
    }

    @Test(expected = NestedServletException.class) //TODO
    public void isRespondToTheNonExistingScriptSendingException() throws Exception {
        this.mockMvc
                .perform(get("/scripts/dx1"))
                .andExpect(status().is(204))
                .andDo(print());
        verify(scriptControllerAdvice, times(1)).dataNotFoundHandler();
    }

    @Test
    public void isRequestToTheExistingScriptGeneratedAsExpected() throws Exception {
        String existingScript = "title";

        this.mockMvc
                .perform(get("/scripts/" + existingScript))
                .andDo(print())
                .andExpect(content().contentType("application/octet-stream"))
                .andExpect(content().string("Paste your script here."));
    }
}
