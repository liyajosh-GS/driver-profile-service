package com.ridemanagement.driverservice.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    public void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    public void testHandleItemNotFoundException() {
        NoSuchItemFoundException exception = new NoSuchItemFoundException("Item not found");

        ResponseEntity<Object> responseEntity = globalExceptionHandler.handleItemNotFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Item not found", responseEntity.getBody());
    }

    @Test
    public void testHandleRuntimeException() {
        RuntimeException exception = new RuntimeException("Runtime error");

        ResponseEntity<Object> responseEntity = globalExceptionHandler.handleRuntimeException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Runtime error", responseEntity.getBody());
    }
}
