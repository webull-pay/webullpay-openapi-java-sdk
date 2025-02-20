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
package com.webullpay.openapi.quotes.subsribe.codec;

import com.webullpay.openapi.logger.Logger;
import com.webullpay.openapi.logger.LoggerFactory;
import com.webullpay.openapi.quotes.subsribe.lifecycle.QuotesSubsInboundHandler;

public abstract class AbstractInboundDecoder<I, O> implements QuotesSubsInboundHandler, Decoder<I, O> {

    private static final Logger logger = LoggerFactory.getLogger(AbstractInboundDecoder.class);

    @Override
    @SuppressWarnings("unchecked")
    public Object onMessage(Object message) {
        try {
            return this.decode((I) message);
        } catch (Exception e) {
            logger.error("Decode error, msg={}.", message, e);
            return null;
        }
    }
}
