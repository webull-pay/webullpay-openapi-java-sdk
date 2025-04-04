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
package com.webullpay.openapi.trade.events.subscribe;

import com.webullpay.openapi.trade.events.subscribe.message.SubscribeResponse;
import com.webullpay.openapi.grpc.lifecycle.GrpcHandler;
import com.webullpay.openapi.grpc.retry.GrpcRetryCondition;
import com.webullpay.openapi.retry.RetryPolicy;
import com.webullpay.openapi.retry.backoff.BackoffStrategy;

import java.util.function.Consumer;

public interface EventClientBuilder {

    EventClientBuilder appKey(String appKey);

    EventClientBuilder appSecret(String appSecret);

    EventClientBuilder host(String host);

    EventClientBuilder port(int port);

    EventClientBuilder regionId(String regionId);

    EventClientBuilder enableTls(boolean enableTls);

    default EventClientBuilder reconnectBy(BackoffStrategy backoffStrategy) {
        return this.reconnectBy(new RetryPolicy(GrpcRetryCondition.getInstance(), backoffStrategy));
    }

    EventClientBuilder reconnectBy(RetryPolicy retryPolicy);

    EventClientBuilder addHandler(GrpcHandler handler);

    EventClientBuilder onMessage(Consumer<SubscribeResponse> consumer);

    EventClient build();
}
