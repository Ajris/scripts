package services;

import entity.Script;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UploadFileService {

    private static Logger LOGGER = Logger.getLogger(UploadFileService.class.getName());

    private ScriptRepository scriptRepository;

    public UploadFileService(ScriptRepository scriptRepository) {
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
