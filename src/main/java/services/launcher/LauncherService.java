package services.launcher;

import org.springframework.stereotype.Service;
import services.DownloadFileService;

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

    public void downloadLauncher(HttpServletRequest request, HttpServletResponse response) {
        File launcher = launcherFileService.prepareLauncherFile(request);

        downloadFileService.downloadFile(response, launcher);
    }
}
