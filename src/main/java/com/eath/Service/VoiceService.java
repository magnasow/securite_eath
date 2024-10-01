package com.eath.Service;


import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public interface VoiceService {
    String convertVoiceToText(MultipartFile file) throws IOException;
    String executeCommand(String commandText);
}
