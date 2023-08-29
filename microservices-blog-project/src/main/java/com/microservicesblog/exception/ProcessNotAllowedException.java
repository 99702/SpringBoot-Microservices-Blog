package com.microservicesblog.exception;

/**
 * @author Rajan Paudel <rajan99702@proton.me>
 */
public class ProcessNotAllowedException extends RuntimeException{

    public ProcessNotAllowedException(String exception){
        super(exception);
    }
}
