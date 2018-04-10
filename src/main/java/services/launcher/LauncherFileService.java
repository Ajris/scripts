package services.launcher;

import creators.LauncherCreator;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Optional;

@Service
public class LauncherFileService {

    public File prepareLauncherFile(HttpServletRequest request) {
        Optional<String[]> scriptsToDownload = Optional.ofNullable(
                request.getParameterValues("script"));

        return new LauncherCreator()
                .buildLauncher(scriptsToDownload.orElse(new String[0]))
                .getLauncher();
    }
}
