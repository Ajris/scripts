package services.launcher;

import creators.LauncherCreator;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

@Service
public class LauncherService {

    public ResponseEntity<InputStreamResource> downloadLauncher(String[] scriptNames){
        String launcherText = new LauncherCreator(scriptNames).getText().toString();

        return ResponseEntity
                .ok()
                .contentLength(launcherText.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(new ByteArrayInputStream(launcherText.getBytes())));
    }
}
