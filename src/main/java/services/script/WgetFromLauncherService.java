package services.script;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import services.DownloadFileService;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Service
public class WgetFromLauncherService {

    private ScriptFileService scriptFileService;
    private DownloadFileService downloadFileService;

    public WgetFromLauncherService(ScriptFileService scriptFileService, DownloadFileService downloadFileService) {
        this.scriptFileService = scriptFileService;
        this.downloadFileService = downloadFileService;
    }

    public ResponseEntity<InputStreamResource> downloadScript(String scriptName) throws IOException {
        String file = scriptFileService.getFile(scriptName);

        return downloadFileService.downloadFile1(file);
    }
}
