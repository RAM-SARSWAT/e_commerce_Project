/*************************************************************************
 *
 * SATIN CREDITCARE NETWORK LIMITED CONFIDENTIAL
 * __________________
 *
 *  [2018] SATIN CREDITCARE NETWORK LIMITED
 *  All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains the property of SATIN CREDITCARE NETWORK LIMITED, and
 * The intellectual and technical concepts contained herein are proprietary to SATIN CREDITCARE NETWORK LIMITED
 * and may be covered by India and Foreign Patents, patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material is strictly forbidden unless prior written permission
 * is obtained from SATIN CREDITCARE NETWORK LIMITED.
 */

package com.ECA.Exception;

import com.ECA.DTO.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.validation.ConstraintViolationException;
import javax.net.ssl.SSLHandshakeException;
import java.io.EOFException;
import java.io.FileNotFoundException;

/**
 * @author shahzad.hussain
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private static final String ERROR_MSG = "Something went Wrong, Please try Later";

    @ExceptionHandler(value = FileNotFoundException.class)
    @ResponseBody
    public ResponseEntity<Object> handleFileNotFoundException(FileNotFoundException ex) {
        logger.info("FileNotFound Exception occurs => {}", ex);
        return new ResponseEntity<>(new Response("File does not exist", HttpStatus.NOT_FOUND), HttpStatus.OK);
    }

    @ExceptionHandler(value = BadRequestException.class)
    @ResponseBody
    public ResponseEntity<Object> handleBadRequestException(BadRequestException ex) {
        logger.info("BadRequestException occurs => {}", ex);
        return new ResponseEntity<>(new Response(ex.getMessage(), ex.getHttpStatus()), HttpStatus.OK);
    }

    @ExceptionHandler(value = ObjectNotFoundException.class)
    @ResponseBody
    public ResponseEntity<Object> handleObjectNotFoundException(ObjectNotFoundException ex) {
        logger.info("ObjectNotFoundException occurs => {}", ex);
        return new ResponseEntity<>(new Response(ex.getMessage(), ex.getHttpStatus()), HttpStatus.OK);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity<Object> handleException(Exception ex) {
        logger.info("Exception  occurs => {}", ex);
        return new ResponseEntity<>(new Response(ERROR_MSG, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public ResponseEntity<Object> handleFileNotFoundException(HttpRequestMethodNotSupportedException ex) {
        logger.info("HttpRequestMethodNotSupportedException occurs => {}", ex);
        return new ResponseEntity<>(new Response(ex.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseBody
    public ResponseEntity<Object> handleFileNotFoundException(MissingServletRequestParameterException ex) {
        logger.info("MissingServletRequestParameterException Exception occurs => {}", ex);
        return new ResponseEntity<>(new Response(ex.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = SSLHandshakeException.class)
    @ResponseBody
    public ResponseEntity<Response> handleSSLHandshakeException(SSLHandshakeException ex) {
        logger.info("SSLHandshake Exception occurs => {}", ex);
        return new ResponseEntity<>(new Response(ERROR_MSG, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = EOFException.class)
    @ResponseBody
    public ResponseEntity<Object> handleEOFException(EOFException ex) {
        logger.info("EOF Exception occurs => {}", ex);
        return new ResponseEntity<>(new Response(ERROR_MSG, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    public ResponseEntity<Object> handleEOFException(ConstraintViolationException ex) {
        logger.info("ConstraintViolationException occurs => {}", ex);
        return new ResponseEntity<>(new Response(ex.getConstraintViolations().iterator().next().getMessageTemplate(), HttpStatus.BAD_REQUEST), HttpStatus.OK);
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    @ResponseBody
    public ResponseEntity<Object> handleEOFException(DataIntegrityViolationException ex) {
        logger.info("DataIntegrityViolationException occurs => {}", ex);
        return new ResponseEntity<>(new Response(ex.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.OK);
    }

}