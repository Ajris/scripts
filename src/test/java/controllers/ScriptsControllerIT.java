package controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import services.DownloadFileService;
import services.ResponseService;
import services.script.ScriptFileService;
import services.script.ScriptService;
import temporary.ValuesForCreator;


import java.io.FileNotFoundException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {
        ScriptsController.class,
        ScriptService.class,
        ResponseService.class,
        ScriptFileService.class,
        DownloadFileService.class
})
public class ScriptsControllerIT {

    @Autowired
    WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }


    @Test(expected = FileNotFoundException.class)
    public void isRespondToTheNonExistingScriptSendingException() throws Exception {
        this.mockMvc
                .perform(get("/scripts/1"))
                .andDo(print());
    }
    @Test
    public void isRequestToTheExistingScriptGeneratedAsExpected() throws Exception{
        String existingScript = "showFiles";

        this.mockMvc
                .perform(get("/scripts/" + existingScript))
                .andDo(print())
                .andExpect(header().string("Content-Disposition", "attachment; filename=" + existingScript))
                .andExpect(content().contentType("application/x-sh"))
                .andExpect(content().string(containsString(ValuesForCreator.INTERPRETER.toString())))
                .andExpect(content().string(containsString("ls -al;")));
    }
}
