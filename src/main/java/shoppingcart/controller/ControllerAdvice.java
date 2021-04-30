package shoppingcart.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.beanvalidation.MethodValidationInterceptor;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import shoppingcart.dao.InvalidCustomerNameException;
import shoppingcart.exception.InvalidProductException;
import shoppingcart.exception.NotInCustomerCartItemException;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotEmpty;

@RestControllerAdvice
public class ControllerAdvice {

    // 잘못된 타입 준 경우
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handle(final HttpMessageNotReadableException e) {
        return new ResponseEntity<>(
                e.getMessage(),
                HttpStatus.BAD_REQUEST);
    }

    // DTO 필드 하나만 빠져도
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handle(final MethodArgumentNotValidException e){
        return new ResponseEntity<>(
                e.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }

    // customerName이 잘못들어온 경우 (공통)
    @ExceptionHandler(InvalidCustomerNameException.class)
    public ResponseEntity<String> handle(final InvalidCustomerNameException e) {
        return new ResponseEntity<>(
                e.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }

    // 없는 productId가 들어온 경우
    @ExceptionHandler(InvalidProductException.class)
    public ResponseEntity<String> handle(final InvalidProductException e) {
        return new ResponseEntity<>(
                e.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }

    // 유저의 카트아이디가 아닌경우, 존재하지 않는 카트 아이디인 경우
    @ExceptionHandler(NotInCustomerCartItemException.class)
    public ResponseEntity<String> handle(final NotInCustomerCartItemException e) {
        return new ResponseEntity<>(
                e.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }

    // list에서 quantity가 0 이상인 경우
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handle(final ConstraintViolationException e) {
        return new ResponseEntity<>(
                e.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }
}
