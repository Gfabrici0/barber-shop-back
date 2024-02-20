package com.br.barbershop.config;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<?> handleEntityNotFound() {
    return ResponseEntity.notFound().build();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex) {
    List<DataErrorValidation> erros = ex.getFieldErrors().stream()
        .map(DataErrorValidation::new)
        .toList();
    return ResponseEntity.badRequest().body(erros);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleInternalServerError(Exception ex, WebRequest request) {

    ApiError apiError = new ApiError(
        HttpStatus.INTERNAL_SERVER_ERROR,
        "An unexpected internal server error occurred",
        ex.getMessage());

    return ResponseEntity
      .status(HttpStatus.INTERNAL_SERVER_ERROR)
      .body(apiError);
  }

  static class ApiError {
    private HttpStatus status;
    private String message;
    private String debugMessage;

    public ApiError(HttpStatus status, String message, String debugMessage) {
      this.status = status;
      this.message = message;
      this.debugMessage = debugMessage;
    }
  }

  private record DataErrorValidation(String campo, String mensagem) {
    public DataErrorValidation(FieldError erro) {
      this(erro.getField(), erro.getDefaultMessage());
    }
  }

}
