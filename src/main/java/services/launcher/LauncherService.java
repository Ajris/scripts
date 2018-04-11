package services.launcher;

import creators.LauncherCreator;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import services.DownloadService;

@Service
public class LauncherService {

    private final DownloadService downloadService;

    public LauncherService(DownloadService downloadService) {
        this.downloadService = downloadService;
    }

    public ResponseEntity<InputStreamResource> downloadLauncher(String[] scriptNames) {
        String launcherText = new LauncherCreator(scriptNames).getText().toString();

        return downloadService.download(launcherText);
    }
}
