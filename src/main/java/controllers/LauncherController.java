package controllers;
import org.springframework.web.bind.annotation.*;
import services.launcher.LauncherService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;

@RestController
public class LauncherController {

    private static final String SHELL_SCRIPT = "application/x-sh";

    private LauncherService launcherService;

    public LauncherController(LauncherService launcherService) {
        this.launcherService = launcherService;
    }

    @GetMapping(value = "/launcherDownloader", produces = SHELL_SCRIPT)
    public void getLauncherForScripts(HttpServletRequest request, HttpServletResponse response) {
        launcherService.prepareLauncherAndStartDownload(request,response);
    }

    @PostMapping(value = "/launcherUploader")
    public void uploadLauncher(HttpServletRequest request) throws FileNotFoundException{
        String title = request.getParameter("scriptTitle").trim();
        String text = request.getParameter("scriptText").trim();
        File file = new File("/home/ajris/scripts/" + title);
        PrintWriter writer = new PrintWriter(file);
        writer.println(text);
        writer.close();
    }
}
