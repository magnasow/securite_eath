package com.eath.Service.Implement;

import com.eath.Service.SpeechToTextService;
import com.eath.Service.VoiceService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class VoiceServiceImpl implements VoiceService {

    private final SpeechToTextService speechToTextService;

    public VoiceServiceImpl(SpeechToTextService speechToTextService) {
        this.speechToTextService = speechToTextService;
    }

    @Override
    public String convertVoiceToText(MultipartFile file) throws IOException {
        return speechToTextService.recognize(file.getBytes());
    }

    @Override
    public String executeCommand(String commandText) {
        // Exemple de logique de commande
        if (commandText.contains("allumer les lumières")) {
            return "Lumières allumées";
        }
        // Ajouter plus de logique de commande ici
        return "Commande inconnue";
    }
}
