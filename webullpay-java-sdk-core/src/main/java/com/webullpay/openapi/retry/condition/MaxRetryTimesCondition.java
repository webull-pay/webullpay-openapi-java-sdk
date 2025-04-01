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
package com.webullpay.openapi.retry.condition;

import com.webullpay.openapi.logger.Logger;
import com.webullpay.openapi.logger.LoggerFactory;
import com.webullpay.openapi.retry.RetryContext;

public class MaxRetryTimesCondition implements RetryCondition {

    private static final Logger logger = LoggerFactory.getLogger(MaxRetryTimesCondition.class);

    private final int maxRetries;

    public MaxRetryTimesCondition(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    @Override
    public boolean shouldRetry(RetryContext context) {
        if (context.getRetriesAttempted() < this.maxRetries) {
            return true;
        }
        logger.debug("Reached the maximum number of retry, attempts:{}", context.getRetriesAttempted());
        return false;
    }
}
