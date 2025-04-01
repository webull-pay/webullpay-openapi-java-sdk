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
package com.webullpay.openapi.quotes.subsribe.message;

import com.webullpay.openapi.quotes.domain.QuotesBasic;

public class MarketData {

    private final Metadata metadata;
    private final QuotesPublish<?> publish;

    public MarketData(Metadata metadata, QuotesPublish<?> publish) {
        this.metadata = metadata;
        this.publish = publish;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public QuotesBasic getData() {
        return publish.getData();
    }

    public String getTimestamp() {
        return publish.getTimestamp();
    }

    @Override
    public String toString() {
        return "MarketData{" +
                "metadata=" + metadata +
                ", publish=" + publish +
                '}';
    }
}
