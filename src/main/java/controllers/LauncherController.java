package controllers;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import services.launcher.LauncherService;

@RestController
public class LauncherController {

    private final LauncherService launcherService;

    public LauncherController(LauncherService launcherService) {
        this.launcherService = launcherService;
    }

    @GetMapping(value = "/launcher")
    public ResponseEntity<InputStreamResource> downloadScript(@RequestParam("script") String[] scriptNames) {
        System.out.println("XD");
        return launcherService.downloadLauncher(scriptNames);
    }
}
