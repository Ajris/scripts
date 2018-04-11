package services.script;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import services.DownloadScriptService;

@Service
public class ScriptService {

    private final ScriptFileService scriptFileService;
    private final DownloadScriptService downloadScriptService;
    private final UploadFileService uploadFileService;

    public ScriptService(ScriptFileService scriptFileService, DownloadScriptService downloadScriptService, UploadFileService uploadFileService) {
        this.scriptFileService = scriptFileService;
        this.downloadScriptService = downloadScriptService;
        this.uploadFileService = uploadFileService;
    }

    public ResponseEntity<InputStreamResource> downloadScript(String scriptName) {
        return downloadScriptService.downloadScript(scriptFileService.getScript(scriptName));
    }

    public void uploadScript(String title, String text) {
        uploadFileService.uploadScript(scriptFileService.createScript(title, text));
    }
}
