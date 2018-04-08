package services;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

@Service
public class LauncherService {

    private LauncherFileService launcherFileService;
    private ResponseService responseService;
    private DownloadFileService downloadFileService;

    public LauncherService(LauncherFileService launcherFileService, ResponseService responseService, DownloadFileService downloadFileService) {
        this.launcherFileService = launcherFileService;
        this.responseService = responseService;
        this.downloadFileService = downloadFileService;
    }

    public void prepareLauncherAndStartDownload(HttpServletRequest request, HttpServletResponse response) {
        File launcher = launcherFileService.prepareLauncherFile(request);
        response = responseService.prepareResponse(response, launcher.getName(), launcher.length());

        downloadFileService.downloadFile(response, launcher);
    }
}
