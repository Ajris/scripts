package services;

import controllers.ScriptController;
import entity.Script;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ScriptService {

    private static Logger LOGGER = Logger.getLogger(ScriptService.class.getName());

    private ScriptRepository scriptRepository;

    @Autowired
    public ScriptService(ScriptRepository scriptRepository) {
        this.scriptRepository = scriptRepository;
    }

    public void uploadScript(String title, String text) throws DuplicateKeyException{
        try {
            scriptRepository.save(new Script(title, text));
        } catch (DuplicateKeyException e) {
            LOGGER.log(Level.WARNING, "Duplicated Key Exception while uploading");
        }
    }
}
