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
package com.webullpay.openapi.quotes.internal.grpc;

import com.webullpay.openapi.common.ApiModule;
import com.webullpay.openapi.endpoint.EndpointResolver;
import com.webullpay.openapi.execption.ClientException;
import com.webullpay.openapi.execption.ErrorCode;
import com.webullpay.openapi.grpc.lifecycle.GrpcHandler;
import com.webullpay.openapi.grpc.retry.GrpcRetryCondition;
import com.webullpay.openapi.quotes.api.QuotesApiClient;
import com.webullpay.openapi.quotes.api.QuotesApiClientBuilder;
import com.webullpay.openapi.quotes.api.lifecycle.ApiDowngradeHandler;
import com.webullpay.openapi.quotes.internal.grpc.lifecycle.ApiDowngradeLoggingHandler;
import com.webullpay.openapi.retry.RetryPolicy;
import com.webullpay.openapi.retry.backoff.FixedDelayStrategy;
import com.webullpay.openapi.utils.Assert;
import com.webullpay.openapi.utils.StringUtils;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class GrpcQuotesApiClientBuilder implements QuotesApiClientBuilder {

    private String appKey;
    private String appSecret;
    private String regionId;
    private String host;
    private int port = 443;
    private long connectTimeoutMillis = 10000;
    private long readTimeoutMillis = 10000;
    private RetryPolicy retryPolicy =
            new RetryPolicy(GrpcRetryCondition.getInstance(), new FixedDelayStrategy(5, TimeUnit.SECONDS));
    private boolean enableTls = true;

    private final LinkedList<GrpcHandler> handlers = new LinkedList<>();

    @Override
    public QuotesApiClientBuilder appKey(String appKey) {
        this.appKey = appKey;
        return this;
    }

    @Override
    public QuotesApiClientBuilder appSecret(String appSecret) {
        this.appSecret = appSecret;
        return this;
    }

    @Override
    public QuotesApiClientBuilder host(String host) {
        this.host = host;
        return this;
    }

    @Override
    public QuotesApiClientBuilder port(int port) {
        this.port = port;
        return this;
    }

    @Override
    public QuotesApiClientBuilder regionId(String regionId) {
        this.regionId = regionId;
        return this;
    }

    @Override
    public QuotesApiClientBuilder connectTimeout(long timeoutMillis) {
        this.connectTimeoutMillis = timeoutMillis;
        return this;
    }

    @Override
    public QuotesApiClientBuilder readTimeout(long timeoutMillis) {
        this.readTimeoutMillis = timeoutMillis;
        return this;
    }

    @Override
    public QuotesApiClientBuilder enableTls(boolean enableTls) {
        this.enableTls = enableTls;
        return this;
    }

    @Override
    public QuotesApiClientBuilder reconnectBy(RetryPolicy retryPolicy) {
        this.retryPolicy = retryPolicy;
        return this;
    }

    @Override
    public QuotesApiClientBuilder addHandler(GrpcHandler handler) {
        Assert.notNull("handler", handler);
        this.handlers.add(handler);
        return this;
    }

    @Override
    public QuotesApiClient build() {
        if (StringUtils.isBlank(this.host)) {
            Assert.notBlank("regionId", regionId);
            this.host = EndpointResolver.getDefault().resolve(regionId, ApiModule.QUOTES)
                    .orElseThrow(() -> new ClientException(ErrorCode.ENDPOINT_RESOLVING_ERROR, "Unknown region"));
        }
        if (this.handlers.stream().noneMatch(ApiDowngradeHandler.class::isInstance)) {
            this.handlers.add(new ApiDowngradeLoggingHandler());
        }
        return new GrpcQuotesApiClient(
                this.appKey,
                this.appSecret,
                this.host,
                this.port,
                this.connectTimeoutMillis,
                this.readTimeoutMillis,
                this.retryPolicy,
                this.enableTls,
                this.handlers);
    }
}
