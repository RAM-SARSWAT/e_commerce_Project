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
package com.ECA.DTO;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class Response {
    private HttpStatus status;
    private int code;
    private String message = "Please try Later";
    private Object responseObject;

    public HttpStatus getStatus() {
        return status;
    }

    public Response() {
    }

    public Response(String message, Object responseObject, HttpStatus status) {
        this.message = message;
        this.status = status;
        this.code = status.value();
        this.responseObject = responseObject;
    }

    public Response(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
        this.code = status.value();
    }

    public Response(String message, HttpStatus status, HttpStatus code) {
        this.message = message;
        this.status = status;
        this.code = code.value();
    }
}
