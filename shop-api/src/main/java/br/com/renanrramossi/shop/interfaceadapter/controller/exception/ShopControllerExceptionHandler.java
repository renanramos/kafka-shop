package br.com.renanrramossi.shop.interfaceadapter.controller.exception;

import br.com.renanrramossi.shop.interfaceadapter.controller.exception.model.ErrorResponse;
import br.com.renanrramossi.shop.interfaceadapter.controller.exception.model.ErrorResponse.ErrorResponseBuilder;
import br.com.renanrramossi.shop.interfaceadapter.controller.exception.model.FieldErrorValidation;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(basePackages = "br.com.renanrramossi.shop.interfaceadapter.controller")
public class ShopControllerExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(final MethodArgumentNotValidException exception) {
    log.error(exception.getMessage());

    final List<FieldErrorValidation> errorsList = exception.getFieldErrors()
        .stream()
        .map(this::buildFieldErrorValidation)
        .collect(Collectors.toList());

    return ResponseEntity
        .badRequest()
        .body(buildErrorResponse(HttpStatus.BAD_REQUEST, errorsList));
  }

  private FieldErrorValidation buildFieldErrorValidation(final FieldError error) {
    return FieldErrorValidation.builder()
        .fieldName(error.getField())
        .fieldValue(error.getRejectedValue())
        .message(error.getDefaultMessage())
        .build();
  }

  private ErrorResponse buildErrorResponse(final HttpStatus httpStatus, final List<FieldErrorValidation> errorsList) {
    return getErrorResponseBuilder(httpStatus)
        .validations(errorsList)
        .build();
  }

  private ErrorResponseBuilder getErrorResponseBuilder(HttpStatus httpStatus) {
    return ErrorResponse.builder()
        .status(httpStatus.value())
        .error(httpStatus.getReasonPhrase());
  }

}
