package services.script;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import services.DownloadFileService;
import services.UploadFileService;

@Service
public class ScriptService {

    private ScriptFileService scriptFileService;
    private DownloadFileService downloadFileService;
    private UploadFileService uploadFileService;

    public ScriptService(ScriptFileService scriptFileService, DownloadFileService downloadFileService, UploadFileService uploadFileService) {
        this.scriptFileService = scriptFileService;
        this.downloadFileService = downloadFileService;
        this.uploadFileService = uploadFileService;
    }

    public ResponseEntity<InputStreamResource> downloadScript(String scriptName) {
        return downloadFileService.downloadFile1(scriptFileService.getScript(scriptName));
    }

    public void uploadScript(String title, String text) {
        uploadFileService.uploadScript(scriptFileService.createScript(title, text));
    }
}
