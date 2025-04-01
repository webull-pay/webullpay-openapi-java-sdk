package com.webullpay.openapi.example.log;

import com.webullpay.openapi.logger.Logger;
import com.webullpay.openapi.logger.LoggerFactory;

public class LoggerTest {

    static {
        LoggerFactory.setDefault(new ExampleLoggerFactory());
    }

    private static final Logger logger = LoggerFactory.getLogger(LoggerTest.class);

    public static void main(String[] args) {
        logger.error("Print error log...");
        logger.warn("Print warn log...");
        logger.info("Print info log...");
        logger.debug("Print debug log...");
        logger.trace("Print trace log...");
    }
}
