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
package com.webullpay.openapi.http;

import com.webullpay.openapi.common.ApiModule;
import com.webullpay.openapi.endpoint.EndpointResolver;
import com.webullpay.openapi.execption.ClientException;
import com.webullpay.openapi.execption.ErrorCode;
import com.webullpay.openapi.utils.Assert;
import com.webullpay.openapi.utils.StringUtils;

public class HttpApiConfig {

    private String appKey;
    private String appSecret;
    private String regionId;
    private String endpoint;
    private Integer port;
    private boolean autoRetry;
    private int maxRetries;
    private RuntimeOptions runtimeOptions;

    private HttpApiConfig() {
    }

    public String getAppKey() {
        return appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public String getRegionId() {
        return regionId;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public Integer getPort() {
        return port;
    }

    public boolean getAutoRetry() {
        return autoRetry;
    }

    public int getMaxRetries() {
        return maxRetries;
    }

    public RuntimeOptions getRuntimeOptions() {
        return runtimeOptions;
    }

    public static HttpApiConfigBuilder builder() {
        return new HttpApiConfigBuilder();
    }

    public static final class HttpApiConfigBuilder {

        private String appKey;
        private String appSecret;
        private String regionId;
        private String endpoint;
        private Integer port;
        private boolean autoRetry = false;
        private int maxRetries = 3;
        private RuntimeOptions runtimeOptions;

        private HttpApiConfigBuilder() {
        }

        public HttpApiConfigBuilder appKey(String appKey) {
            Assert.notBlank("appKey", appKey);
            this.appKey = appKey;
            return this;
        }

        public HttpApiConfigBuilder appSecret(String appSecret) {
            Assert.notBlank("appSecret", appSecret);
            this.appSecret = appSecret;
            return this;
        }

        public HttpApiConfigBuilder regionId(String regionId) {
            Assert.notBlank("regionId", regionId);
            this.regionId = regionId;
            return this;
        }

        public HttpApiConfigBuilder port(int port) {
            Assert.inRange("port", port, 0, 65535);
            this.port = port;
            return this;
        }

        public HttpApiConfigBuilder autoRetry(boolean autoRetry) {
            this.autoRetry = autoRetry;
            return this;
        }

        public HttpApiConfigBuilder maxRetryNum(int maxRetries) {
            Assert.nonnegative("maxRetries", maxRetries);
            this.maxRetries = maxRetries;
            return this;
        }

        public HttpApiConfigBuilder runtimeOptions(RuntimeOptions runtimeOptions) {
            Assert.notNull("runtimeOptions", runtimeOptions);
            this.runtimeOptions = runtimeOptions;
            return this;
        }

        public HttpApiConfigBuilder endpoint(String endpoint) {
            Assert.notBlank("endpoint", endpoint);
            this.endpoint = endpoint;
            return this;
        }

        public HttpApiConfig build() {
            HttpApiConfig config = new HttpApiConfig();
            config.appKey = this.appKey;
            config.appSecret = this.appSecret;
            config.regionId = this.regionId;

            if (StringUtils.isBlank(this.endpoint)) {
                Assert.notBlank("regionId", regionId);
                EndpointResolver resolver = EndpointResolver.getDefault();
                config.endpoint = resolver.resolve(config.regionId, ApiModule.API)
                        .orElseThrow(() -> new ClientException(ErrorCode.ENDPOINT_RESOLVING_ERROR, "Unknown region"));
            } else {
                config.endpoint = this.endpoint;
            }

            config.port = this.port;
            config.autoRetry = this.autoRetry;
            config.maxRetries = this.maxRetries;
            config.runtimeOptions = this.runtimeOptions != null ? this.runtimeOptions : new RuntimeOptions();
            return config;
        }
    }
}
