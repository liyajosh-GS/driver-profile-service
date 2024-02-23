package com.ridemanagement.driverservice.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NoSuchItemFoundExceptionTest {

    @Test
    public void testNoSuchItemFoundException() {
        NoSuchItemFoundException exception = new NoSuchItemFoundException("Item not found");

        assertEquals("Item not found", exception.getMessage());
    }
}
