package services.script;

import entity.Script;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScriptFileService {

    private ScriptRepository scriptRepository;

    public ScriptFileService(ScriptRepository scriptRepository) {
        this.scriptRepository = scriptRepository;
    }

    public Script getScript(String name) {
        Optional<Script> script = scriptRepository.findByTitle(name);
        return script.orElseThrow(NullPointerException::new);
    }

    public Script createScript(String title, String text) {
        return new Script(title, text);
    }
}
