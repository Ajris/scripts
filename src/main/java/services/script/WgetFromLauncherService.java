package services.script;

import org.springframework.stereotype.Service;
import services.DownloadFileService;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Service
public class ScriptService {

    private ScriptFileService scriptFileService;
    private DownloadFileService downloadFileService;

    public ScriptService(ScriptFileService scriptFileService, DownloadFileService downloadFileService) {
        this.scriptFileService = scriptFileService;
        this.downloadFileService = downloadFileService;
    }

    public void downloadScript(HttpServletResponse response, String scriptName) throws IOException {
        File file = scriptFileService.getFile(scriptName);

        downloadFileService.downloadFile(response, file);
    }
}
