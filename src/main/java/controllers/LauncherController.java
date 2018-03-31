package controllers;

import creators.LauncherCreator;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import temporary.ValuesForController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@RestController
public class LauncherController {

    private static final String SHELL_SCRIPT = "application/x-sh";

    @RequestMapping(value = "/launcherDownloader", method = RequestMethod.GET, produces = SHELL_SCRIPT)
    @ResponseStatus
    public void getLauncherForScripts(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Optional<String[]> scriptsToDownload = Optional.ofNullable(request.getParameterValues(ValuesForController.NAMEINCHECKBOX.toString()));

        File launcher = new LauncherCreator()
                .buildLauncher(scriptsToDownload.orElse(new String[0]))
                .getLauncher();

        response.setContentType(SHELL_SCRIPT);
        response.setHeader("Content-Disposition", "attachment; filename=" + launcher.getName());
        response.setContentLengthLong(launcher.length());

        InputStream in = new FileInputStream(launcher);
        FileCopyUtils.copy(in, response.getOutputStream());
    }
}
