package com.typeb.assessment.service;

import com.typeb.assessment.dto.HelloResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class HelloService {
    private static final char FIRST_HALF_END = 'M';

    public boolean isValidName(String name) {
        if (name == null || name.isBlank()) return false;
        char first = Character.toUpperCase(name.trim().charAt(0));
        return Character.isLetter(first) && first <= FIRST_HALF_END;
    }

    public HelloResponseDTO greet(String name) {
        String trimmed = name.trim();
        String formatted = Character.toUpperCase(trimmed.charAt(0)) + trimmed.substring(1);
        return new HelloResponseDTO("Hello " + formatted);
    }
}
