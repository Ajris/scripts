package services.script;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import services.DownloadFileService;

@Service
public class WgetFromLauncherService {

    private ScriptFileService scriptFileService;
    private DownloadFileService downloadFileService;

    public WgetFromLauncherService(ScriptFileService scriptFileService, DownloadFileService downloadFileService) {
        this.scriptFileService = scriptFileService;
        this.downloadFileService = downloadFileService;
    }

    public ResponseEntity<InputStreamResource> downloadScript(String scriptName) {
        String file = scriptFileService.getFile(scriptName);

        return downloadFileService.downloadFile1(file);
    }
}
