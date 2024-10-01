package com.eath.web;

import com.eath.Service.VoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@CrossOrigin
@RestController
@RequestMapping("/api/v1/voice")
public class VoiceController {

    private final VoiceService voiceService;

    public VoiceController(VoiceService voiceService) {
        this.voiceService = voiceService;
    }

    @PostMapping("/process")
    public ResponseEntity<String> processVoiceCommand(@RequestParam("file") MultipartFile file) {
        try {
            String commandText = voiceService.convertVoiceToText(file);
            String result = voiceService.executeCommand(commandText);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process voice command: " + e.getMessage());
        }
    }
}

