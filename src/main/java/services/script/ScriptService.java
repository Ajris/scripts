package services.script;

import controllers.ScriptController;
import entity.Script;
import org.springframework.core.io.InputStreamResource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import services.DownloadFileService;
import services.ScriptRepository;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ScriptService {

    private static Logger LOGGER = Logger.getLogger(ScriptService.class.getName());


    private ScriptFileService scriptFileService;
    private DownloadFileService downloadFileService;
    private ScriptRepository scriptRepository;

    public ScriptService(ScriptFileService scriptFileService, DownloadFileService downloadFileService, ScriptRepository scriptRepository) {
        this.scriptFileService = scriptFileService;
        this.downloadFileService = downloadFileService;
        this.scriptRepository = scriptRepository;
    }

    public ResponseEntity<InputStreamResource> downloadScript(String scriptName) {
        Script file = scriptFileService.getFile(scriptName);

        return downloadFileService.downloadFile1(file);
    }

    public void uploadScript(String title, String text){
        try {
            scriptRepository.save(new Script(title, text));
        } catch (DuplicateKeyException e) {
            LOGGER.log(Level.WARNING, "Duplicated Key Exception while uploading");
        }
    }
}
