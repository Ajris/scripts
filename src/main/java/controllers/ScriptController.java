package controllers;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import services.script.ScriptService;

@RestController
public class ScriptController {

    private final ScriptService scriptService;

    public ScriptController(ScriptService scriptService) {
        this.scriptService = scriptService;
    }

    @PostMapping(value = "/uploadScript")
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadScript(@RequestParam(name = "scriptTitle") String title,
                             @RequestParam(name = "scriptText") String text,
                             @RequestParam(name = "scriptDescription") String description) {
        scriptService.uploadScript(title, text, description);
    }

    @GetMapping(value = "/scripts/{scriptName}", produces = "application/json")
    public ResponseEntity<InputStreamResource> downloadScript(@PathVariable("scriptName") String scriptName) {
        return scriptService.downloadScript(scriptName);
    }
}
