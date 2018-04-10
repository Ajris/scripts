package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import services.script.WgetFromLauncherService;

import java.io.IOException;

@RestController
public class WgetFromLauncherController {

//    private WgetFromLauncherService wgetFromLauncherService;
//
//    @Autowired
//    public WgetFromLauncherController(WgetFromLauncherService wgetFromLauncherService) {
//        this.wgetFromLauncherService = wgetFromLauncherService;
//    }
//
//    @GetMapping(value = "/scripts/{scriptName}", produces = "application/json")
//    public ResponseEntity<InputStreamResource> downloadScript(@PathVariable("scriptName") String scriptName) throws IOException {
//        return wgetFromLauncherService.downloadScript(scriptName);
//    }
}
