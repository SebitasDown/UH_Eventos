package com.UH.OtherLevel.exceptions;

import com.UH.OtherLevel.dto.error.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Error para
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDTO> handleIllegalArgumentException(IllegalArgumentException ex){
        ErrorDTO error = new ErrorDTO("ERROR", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorDTO> handleBusinessException(BusinessException ex){
        ErrorDTO error = new ErrorDTO(ex.getCode(), ex.getMessage());
        // Interesante
        HttpStatus status;
        switch (ex.getCode()) {
            case "BAD_REQUEST":
                status = HttpStatus.BAD_REQUEST;
                break;
            case "CONFLICT":
                status = HttpStatus.CONFLICT;
                break;
            case "NOT_FOUND":
                status = HttpStatus.NOT_FOUND;
                break;
            default:
                status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity.status(status).body(error);
    }

    // Error para
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorDTO> handleIllegalStateException(IllegalStateException ex){
        ErrorDTO error = new ErrorDTO("ERROR", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // Error para
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleOtherExceptions(Exception ex) {
        ErrorDTO error = new ErrorDTO("ERROR", "Ocurrio un error inesperado");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
