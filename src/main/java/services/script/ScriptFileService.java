package services.script;

import entity.Script;
import exception.DataNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScriptFileService {

    private final ScriptRepository scriptRepository;

    public ScriptFileService(ScriptRepository scriptRepository) {
        this.scriptRepository = scriptRepository;
    }

    public Script getScript(String name) {
        Optional<Script> script = scriptRepository.findByTitle(name);

        return script.orElseThrow(DataNotFoundException::new);
    }

    public Script createScript(String title, String text, String description) {
        return Script.builder()
        .title(title)
        .text(text)
        .description(description)
        .build();
    }
}
