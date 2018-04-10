package controllers;

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
import services.script.ScriptService;

import java.io.IOException;

@RestController
public class ScriptController {

    private ScriptService scriptService;

    public ScriptController(ScriptService scriptService) {
        this.scriptService = scriptService;
    }

    @PostMapping(value = "/uploadScript")
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadScript(@RequestParam(name = "scriptTitle") String title,
                             @RequestParam(name = "scriptText") String text) throws DuplicateKeyException {
        scriptService.uploadScript(title, text);
    }

    @GetMapping(value = "/scripts/{scriptName}", produces = "application/json")
    public ResponseEntity<InputStreamResource> downloadScript(@PathVariable("scriptName") String scriptName) throws IOException {
        return scriptService.downloadScript(scriptName);
    }
}
