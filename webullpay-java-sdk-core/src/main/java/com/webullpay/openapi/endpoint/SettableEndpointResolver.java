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
package com.webullpay.openapi.endpoint;

import com.webullpay.openapi.common.ApiModule;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

class SettableEndpointResolver implements EndpointResolver {

    private final Map<String, String> endpoints = new ConcurrentHashMap<>();

    private SettableEndpointResolver() {
    }

    private static class InstanceHolder {
        private static final SettableEndpointResolver instance = new SettableEndpointResolver();
    }

    public static SettableEndpointResolver getInstance() {
        return SettableEndpointResolver.InstanceHolder.instance;
    }

    @Override
    public void addEndpoint(final String regionId, final ApiModule apiModule, final String endpoint) {
        String entryKey = getKey(regionId, apiModule);
        this.endpoints.put(entryKey, endpoint);
    }

    @Override
    public int order() {
        return 1;
    }

    @Override
    public Optional<String> resolve(final String regionId, final ApiModule apiModule) {
        String entryKey = getKey(regionId, apiModule);
        return Optional.ofNullable(this.endpoints.get(entryKey));
    }

    private String getKey(final String regionId, final ApiModule apiModule) {
        return regionId + "-" + apiModule.name();
    }
}
