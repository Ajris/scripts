package controllers;

import entity.Script;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import services.ScriptRepository;
import services.launcher.LauncherService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LauncherController {

    private static final String SHELL_SCRIPT = "application/x-sh";

    private LauncherService launcherService;

    private final ScriptRepository scriptRepository;

    @Autowired
    public LauncherController(LauncherService launcherService, ScriptRepository scriptRepository) {
        this.launcherService = launcherService;
        this.scriptRepository = scriptRepository;
    }

    @GetMapping(value = "/launcher", produces = SHELL_SCRIPT)
    public void getLauncherForScripts(HttpServletRequest request, HttpServletResponse response) {
        launcherService.downloadLauncher(request, response);
    }

    @PostMapping(value = "/launcher")
    public ResponseEntity<String> uploadLauncher(HttpServletRequest request) {
        return launcherService.createNewScript(request);
    }
}
