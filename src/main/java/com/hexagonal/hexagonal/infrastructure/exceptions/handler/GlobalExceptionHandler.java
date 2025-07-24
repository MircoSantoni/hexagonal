package com.hexagonal.hexagonal.infrastructure.exceptions.handler;

// import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.web.ErrorResponse;
// import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hexagonal.hexagonal.application.ports.exceptions.ListDoesNotExistException;
import com.hexagonal.hexagonal.domain.model.exceptions.EmailAlreadyInUseException;
import com.hexagonal.hexagonal.domain.model.exceptions.NameConventionException;
import com.hexagonal.hexagonal.infrastructure.exceptions.BadRequestException;
import com.hexagonal.hexagonal.infrastructure.exceptions.EmptyResourceException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ListDoesNotExistException.class)
    public ResponseEntity<Map<String,String>> handleClientListDoesNotExistException(ListDoesNotExistException exception) {
        Map<String, String> response = new HashMap<>();

        response.put("Error: ", "Conflicto al buscar el cliente");
        response.put("Message: " , exception.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(EmailAlreadyInUseException.class)
    public ResponseEntity<Map<String,String>> handleEmailAlreadyInUseException(EmailAlreadyInUseException exception) {
        Map<String, String> response = new HashMap<>();

        response.put("Error: ", "Conflicto con el correo del cliente");
        response.put("Message: " , exception.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(NameConventionException.class)
    public ResponseEntity<Map<String,String>> handleNameConventionException(NameConventionException exception) {
        Map<String, String> response = new HashMap<>();

        response.put("Error: " , "Conflicto con los nombres de los clientes");
        response.put("Message: ", exception.getMessage());
        
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
    
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Map<String,String>> handBadRequestException(BadRequestException exception) {
        Map<String, String> response = new HashMap<>();

        response.put("Error: " , "Conflicto, solicitud denegada");
        response.put("Message: ", exception.getMessage());
        
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(EmptyResourceException.class)
    public ResponseEntity<Map<String,String>> handleEmptyResourceException(EmptyResourceException exception) {
        Map<String, String> response = new HashMap<>();

        response.put("Error: " , "Conflicto, recurso inexistente");
        response.put("Message: ", exception.getMessage());
        
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
    

    // TO DO terminar esto 
    // @ExceptionHandler(MethodArgumentNotValidException.class)
    // public ResponseEntity<ErrorDetail> handleValidationExceptions(MethodArgumentNotValidException ex) {
    //     List<String> errors = ex.getBindingResult()
    //             .getFieldErrors()
    //             .stream()
    //             .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
    //             .collect(Collectors.toList());

    //     String errorMessage = String.join(", ", errors);
    //     return buildErrorResponse(HttpStatus.BAD_REQUEST, errorMessage);
    // }

    // private ResponseEntity<ErrorDetail> buildErrorResponse(HttpStatus status, String message) {
    //     ErrorResponse errorResponse = new ErrorResponse(status.value(), message);
    //     return new ResponseEntity<>(errorResponse, status);
    // }

}