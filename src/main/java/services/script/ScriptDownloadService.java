package services.script;

import entity.Script;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import services.DownloadService;

@Service
public class ScriptDownloadService {

    private final DownloadService downloadService;

    public ScriptDownloadService(DownloadService downloadService) {
        this.downloadService = downloadService;
    }

    public ResponseEntity<InputStreamResource> downloadScript(Script script) {
        return downloadService.download(script.getText());
    }
}
