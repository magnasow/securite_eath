package com.eath.Service;


import java.io.IOException;

public interface SpeechToTextService {
    String recognize(byte[] audioData) throws IOException;
}

