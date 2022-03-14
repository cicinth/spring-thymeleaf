package com.aula04.banco.banco.config;

import com.aula04.banco.banco.exceptions.ErroValidacao;
import com.aula04.banco.banco.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestControllerAdvice
public class ErrorHandle {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroValidacao> lidarErroValidacao(MethodArgumentNotValidException exception){
        List<ErroValidacao> erros = new ArrayList<>();
        List<FieldError> camposErro = exception.getBindingResult().getFieldErrors();
        camposErro.forEach(e ->{
            String mensagem =  messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErroValidacao erro = new ErroValidacao(e.getField(), mensagem);
            erros.add(erro);
        });
        return  erros;
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public String lidarNaoEncontrado(NotFoundException exception){
        return exception.getMessage();
    }
}
