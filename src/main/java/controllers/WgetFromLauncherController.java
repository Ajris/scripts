package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import services.script.ScriptService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class WgetFromLauncherController {

    private static final String SHELL_SCRIPT = "application/x-sh";

    private ScriptService scriptService;

    @Autowired
    public WgetFromLauncherController(ScriptService scriptService) {
        this.scriptService = scriptService;
    }

    @GetMapping(value = "/scripts/{scriptName}", produces = SHELL_SCRIPT)
    public void downloadScript(HttpServletResponse response, @PathVariable("scriptName") String scriptName) throws IOException {
        scriptService.downloadScript(response, scriptName);
    }
}
