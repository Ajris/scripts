package controllers;

import entity.Script;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;
import services.DownloadFileService;
import services.ResponseService;
import services.ScriptRepository;
import services.script.ScriptFileService;
import services.script.WgetFromLauncherService;
import temporary.ValuesForCreator;


import java.io.FileNotFoundException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@EnableAutoConfiguration
@EnableMongoRepositories(basePackages = {"services"})
@ContextConfiguration(classes = {
        WgetFromLauncherController.class,
        WgetFromLauncherService.class,
        ResponseService.class,
        ScriptFileService.class,
        DownloadFileService.class,
        ScriptRepository.class
})
public class WgetFromLauncherControllerIT {

    @Autowired
    WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }


    @Test(expected = NestedServletException.class)
    public void isRespondToTheNonExistingScriptSendingException() throws Exception {
        this.mockMvc
                .perform(get("/scripts/1"))
                .andDo(print());
    }
    @Test
    public void isRequestToTheExistingScriptGeneratedAsExpected() throws Exception{
        String existingScript = "first";

        this.mockMvc
                .perform(get("/scripts/" + existingScript))
                .andDo(print())
                .andExpect(content().contentType("application/octet-stream"))
                .andExpect(content().string("a"));
    }
}
