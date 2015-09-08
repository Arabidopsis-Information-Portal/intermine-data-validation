package org.araport.validation.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.batch.repeat.RepeatContext;
import org.springframework.batch.repeat.exception.ExceptionHandler;

public class LocalExceptionHandler implements ExceptionHandler {

	private static final Logger log = LoggerFactory
			.getLogger(LocalExceptionHandler.class);
	
    @Override
    public void handleException(RepeatContext rc, Throwable throwable) throws Throwable {
        if (throwable instanceof FlatFileParseException) {
            FlatFileParseException fe = (FlatFileParseException)throwable;
            log.error("!!!! FlatFileParseException, line # is: " + fe.getLineNumber());
            log.error("!!!! FlatFileParseException, input is: " + fe.getInput());
        }
        log.error("!!!! Message : " + throwable.getMessage());
        log.error("!!!! Cause : " + throwable.getCause());      
    }
}
