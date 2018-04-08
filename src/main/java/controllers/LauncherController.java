package controllers;
import org.springframework.web.bind.annotation.*;
import services.LauncherService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LauncherController {

    private static final String SHELL_SCRIPT = "application/x-sh";

    private LauncherService launcherService;

    public LauncherController(LauncherService launcherService) {
        this.launcherService = launcherService;
    }

    @GetMapping(value = "/launcherDownloader", produces = SHELL_SCRIPT)
    public void getLauncherForScripts(HttpServletRequest request, HttpServletResponse response) {
        launcherService.startDownloading(request,response);
    }
}
