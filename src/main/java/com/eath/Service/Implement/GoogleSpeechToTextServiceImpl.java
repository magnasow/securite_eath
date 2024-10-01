package com.eath.Service.Implement;

import com.eath.Service.SpeechToTextService;
import com.google.cloud.speech.v1.*;
import com.google.protobuf.ByteString;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.stream.Collectors;

@Service
public class GoogleSpeechToTextServiceImpl implements SpeechToTextService {

    @Override
    public String recognize(byte[] audioData) throws IOException {
        try (SpeechClient speechClient = SpeechClient.create()) {
            RecognitionConfig config = RecognitionConfig.newBuilder()
                    .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
                    .setSampleRateHertz(16000)
                    .setLanguageCode("fr-FR") // Utiliser le code de langue approprié
                    .build();
            RecognitionAudio audio = RecognitionAudio.newBuilder()
                    .setContent(ByteString.copyFrom(audioData))
                    .build();

            RecognizeResponse response = speechClient.recognize(config, audio);
            return response.getResultsList().stream()
                    .map(result -> result.getAlternativesList().get(0).getTranscript())
                    .collect(Collectors.joining("\n"));
        }
    }
}
