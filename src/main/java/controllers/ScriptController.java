package controllers;

import entity.Script;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import services.ScriptRepository;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class ScriptController {

    private static Logger LOGGER = Logger.getLogger(ScriptController.class.getName());

    private ScriptRepository scriptRepository;

    public ScriptController(ScriptRepository scriptRepository) {
        this.scriptRepository = scriptRepository;
    }

    @PostMapping(value = "/uploadScript")
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadScript(@RequestParam(name = "scriptTitle") String title,
                             @RequestParam(name = "scriptText") String text) {
        try {
            scriptRepository.save(new Script(title, text));
        } catch (DuplicateKeyException e) {
            LOGGER.log(Level.WARNING, "Duplicated Key Exception while uploading");
        }
    }
}
