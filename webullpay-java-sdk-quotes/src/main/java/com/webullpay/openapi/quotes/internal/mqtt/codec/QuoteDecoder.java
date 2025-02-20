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
package com.webullpay.openapi.quotes.internal.mqtt.codec;

import com.google.protobuf.InvalidProtocolBufferException;
import com.webullpay.openapi.quotes.domain.AskBid;
import com.webullpay.openapi.quotes.domain.Quote;
import com.webullpay.openapi.quotes.subsribe.exception.DecoderException;
import com.webullpay.openapi.quotes.subsribe.message.QuotesPublish;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.stream.Collectors;

public class QuoteDecoder implements QuotesPublishDecoder<Quote> {

    @Override
    public QuotesPublish<Quote> decode(ByteBuffer in) {
        try {
            Quotes.Quote from = Quotes.Quote.parseFrom(in);
            Quote quote = assembleQuote(from);
            return new QuotesPublish<>(from.getBasic().getTimestamp(), quote);
        } catch (InvalidProtocolBufferException e) {
            throw new DecoderException("Decode quote data error", e);
        }
    }

    private Quote assembleQuote(Quotes.Quote from) {
        Quote result = new Quote();
        result.setSymbol(from.getBasic().getSymbol());
        result.setInstrumentId(from.getBasic().getInstrumentId());
        List<AskBid> asks = from.getAsksList().stream().map(this::assembleAskBid).collect(Collectors.toList());
        result.setAsks(asks);
        List<AskBid> bids = from.getBidsList().stream().map(this::assembleAskBid).collect(Collectors.toList());
        result.setBids(bids);
        return result;
    }

    private AskBid assembleAskBid(Quotes.AskBid from) {
        AskBid result = new AskBid();
        result.setPrice(from.getPrice());
        return result;
    }
}
