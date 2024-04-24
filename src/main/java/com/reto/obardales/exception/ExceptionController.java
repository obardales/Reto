package com.reto.obardales.exception;

import com.reto.obardales.commons.Cte;
import com.reto.obardales.commons.MessageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice("com.nisum.obardales")
public class ExceptionController {

    private Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    private final MessageHelper messageHelper;

    public ExceptionController(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ExceptionResponse> application(Exception ex) {
        logger.error(ex.getMessage(), ex);
        return new ResponseEntity<>(
                new ExceptionResponse(
                        messageHelper.get(Cte.Message.APP_RESPONSE_EX_MESSAGE)),
                HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(BussinesException.class)
    protected ResponseEntity<ExceptionResponse> application(BussinesException ex) {
        return new ResponseEntity<>(
                new ExceptionResponse(ex.getFacingMessage()),
                HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(ApplicationValidationException.class)
    protected ResponseEntity<ExceptionResponse> validation(ApplicationValidationException ex) {
        return new ResponseEntity<>(
                new ExceptionResponse(
                        ex.getMessage(),
                        ex.errors()),
                HttpStatus.BAD_REQUEST);
    }

}