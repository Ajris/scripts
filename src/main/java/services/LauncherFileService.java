package services;

import creators.LauncherCreator;
import org.springframework.stereotype.Service;
import temporary.ValuesForController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Optional;

@Service
public class LauncherFileService {

    public File prepareFile(HttpServletRequest request) {
        Optional<String[]> scriptsToDownload = Optional.ofNullable(request.getParameterValues(ValuesForController.NAMEINCHECKBOX.toString()));

        return new LauncherCreator()
                .buildLauncher(scriptsToDownload.orElse(new String[0]))
                .getLauncher();
    }
}
