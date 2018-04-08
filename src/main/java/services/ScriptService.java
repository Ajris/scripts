package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ScriptService {

    private static Logger logger = Logger.getLogger("InfoLogging");

    private ResponseService responseService;
    private ScriptFileService scriptFileService;

    @Autowired
    public ScriptService(ResponseService responseService, ScriptFileService scriptFileService) {
        this.responseService = responseService;
        this.scriptFileService = scriptFileService;
    }

    public void prepareScriptAndStartDownload(HttpServletResponse response, String scriptName) throws IOException {
        File file = scriptFileService.getFile(scriptName);
        response = responseService.prepareResponse(response, file.getName(), file.length());

        try (InputStream in = new FileInputStream(file)) {
            FileCopyUtils.copy(in, response.getOutputStream());
        } catch (IOException e) {
            logger.log(Level.ALL, e.toString());
        }
    }
}
