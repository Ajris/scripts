package controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import services.LauncherFileService;
import services.LauncherService;
import temporary.ValuesForCreator;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {LauncherController.class, LauncherService.class, LauncherFileService.class})
public class LauncherControllerIT {

    @Autowired
    WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void isRespondWithoutParametersGeneratedAsExpected() throws Exception {
        this.mockMvc
                .perform(get("/launcherDownloader"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Disposition", "attachment; filename=" + ValuesForCreator.LAUNCHERNAME.toString()))
                .andExpect(content().contentType("application/x-sh"))
                .andExpect(content().string(containsString(ValuesForCreator.INTERPRETER.toString())));
    }

    @Test
    public void isRespondWith1ParameterGeneratedAsExpected() throws Exception {

        String firstScript = "showFiles";

        this.mockMvc
                .perform(get("/launcherDownloader")
                        .param("script", firstScript))

                .andDo(print())

                .andExpect(status().isOk())
                .andExpect(header().string("Content-Disposition", "attachment; filename=" + ValuesForCreator.LAUNCHERNAME.toString()))
                .andExpect(content().contentType("application/x-sh"))

                .andExpect(content().string(containsString(ValuesForCreator.INTERPRETER.toString())))

                .andExpect(content().string(containsString(ValuesForCreator.WGETCOMMAND.toString() + firstScript)))

                .andExpect(content().string(containsString(ValuesForCreator.CHMODCOMMAND.toString() + firstScript)))

                .andExpect(content().string(containsString(ValuesForCreator.EXECUTECOMMAND.toString() + firstScript)));
    }

    @Test
    public void isRespondWith2ParametersGeneratedAsExpected() throws Exception {

        String firstScript = "showFiles";
        String secondScript = "showProcesses";

        this.mockMvc
                .perform(get("/launcherDownloader")
                        .param("script", firstScript)
                        .param("script", secondScript))

                .andDo(print())

                .andExpect(status().isOk())
                .andExpect(header().string("Content-Disposition", "attachment; filename=" + ValuesForCreator.LAUNCHERNAME.toString()))
                .andExpect(content().contentType("application/x-sh"))

                .andExpect(content().string(containsString(ValuesForCreator.INTERPRETER.toString())))

                .andExpect(content().string(containsString(ValuesForCreator.WGETCOMMAND.toString() + firstScript)))
                .andExpect(content().string(containsString(ValuesForCreator.WGETCOMMAND.toString() + secondScript)))

                .andExpect(content().string(containsString(ValuesForCreator.CHMODCOMMAND.toString() + firstScript)))
                .andExpect(content().string(containsString(ValuesForCreator.CHMODCOMMAND.toString() + secondScript)))

                .andExpect(content().string(containsString(ValuesForCreator.EXECUTECOMMAND.toString() + firstScript)))
                .andExpect(content().string(containsString(ValuesForCreator.EXECUTECOMMAND.toString() + secondScript)));
    }

    @Test
    public void isRespondWith3ParametersGeneratedAsExpected() throws Exception {

        String firstScript = "showFiles";
        String secondScript = "showProcesses";
        String thirdScript = "numOfFilesInHome";

        this.mockMvc
                .perform(get("/launcherDownloader")
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
