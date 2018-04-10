package controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import services.DownloadFileService;
import services.ScriptRepository;
import services.ResponseService;
import services.launcher.LauncherFileService;
import services.launcher.LauncherService;
import temporary.ValuesForCreator;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {
        LauncherController.class,
        LauncherService.class,
        LauncherFileService.class,
        ResponseService.class,
        DownloadFileService.class,
        ScriptRepository.class
})
@EnableAutoConfiguration
@EnableMongoRepositories(basePackages = {"services"})
public class LauncherControllerIT {

    @Autowired
    WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;


    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void isGetWith0ParametersCorrect() throws Exception {
        this.mockMvc
                .perform(get("/launcher"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Disposition", "attachment; filename=" + ValuesForCreator.LAUNCHERNAME.toString()))
                .andExpect(content().contentType("application/x-sh"))
                .andExpect(content().string(containsString(ValuesForCreator.INTERPRETER.toString())));
    }

    @Test
    public void isGetWith3ParametersCorrect() throws Exception {

        String firstScript = "showFiles";
        String secondScript = "showProcesses";
        String thirdScript = "numOfFilesInHome";

        this.mockMvc
                .perform(get("/launcher")
                        .param("script", firstScript)
                        .param("script", secondScript)
                        .param("script", thirdScript))

                .andDo(print())

                .andExpect(status().isOk())
                .andExpect(header().string("Content-Disposition", "attachment; filename=" + ValuesForCreator.LAUNCHERNAME.toString()))
                .andExpect(content().contentType("application/x-sh"))

                .andExpect(content().string(containsString(ValuesForCreator.INTERPRETER.toString())))

                .andExpect(content().string(containsString(ValuesForCreator.WGETCOMMAND.toString() + firstScript)))
                .andExpect(content().string(containsString(ValuesForCreator.WGETCOMMAND.toString() + secondScript)))
                .andExpect(content().string(containsString(ValuesForCreator.WGETCOMMAND.toString() + thirdScript)))

                .andExpect(content().string(containsString(ValuesForCreator.CHMODCOMMAND.toString() + firstScript)))
                .andExpect(content().string(containsString(ValuesForCreator.CHMODCOMMAND.toString() + secondScript)))
                .andExpect(content().string(containsString(ValuesForCreator.CHMODCOMMAND.toString() + thirdScript)))

                .andExpect(content().string(containsString(ValuesForCreator.EXECUTECOMMAND.toString() + firstScript)))
                .andExpect(content().string(containsString(ValuesForCreator.EXECUTECOMMAND.toString() + secondScript)))
                .andExpect(content().string(containsString(ValuesForCreator.EXECUTECOMMAND.toString() + thirdScript)));
    }
}
