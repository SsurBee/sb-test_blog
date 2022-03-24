package sb.test.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice   // 모든 곳에서 exception 이 발생하면 아래 클래스를 타게 함.
@RestController
public class GlobalExceptionHandler {
//    IllegalArgumentException 만 걸림
//    @ExceptionHandler(value = IllegalArgumentException.class)
//    public String handleArgumentException(IllegalArgumentException e) {
//        return "<h1>" + e.getMessage() + "</h1>";
//    }

    @ExceptionHandler(value = Exception.class)
    public String handleArgumentException(Exception e) {
        return "<h1>" + e.getMessage() + "</h1>";
    }
}