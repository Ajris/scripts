package services.launcher;

import entity.Script;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import services.DownloadFileService;
import services.ScriptRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Optional;

@Service
public class LauncherService {

    private LauncherFileService launcherFileService;
    private DownloadFileService downloadFileService;
    private ScriptRepository scriptRepository;

    public LauncherService(LauncherFileService launcherFileService, DownloadFileService downloadFileService, ScriptRepository scriptRepository) {
        this.launcherFileService = launcherFileService;
        this.downloadFileService = downloadFileService;
        this.scriptRepository = scriptRepository;
    }

    public void downloadLauncher(HttpServletRequest request, HttpServletResponse response) {
        File launcher = launcherFileService.prepareLauncherFile(request);

        downloadFileService.prepareResponseAndDownloadFile(response, launcher);
    }

    public ResponseEntity<String> createNewScript(HttpServletRequest request) {

        Optional<String> title = Optional.ofNullable(request.getParameter("scriptTitle"));
        Optional<String> text = Optional.ofNullable(request.getParameter("scriptText"));

        File file = new File("/home/ajris/scripts/" + title.orElse(""));

        try {
            PrintWriter writer = new PrintWriter(file);
            writer.println(text);
            writer.close();
            return new ResponseEntity<>("Successfully saved file.", HttpStatus.OK);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Sth went wrong, maybe wrong title?", HttpStatus.NOT_FOUND);
        }
    }
}
