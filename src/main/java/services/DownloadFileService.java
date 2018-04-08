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

    private static Logger logger = Logger.getLogger("InfoLogging");

    public void downloadFile(HttpServletResponse response, File file){
        try (InputStream in = new FileInputStream(file)) {
            FileCopyUtils.copy(in, response.getOutputStream());
        } catch (IOException e) {
            logger.log(Level.ALL, e.toString());
        }
    }
}
