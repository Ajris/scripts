package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.ScriptService;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
public class ScriptsController {

    private static final String SHELL_SCRIPT = "application/x-sh";

    private ScriptService scriptService;

    @Autowired
    public ScriptsController(ScriptService scriptService) {
        this.scriptService = scriptService;
    }

    @GetMapping(value = "/scripts/{scriptName}", produces = SHELL_SCRIPT)
    public void downloadScript(HttpServletResponse response, @PathVariable("scriptName") String scriptName) throws IOException {
        scriptService.prepareScriptAndStartDownload(response, scriptName);
    }
}
