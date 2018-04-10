package services;

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
public class DownloadFileService {

    private static Logger LOGGER = Logger.getLogger("InfoLogging");

    private ResponseService responseService;

    public DownloadFileService(ResponseService responseService) {
        this.responseService = responseService;
    }

    public void downloadFile(HttpServletResponse response, File file){
        responseService.prepareResponse(response, file.getName(), file.length());

        try (InputStream in = new FileInputStream(file)) {
            FileCopyUtils.copy(in, response.getOutputStream());
        } catch (IOException e) {
            LOGGER.log(Level.ALL, e.toString());
        }
    }
}
