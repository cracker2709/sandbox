package sample.exception.advice;


import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Slf4j
@ControllerAdvice
class GlobalExceptionHandler extends ResponseEntityExceptionHandler {



    //StandardServletMultipartResolver
    @ExceptionHandler(MultipartException.class)
    @ResponseBody
    public ResponseEntity<Object> handleMultipartException(MultipartException e) {
        log.error("Erreur :", e);
        return new ResponseEntity<>("MultipartException", HttpStatus.BAD_REQUEST);

    }

    //CommonsMultipartResolver
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseBody
    public ResponseEntity<Object> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        log.error("Erreur :", e);
        return new ResponseEntity<>("MaxUploadSizeExceededException", HttpStatus.BAD_REQUEST);

    }


   /* @ExceptionHandler(FileTooBigException.class)
    @ResponseBody
    public ResponseEntity<Object> handleFileTooBigException(FileTooBigException e) {
        Operator operator = requestContext.getOperator();
        log.error("Erreur :", e);
        return new ResponseEntity<>("MaxUploadSizeExceededException", HttpStatus.BAD_REQUEST);

    }*/

    @Override
    @ResponseBody
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String name = e.getParameterName();
        log.error(name + " parameter is missing", e);

        return handleExceptionInternal(e, name + " parameter is missing", headers, status, request);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity<Object> handleRuntimeException(RuntimeException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("Erreur :", e);
        return handleExceptionInternal(e, "Runtime", headers, status, request);

    }

    @ExceptionHandler(value
            = { IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {
        log.error("Erreur :", ex);
        String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }






}
