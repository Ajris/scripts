package services;

import entity.Script;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

@Service
public class DownloadFileService {

    public ResponseEntity<InputStreamResource> downloadScript(Script script) {

        String text = script.getText();

        return ResponseEntity
                .ok()
                .contentLength(text.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(new ByteArrayInputStream(text.getBytes())));
    }
}
