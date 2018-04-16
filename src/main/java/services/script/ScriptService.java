package services.script;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ScriptService {

    private final ScriptFileService scriptFileService;
    private final ScriptDownloadService scriptDownloadService;
    private final ScriptUploadService scriptUploadService;

    public ScriptService(ScriptFileService scriptFileService, ScriptDownloadService scriptDownloadService, ScriptUploadService scriptUploadService) {
        this.scriptFileService = scriptFileService;
        this.scriptDownloadService = scriptDownloadService;
        this.scriptUploadService = scriptUploadService;
    }

    public ResponseEntity<InputStreamResource> downloadScript(String scriptName) {
        return scriptDownloadService.downloadScript(scriptFileService.getScript(scriptName));
    }

    public void uploadScript(String title, String text, String description) {
        scriptUploadService.uploadScript(scriptFileService.createScript(title, text, description));
    }
}
