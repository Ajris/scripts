package services;

import entity.Script;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DownloadScriptService {

    private DownloadService downloadService;

    public DownloadScriptService(DownloadService downloadService) {
        this.downloadService = downloadService;
    }

    public ResponseEntity<InputStreamResource> downloadScript(Script script) {
        return downloadService.download(script.getText());
    }
}
