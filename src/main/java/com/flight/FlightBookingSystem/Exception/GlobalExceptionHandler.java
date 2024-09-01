package com.flight.FlightBookingSystem.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
@ControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(FlightNotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public ResponseEntity<ErrorResponse> handleFlightNotFoundException(FlightNotFoundException ex) {
            ErrorResponse errorResponse = new ErrorResponse("NOT_FOUND", ex.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    @ExceptionHandler(DuplicateFlightException.class)
    public ResponseEntity<String> handleDuplicateFlightException(DuplicateFlightException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT); // 409 Conflict
    }
        @ExceptionHandler(PassengerNotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public ResponseEntity<ErrorResponse> handlePassengerNotFoundException(PassengerNotFoundException ex) {
            ErrorResponse errorResponse = new ErrorResponse("NOT_FOUND", ex.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(BookingNotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public ResponseEntity<ErrorResponse> handleBookingNotFoundException(BookingNotFoundException ex) {
            ErrorResponse errorResponse = new ErrorResponse("NOT_FOUND", ex.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(Exception.class)
        @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
            ErrorResponse errorResponse = new ErrorResponse("INTERNAL_SERVER_ERROR", "An unexpected error occurred");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        public static class ErrorResponse {
            private String errorCode;
            private String message;

            public ErrorResponse(String errorCode, String message) {
                this.errorCode = errorCode;
                this.message = message;
            }

            // Getters and Setters
            public String getErrorCode() {
                return errorCode;
            }

            public void setErrorCode(String errorCode) {
                this.errorCode = errorCode;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }
        }
    }
