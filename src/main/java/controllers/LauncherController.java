package controllers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.launcher.LauncherService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Null;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.util.Optional;

@RestController
public class LauncherController {

    private static final String SHELL_SCRIPT = "application/x-sh";

    private LauncherService launcherService;

    public LauncherController(LauncherService launcherService) {
        this.launcherService = launcherService;
    }

    @GetMapping(value = "/launcher", produces = SHELL_SCRIPT)
    public void getLauncherForScripts(HttpServletRequest request, HttpServletResponse response) {
        launcherService.prepareLauncherAndStartDownload(request,response);
    }

    @PostMapping(value = "/launcher")
    public ResponseEntity<String> uploadLauncher(HttpServletRequest request){

        String title = request.getParameter("scriptTitle");
        String text = request.getParameter("scriptText");

        if(title == null)
            title = "";
        title = title.trim();

        if(text != null)
            text = text.trim();

        File file = new File("/home/ajris/scripts/" + title);

        try{
            PrintWriter writer = new PrintWriter(file);
            writer.println(text);
            writer.close();
            return new ResponseEntity<>("Successfully saved file.",HttpStatus.OK);
        }catch(FileNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>("Sth went wrong, maybe wrong title?",HttpStatus.NOT_FOUND);
        }
    }
}
