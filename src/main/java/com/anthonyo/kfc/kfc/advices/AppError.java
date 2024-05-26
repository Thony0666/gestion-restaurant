package com.anthonyo.kfc.kfc.advices;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AppError {
    private String message;
    private LocalDateTime dateTime;
    private int status;
}
