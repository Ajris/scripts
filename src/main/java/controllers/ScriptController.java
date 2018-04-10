package controllers;

import entity.Script;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import services.ScriptRepository;
import services.ScriptService;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class ScriptController {

    private ScriptService scriptService;

    public ScriptController(ScriptService scriptService) {
        this.scriptService = scriptService;
    }

    @PostMapping(value = "/uploadScript")
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadScript(@RequestParam(name = "scriptTitle") String title,
                             @RequestParam(name = "scriptText") String text) {
        scriptService.uploadScript(title, text);
    }
}
