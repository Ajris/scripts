package controllers;

import org.springframework.core.io.InputStreamResource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import services.launcher.LauncherService;
import temporary.ValuesForCreator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.ByteArrayInputStream;

@RestController
public class LauncherController {

    private static final String SHELL_SCRIPT = "application/x-sh";

    private LauncherService launcherService;

    public LauncherController(LauncherService launcherService) {
        this.launcherService = launcherService;
    }

    @GetMapping(value = "/launcher", produces = SHELL_SCRIPT)
    public void getLauncherForScripts(HttpServletRequest request, HttpServletResponse response) {
        launcherService.downloadLauncher(request, response);
    }

    @GetMapping(value = "/launcher1", produces = "application/json")
    public ResponseEntity<InputStreamResource> downloadScript(@RequestParam("script") String[] scriptNames) {
        String toDownload = "";
        toDownload += ValuesForCreator.INTERPRETER.toString();
        toDownload += "\n";

        for(String scriptName : scriptNames){
            toDownload += ValuesForCreator.WGETCOMMAND.toString() + scriptName + "\n";
        }

        for(String scriptName : scriptNames){
            toDownload += ValuesForCreator.CHMODCOMMAND.toString() + scriptName + "\n";
        }

        for(String scriptName : scriptNames){
            toDownload += ValuesForCreator.EXECUTECOMMAND.toString() + scriptName + "\n";
        }

        return ResponseEntity
                .ok()
                .contentLength(toDownload.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(new ByteArrayInputStream(toDownload.getBytes())));
    }
}
