package com.webullpay.openapi.example.log;

import com.webullpay.openapi.logger.Logger;
import com.webullpay.openapi.logger.LoggerFactory;

public class ExampleLoggerFactory extends LoggerFactory {

    @Override
    protected Logger newLogger(String name) {
        return new ExampleLogger(name);
    }
}
