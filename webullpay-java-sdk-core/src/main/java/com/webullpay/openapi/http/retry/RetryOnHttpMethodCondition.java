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
package com.webullpay.openapi.http.retry;

import com.webullpay.openapi.retry.condition.RetryCondition;
import com.webullpay.openapi.execption.ClientException;
import com.webullpay.openapi.execption.ServerException;
import com.webullpay.openapi.http.common.HttpMethod;
import com.webullpay.openapi.retry.RetryContext;

public class RetryOnHttpMethodCondition implements RetryCondition {

    @Override
    public boolean shouldRetry(RetryContext context) {
        if (!(context instanceof HttpRetryContext)) {
            throw new IllegalArgumentException("Retry context[" + context.getClass().getName() + "] is inappropriate to http retry condition!");
        }
        HttpRetryContext httpCtx = (HttpRetryContext) context;
        Throwable cause = httpCtx.getCause();
        if (cause instanceof ClientException || cause instanceof ServerException) {
            return HttpMethod.GET == httpCtx.getMethod();
        }
        return false;
    }
}
