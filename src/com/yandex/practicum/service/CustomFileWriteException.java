package com.yandex.practicum.service;

import java.io.IOException;

public class CustomFileWriteException extends RuntimeException {
    public CustomFileWriteException(String message, IOException e) {
        super(message);
    }
}
