package per.budictreas.springmvc.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import per.budictreas.springmvc.data.responsemodel.CommonResponseModel;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", status.value());

        Map<String, String> errors = new HashMap<>();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        for (FieldError x : fieldErrors) errors.put(x.getField(), x.getDefaultMessage());

        body.put("errors", errors);

        return new ResponseEntity<>(body, headers, status);
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<CommonResponseModel> handleEntityNotFound() {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

//    [LƯU Ý]: vì ResponseEntityExceptionHandler chỉ là một class do Spring cung cấp
//    Chúng ta có thể extends hoặc không tùy ý. Nếu không extends code sẽ thay đổi như sau đây
//    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
//        HttpStatus status = HttpStatus.BAD_REQUEST;
//        Map<String, Object> body = new HashMap<>();
//        body.put("timestamp", LocalDateTime.now().toString());
//        body.put("status", status);
//
//        Map<String, String> errors = new HashMap<>();
//        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
//        for (FieldError x : fieldErrors) errors.put(x.getField(), x.getDefaultMessage());
//
//        body.put("errors", errors);
//
//        return new ResponseEntity<>(body, status);
//    }
