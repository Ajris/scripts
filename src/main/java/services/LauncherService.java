package services;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

@Service
public class LauncherService {

    private LauncherFileService launcherFileService;
    private DownloadFileService downloadFileService;

    public LauncherService(LauncherFileService launcherFileService, DownloadFileService downloadFileService) {
        this.launcherFileService = launcherFileService;
        this.downloadFileService = downloadFileService;
    }

    public void prepareLauncherAndStartDownload(HttpServletRequest request, HttpServletResponse response) {
        File launcher = launcherFileService.prepareLauncherFile(request);

        downloadFileService.prepareResponseAndDownloadFile(response, launcher);
    }
}
