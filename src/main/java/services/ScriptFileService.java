package services;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;

import static temporary.ValuesForController.DIRECTORY_PATH;

@Service
public class ScriptFileService {

    public File getFile(String name) throws FileNotFoundException {

        File file = new File(DIRECTORY_PATH + name);

        if (!file.exists()) {
            throw new FileNotFoundException("file with path: " + DIRECTORY_PATH + name + " was not found.");
        }

        return file;
    }
}
