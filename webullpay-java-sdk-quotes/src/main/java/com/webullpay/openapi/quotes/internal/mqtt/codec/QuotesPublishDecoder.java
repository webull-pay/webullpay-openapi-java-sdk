package com.webullpay.openapi.quotes.internal.mqtt.codec;

import com.webullpay.openapi.quotes.domain.QuotesBasic;
import com.webullpay.openapi.quotes.subsribe.codec.Decoder;
import com.webullpay.openapi.quotes.subsribe.message.QuotesPublish;

import java.nio.ByteBuffer;

public interface QuotesPublishDecoder<T extends QuotesBasic> extends Decoder<ByteBuffer, QuotesPublish<T>> {
}
