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

public class JdkLoggerFactory extends LoggerFactory {

    static LoggerFactory getFactoryInstance() {
        return JdkLoggerFactory.InstanceHolder.INSTANCE;
    }

    private static final class InstanceHolder {
        private static final LoggerFactory INSTANCE = new JdkLoggerFactory();
    }

    private JdkLoggerFactory() {
    }

    @Override
    public Logger newLogger(String name) {
        return new JdkLogger(java.util.logging.Logger.getLogger(name));
    }
}