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

import org.springframework.http.HttpStatus;

/**
 * @author shahzad.hussain
 */
public class BadRequestException extends Exception {

    private static final long serialVersionUID = -282913843145658333L;

    private int code;

    private HttpStatus httpStatus;

    private Object responseObject;

    public BadRequestException(String exceptionMessage) {
        super(exceptionMessage);
    }

    public BadRequestException(String exceptionMessage, HttpStatus httpStatus) {
        super(exceptionMessage);
        this.code = httpStatus.value();
        this.httpStatus = httpStatus;
    }

    public BadRequestException(String exceptionMessage, Object responseObject, HttpStatus httpStatus) {
        super(exceptionMessage);
        this.code = httpStatus.value();
        this.httpStatus = httpStatus;
        this.responseObject = responseObject;
    }

    public int getCode() {
        return code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public Object getResponseObject() {
        return responseObject;
    }

}
