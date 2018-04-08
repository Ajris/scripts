package services;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import static temporary.ValuesForController.DIRECTORY_PATH;

@Service
public class ScriptService {

    private static final String SHELL_SCRIPT = "application/x-sh";
    private static Logger logger = Logger.getLogger("InfoLogging");

    public void downloadScript(HttpServletResponse response, String scriptName) throws IOException {
        File file = getFile(scriptName);

        response.setContentType(SHELL_SCRIPT);
        response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
        response.setContentLengthLong(file.length());

        try(InputStream in = new FileInputStream(file)){
            FileCopyUtils.copy(in, response.getOutputStream());
        }catch(IOException e){
            logger.log(Level.ALL, e.toString());
        }

    }


    private File getFile(String name) throws FileNotFoundException {

        File file = new File(DIRECTORY_PATH + name);

        if (!file.exists()) {
            throw new FileNotFoundException("file with path: " + DIRECTORY_PATH + name + " was not found.");
        }

        return file;
    }
}
