package services;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Service
public class ScriptService {

    private ResponseService responseService;
    private ScriptFileService scriptFileService;
    private DownloadFileService downloadFileService;

    public ScriptService(ResponseService responseService, ScriptFileService scriptFileService, DownloadFileService downloadFileService) {
        this.responseService = responseService;
        this.scriptFileService = scriptFileService;
        this.downloadFileService = downloadFileService;
    }

    public void prepareScriptAndStartDownload(HttpServletResponse response, String scriptName) throws IOException {
        File file = scriptFileService.getFile(scriptName);
        response = responseService.prepareResponse(response, file.getName(), file.length());

        downloadFileService.downloadFile(response, file);
    }
}
