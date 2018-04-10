package services.script;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Script;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;
import services.ScriptRepository;

import javax.validation.constraints.Null;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;

import static temporary.ValuesForController.DIRECTORY_PATH;

@Service
public class ScriptFileService {

    private ScriptRepository scriptRepository;

    public ScriptFileService(ScriptRepository scriptRepository) {
        this.scriptRepository = scriptRepository;
    }

    public String getFile(String name) {
        Optional<Script> script = scriptRepository.findByTitle(name);
        return script.get().getText();
    }
}
