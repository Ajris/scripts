package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class LauncherService {

    private static Logger logger = Logger.getLogger("InfoLogging");

    private LauncherFileService launcherFileService;
    private ResponseService responseService;

    @Autowired
    public LauncherService(LauncherFileService launcherFileService, ResponseService responseService) {
        this.launcherFileService = launcherFileService;
        this.responseService = responseService;
    }

    public void prepareLauncherAndStartDownload(HttpServletRequest request, HttpServletResponse response) {
        File launcher = launcherFileService.prepareLauncherFile(request);
        response = responseService.prepareResponse(response, launcher.getName(), launcher.length());

        try (InputStream in = new FileInputStream(launcher)) {
            FileCopyUtils.copy(in, response.getOutputStream());
        } catch (IOException e) {
            logger.log(Level.ALL, e.toString());
        }
    }
}
