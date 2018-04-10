package services.script;

import entity.Script;
import org.springframework.stereotype.Service;
import services.ScriptRepository;

import java.util.Optional;

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
