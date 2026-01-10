package com.example.demo.exceptions.controllers;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.exceptions.WorklineErrorResponse;
import com.example.demo.exceptions.ofertaExceptions.NoOfertaResultsFoundException;
import com.example.demo.exceptions.ofertaExceptions.OfertaIdNotFoundException;
import com.example.demo.exceptions.ofertaExceptions.OfertaPageIndexException;

@RestControllerAdvice
public class OfertasExceptionController {
    
    @ExceptionHandler({
        OfertaPageIndexException.class,
        NoOfertaResultsFoundException.class,
        OfertaIdNotFoundException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<WorklineErrorResponse> handleOfertasBusquedaExceptions(RuntimeException ex) {
        WorklineErrorResponse finalException = WorklineErrorResponse.builder()
            .date(LocalDate.now())
            .message(ex.getMessage())
            .invalidRequest(ex)
            .build();
        return ResponseEntity.status(404).body(finalException);
    }
}
