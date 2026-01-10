package com.example.demo.exceptions;

import java.time.LocalDate;

import io.micrometer.common.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class WorklineErrorResponse {
    private String message;
    private LocalDate date;
    @Nullable
    private Object invalidRequest;
}
