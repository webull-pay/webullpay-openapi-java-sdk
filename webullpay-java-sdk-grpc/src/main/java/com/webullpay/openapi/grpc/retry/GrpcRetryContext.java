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
package com.webullpay.openapi.grpc.retry;

import com.webullpay.openapi.grpc.lifecycle.GrpcFailedContext;
import com.webullpay.openapi.grpc.lifecycle.GrpcFailedContextAdapter;
import com.webullpay.openapi.retry.RetryContext;

public class GrpcRetryContext extends GrpcFailedContextAdapter implements RetryContext, GrpcFailedContext {

    private final int attempts;
    private boolean throttled;

    public GrpcRetryContext(int attempts, Throwable cause) {
        super(cause);
        this.attempts = attempts;
    }

    @Override
    public int getRetriesAttempted() {
        return this.attempts;
    }

    @Override
    public boolean throttled() {
        return this.throttled;
    }

    @Override
    public void setThrottled(boolean throttled) {
        this.throttled = throttled;
    }
}
