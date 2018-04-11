package services.script;

import entity.Script;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ScriptUploadService {

    private static final Logger LOGGER = Logger.getLogger(ScriptUploadService.class.getName());

    private final ScriptRepository scriptRepository;

    public ScriptUploadService(ScriptRepository scriptRepository) {
        this.scriptRepository = scriptRepository;
    }

    public void uploadScript(Script script) {
        try {
            scriptRepository.save(script);
        } catch (DuplicateKeyException e) {
            LOGGER.log(Level.WARNING, "Duplicated Key Exception while uploading");
        }
    }
}
