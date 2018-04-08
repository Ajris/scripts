package services;

import creators.LauncherCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import temporary.ValuesForController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class LauncherService {

    private static Logger logger = Logger.getLogger("InfoLogging");
    private static final String SHELL_SCRIPT = "application/x-sh";

    private LauncherFileService launcherFileService;

    @Autowired
    public LauncherService(LauncherFileService launcherFileService) {
        this.launcherFileService = launcherFileService;
    }

    private HttpServletResponse prepareResponse(HttpServletResponse response, String fileName, Long length){
        response.setContentType(SHELL_SCRIPT);
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        response.setContentLengthLong(length);

        return response;
    }

    public void startDownloading(HttpServletRequest request, HttpServletResponse response){
        File launcher = launcherFileService.prepareFile(request);
        response = prepareResponse(response, launcher.getName(), launcher.length());

        try(InputStream in = new FileInputStream(launcher)){
            FileCopyUtils.copy(in, response.getOutputStream());
        }catch(IOException e){
            logger.log(Level.ALL, e.toString());
        }
    }
}
