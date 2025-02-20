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
package com.webullpay.openapi.quotes.internal.http;

import com.google.common.reflect.TypeToken;
import com.webullpay.openapi.common.Versions;
import com.webullpay.openapi.http.HttpApiClient;
import com.webullpay.openapi.http.HttpApiConfig;
import com.webullpay.openapi.http.HttpRequest;
import com.webullpay.openapi.http.common.HttpMethod;
import com.webullpay.openapi.quotes.api.QuotesApiClient;
import com.webullpay.openapi.quotes.domain.Bar;
import com.webullpay.openapi.quotes.domain.CorpAction;
import com.webullpay.openapi.quotes.domain.CorpActionRequest;
import com.webullpay.openapi.quotes.domain.EodBars;
import com.webullpay.openapi.quotes.domain.Instrument;
import com.webullpay.openapi.quotes.domain.Quote;
import com.webullpay.openapi.quotes.domain.Snapshot;
import com.webullpay.openapi.quotes.domain.Tick;
import com.webullpay.openapi.quotes.internal.common.ArgNames;
import com.webullpay.openapi.utils.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HttpQuotesApiClient implements QuotesApiClient {

    private static final String NOT_SUPPORT_MSG = "Http client not support for this method, please use default grpc client.";

    private final HttpApiClient apiClient;

    public HttpQuotesApiClient(HttpApiConfig config) {
        this.apiClient = new HttpApiClient(config);
    }

    public HttpQuotesApiClient(HttpApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Override
    public List<Instrument> getInstruments(Set<String> symbols, String category) {
        Assert.notEmpty(ArgNames.SYMBOLS, symbols);
        Assert.notBlank(ArgNames.CATEGORY, category);
        HttpRequest request = new HttpRequest("/instrument/list", Versions.V1, HttpMethod.GET);
        Map<String, Object> params = new HashMap<>();
        params.put(ArgNames.SYMBOLS, String.join(",", symbols));
        params.put(ArgNames.CATEGORY, category);
        request.setQuery(params);
        return apiClient.request(request).responseType(new TypeToken<List<Instrument>>() {}.getType()).doAction();
    }

    @Override
    public List<Bar> getBars(String symbol, String category, String timespan, int count) {
        Assert.notBlank(Arrays.asList(ArgNames.SYMBOL, ArgNames.CATEGORY, ArgNames.TIMESPAN), symbol, category, timespan);
        HttpRequest request = new HttpRequest("/market-data/bars", Versions.V1, HttpMethod.GET);
        Map<String, Object> params = new HashMap<>();
        params.put(ArgNames.SYMBOL, symbol);
        params.put(ArgNames.CATEGORY, category);
        params.put(ArgNames.TIMESPAN, timespan);
        params.put(ArgNames.COUNT, count);
        request.setQuery(params);
        return apiClient.request(request).responseType(new TypeToken<List<Bar>>() {}.getType()).doAction();
    }

    @Override
    public List<EodBars> getEodBars(Set<String> instrumentIds, String date, Integer count){
        throw new UnsupportedOperationException(NOT_SUPPORT_MSG);
    }

    @Override
    public List<CorpAction> getCorpAction(CorpActionRequest action){
        throw new UnsupportedOperationException(NOT_SUPPORT_MSG);
    }


    @Override
    public Quote getQuote(String symbol, String category) {
        Assert.notBlank(ArgNames.SYMBOL, symbol);
        Assert.notBlank(ArgNames.CATEGORY, category);
        HttpRequest request = new HttpRequest("/market-data/quotes", Versions.V1, HttpMethod.GET);
        Map<String, Object> params = new HashMap<>();
        params.put(ArgNames.SYMBOL, symbol);
        params.put(ArgNames.CATEGORY, category);
        request.setQuery(params);
        return apiClient.request(request).responseType(new TypeToken<Quote>() {}.getType()).doAction();
    }

    @Override
    public List<Snapshot> getSnapshots(Set<String> symbols, String category) {
        Assert.notEmpty(ArgNames.SYMBOLS, symbols);
        Assert.notBlank(ArgNames.CATEGORY, category);
        HttpRequest request = new HttpRequest("/market-data/snapshot", Versions.V1, HttpMethod.GET);
        Map<String, Object> params = new HashMap<>();
        params.put(ArgNames.SYMBOLS, String.join(",", symbols));
        params.put(ArgNames.CATEGORY, category);
        request.setQuery(params);
        return apiClient.request(request).responseType(new TypeToken<List<Snapshot>>() {}.getType()).doAction();
    }

    @Override
    public List<Tick> getTicks(String symbol, String category, int count) {
        throw new UnsupportedOperationException(NOT_SUPPORT_MSG);
    }

    @Override
    public String getToken() {
        throw new UnsupportedOperationException(NOT_SUPPORT_MSG);
    }

    @Override
    public void subscribe(String token, Set<String> symbols, String category, Set<String> subTypes) {
        throw new UnsupportedOperationException(NOT_SUPPORT_MSG);
    }

    @Override
    public void unsubscribe(String token, Set<String> symbols, String category, Set<String> subTypes, Boolean unsubscribeAll) {
        throw new UnsupportedOperationException(NOT_SUPPORT_MSG);
    }
}
