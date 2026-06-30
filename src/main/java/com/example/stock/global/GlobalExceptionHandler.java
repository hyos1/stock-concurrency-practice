package com.example.stock.global;

import com.example.stock.global.dto.ValidationError;
import com.example.stock.global.exception.ClientException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ApiResponse<Void>> clientExceptionHandler(ClientException e) {
        ApiResponse<Void> apiResponse = ApiResponse.fail(null, e.getErrorCode().getCode(), e.getMessage());
        return ResponseEntity.status(apiResponse.getCode()).body(apiResponse);
    }

    @ExceptionHandler
    public ResponseEntity<ApiResponse<List<ValidationError>>> validationExceptionHandler(MethodArgumentNotValidException e) {
//        log.warn("입력 DTO 검증 실패: ", e);
        List<ValidationError> errors = ValidationError.of(e.getBindingResult());
        ApiResponse<List<ValidationError>> apiResponse = ApiResponse.fail(errors, e.getStatusCode().value(), "입력값 유효성 검사에 실패했습니다.");

        return ResponseEntity.status(e.getStatusCode()).body(apiResponse);
    }
}