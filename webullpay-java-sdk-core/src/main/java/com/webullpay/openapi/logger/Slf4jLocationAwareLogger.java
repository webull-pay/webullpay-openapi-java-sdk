/*
 * Copyright 2025 Webullpay
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.webullpay.openapi.logger;

import org.slf4j.spi.LocationAwareLogger;

final class Slf4jLocationAwareLogger implements Logger {

    private static final String FQCN = Slf4jLocationAwareLogger.class.getName();

    private final LocationAwareLogger logger;

    Slf4jLocationAwareLogger(LocationAwareLogger logger) {
        this.logger = logger;
    }

    @Override
    public String name() {
        return logger.getName();
    }

    @Override
    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    @Override
    public void trace(String msg) {
        if (isTraceEnabled()) {
            log(LocationAwareLogger.TRACE_INT, msg);
        }
    }

    @Override
    public void trace(String format, Object arg) {
        if (isTraceEnabled()) {
            log(LocationAwareLogger.TRACE_INT, org.slf4j.helpers.MessageFormatter.format(format, arg));
        }
    }

    @Override
    public void trace(String format, Object... args) {
        if (isTraceEnabled()) {
            log(LocationAwareLogger.TRACE_INT, org.slf4j.helpers.MessageFormatter.arrayFormat(format, args));
        }
    }

    @Override
    public void trace(String msg, Throwable t) {
        if (isTraceEnabled()) {
            log(LocationAwareLogger.TRACE_INT, msg, t);
        }
    }

    @Override
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    @Override
    public void debug(String msg) {
        if (isDebugEnabled()) {
            log(LocationAwareLogger.DEBUG_INT, msg);
        }
    }

    @Override
    public void debug(String format, Object arg) {
        if (isDebugEnabled()) {
            log(LocationAwareLogger.DEBUG_INT, org.slf4j.helpers.MessageFormatter.format(format, arg));
        }
    }

    @Override
    public void debug(String format, Object... args) {
        if (isDebugEnabled()) {
            log(LocationAwareLogger.DEBUG_INT, org.slf4j.helpers.MessageFormatter.arrayFormat(format, args));
        }
    }

    @Override
    public void debug(String msg, Throwable t) {
        if (isDebugEnabled()) {
            log(LocationAwareLogger.DEBUG_INT, msg, t);
        }
    }

    @Override
    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    @Override
    public void info(String msg) {
        if (isInfoEnabled()) {
            log(LocationAwareLogger.INFO_INT, msg);
        }
    }

    @Override
    public void info(String format, Object arg) {
        if (isInfoEnabled()) {
            log(LocationAwareLogger.INFO_INT, org.slf4j.helpers.MessageFormatter.format(format, arg));
        }
    }

    @Override
    public void info(String format, Object... args) {
        if (isInfoEnabled()) {
            log(LocationAwareLogger.INFO_INT, org.slf4j.helpers.MessageFormatter.arrayFormat(format, args));
        }
    }

    @Override
    public void info(String msg, Throwable t) {
        if (isInfoEnabled()) {
            log(LocationAwareLogger.INFO_INT, msg, t);
        }
    }

    @Override
    public boolean isWarnEnabled() {
        return logger.isWarnEnabled();
    }

    @Override
    public void warn(String msg) {
        if (isWarnEnabled()) {
            log(LocationAwareLogger.WARN_INT, msg);
        }
    }

    @Override
    public void warn(String format, Object arg) {
        if (isWarnEnabled()) {
            log(LocationAwareLogger.WARN_INT, org.slf4j.helpers.MessageFormatter.format(format, arg));
        }
    }

    @Override
    public void warn(String format, Object... args) {
        if (isWarnEnabled()) {
            log(LocationAwareLogger.WARN_INT, org.slf4j.helpers.MessageFormatter.arrayFormat(format, args));
        }
    }

    @Override
    public void warn(String msg, Throwable t) {
        if (isWarnEnabled()) {
            log(LocationAwareLogger.WARN_INT, msg, t);
        }
    }

    @Override
    public boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }

    @Override
    public void error(String msg) {
        if (isErrorEnabled()) {
            log(LocationAwareLogger.ERROR_INT, msg);
        }
    }

    @Override
    public void error(String format, Object arg) {
        if (isErrorEnabled()) {
            log(LocationAwareLogger.ERROR_INT, org.slf4j.helpers.MessageFormatter.format(format, arg));
        }
    }

    @Override
    public void error(String format, Object... args) {
        if (isErrorEnabled()) {
            log(LocationAwareLogger.ERROR_INT, org.slf4j.helpers.MessageFormatter.arrayFormat(format, args));
        }
    }

    @Override
    public void error(String msg, Throwable t) {
        if (isErrorEnabled()) {
            log(LocationAwareLogger.ERROR_INT, msg, t);
        }
    }

    private void log(final int level, final String message) {
        logger.log(null, FQCN, level, message, null, null);
    }

    private void log(final int level, final String message, Throwable cause) {
        logger.log(null, FQCN, level, message, null, cause);
    }

    private void log(final int level, final org.slf4j.helpers.FormattingTuple tuple) {
        logger.log(null, FQCN, level, tuple.getMessage(), tuple.getArgArray(), tuple.getThrowable());
    }
}
