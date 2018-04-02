package controllers;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static temporary.ValuesForController.DIRECTORY_PATH;

@RestController
public class ScriptsController {

    private static final String SHELL_SCRIPT = "application/x-sh";
    private static Logger logger = Logger.getLogger("InfoLogging");

    @RequestMapping(value = "/scripts/{scriptName}", method = RequestMethod.GET, produces = SHELL_SCRIPT)
    public void downloadScript(HttpServletResponse response, @PathVariable("scriptName") String scriptName) throws IOException {

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
