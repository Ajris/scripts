package controllers;

import entity.Script;
import org.springframework.core.io.InputStreamResource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import services.ScriptRepository;
import services.script.WgetFromLauncherService;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class ScriptController {

    private static Logger LOGGER = Logger.getLogger(ScriptController.class.getName());

    private ScriptRepository scriptRepository;
    private WgetFromLauncherService wgetFromLauncherService;

    public ScriptController(ScriptRepository scriptRepository, WgetFromLauncherService wgetFromLauncherService) {
        this.scriptRepository = scriptRepository;
        this.wgetFromLauncherService = wgetFromLauncherService;
    }

    @PostMapping(value = "/uploadScript")
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadScript(@RequestParam(name = "scriptTitle") String title,
                             @RequestParam(name = "scriptText") String text) throws DuplicateKeyException {
        try {
            scriptRepository.save(new Script(title, text));
        } catch (DuplicateKeyException e) {
            LOGGER.log(Level.WARNING, "Duplicated Key Exception while uploading");
        }
    }

    @GetMapping(value = "/scripts/{scriptName}", produces = "application/json")
    public ResponseEntity<InputStreamResource> downloadScript(@PathVariable("scriptName") String scriptName) throws IOException {
        return wgetFromLauncherService.downloadScript(scriptName);
    }
}
