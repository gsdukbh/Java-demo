package top.werls.springbootvalidfromdemo;

import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author leejiawei
 */
@RestControllerAdvice
public class AppException {

    @ExceptionHandler
    public String validation(BindException e){
        return e.getMessage();
    }
}
