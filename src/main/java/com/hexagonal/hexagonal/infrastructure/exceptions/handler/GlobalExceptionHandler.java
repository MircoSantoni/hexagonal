package com.hexagonal.hexagonal.infrastructure.exceptions.handler;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hexagonal.hexagonal.infrastructure.exceptions.*;

import java.net.URI;
import java.time.Instant;

// /*
// * Implementacion segun: https://www.rfc-editor.org/rfc/rfc9457.html#name-members-of-a-problem-detail
// * */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @Value("${problem-detail.name-exception}")
    private String nameException;

    @Value("${problem-detail.name-timestamp}")
    private String nameTimestamp;

    @ExceptionHandler({BusinessException.class, ApiException.class})
    public ResponseEntity<ProblemDetail> handleCustomExceptions(Exception e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String detail = null;
        String exception = e.getClass().getSimpleName();

        if (e instanceof BusinessException be) {
            status = be.getStatus();
            detail = be.getDetail();
        } else if (e instanceof ApiException se) {
            status = se.getStatus();
            detail = se.getDetail();
        }

        var problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setTitle(e.getMessage());
        problemDetail.setDetail(detail);
        problemDetail.setProperty(nameException, exception);
        problemDetail.setProperty(nameTimestamp, Instant.now());
//        problemDetail.setType(URI.create("https://midominio.com/docs/errors/" + code));
        problemDetail.setInstance(URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString()));
        logsDetailException(e, status);
        return new ResponseEntity<>(problemDetail, status);
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ProblemDetail> handleDomainExceptions(DomainException e) {
        HttpStatus status = mapDomainExceptionToHttpStatus(e);
        String exception = e.getClass().getSimpleName();

        var problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setTitle(e.getMessage());
        problemDetail.setDetail(e.getDetail());
        problemDetail.setProperty(nameException, exception);
        problemDetail.setProperty(nameTimestamp, Instant.now());
//        problemDetail.setType(URI.create("https://midominio.com/docs/errors/" + code));
        problemDetail.setInstance(URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString()));
        logsDetailException(e, status);
        return new ResponseEntity<>(problemDetail, status);
    }

    private HttpStatus mapDomainExceptionToHttpStatus(DomainException e) {
        return switch (e) {
            case ListDoesNotExistException ignored -> HttpStatus.CONFLICT;
            case EmailAlreadyInUseException ignored -> HttpStatus.UNPROCESSABLE_ENTITY;
            case BadRequestException ignored -> HttpStatus.UNPROCESSABLE_ENTITY;
            case EmptyResourceException ignored -> HttpStatus.UNPROCESSABLE_ENTITY;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleGeneric(Exception e) {
        var problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problemDetail.setTitle("Error interno del servidor");
        problemDetail.setDetail(e.getMessage());
//        problemDetail.setType(URI.create("https://midominio.com/docs/errors/INTERNAL_ERROR"));
        problemDetail.setProperty(nameException, "INTERNAL_ERROR");
        problemDetail.setProperty(nameTimestamp, Instant.now());
        problemDetail.setInstance(URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString()));

        logsDetailException(e, HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(problemDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    void logsDetailException(Exception e, HttpStatus status) {
        log.error("ERROR - Http Status: " + status + ", Message: " + e.getMessage());
    }
}

// // // // // // // // // ListDoesNotExistException
// // // // // // // // // EmailAlreadyInUseException
// // // // // // // // // BadRequestException
// // // // // // // // // EmptyResourceException