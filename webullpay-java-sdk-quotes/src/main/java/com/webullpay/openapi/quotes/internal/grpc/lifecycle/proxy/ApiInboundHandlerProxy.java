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
package com.webullpay.openapi.quotes.internal.grpc.lifecycle.proxy;

import com.webullpay.openapi.grpc.lifecycle.SubStreamObserver;
import com.webullpay.openapi.quotes.api.lifecycle.ApiInboundHandler;
import com.webullpay.openapi.quotes.api.message.ApiResponse;
import com.webullpay.openapi.quotes.internal.grpc.proto.Gateway;

public class ApiInboundHandlerProxy implements SubStreamObserver<Gateway.ClientResponse> {

    private final ApiInboundHandler inboundHandler;

    public ApiInboundHandlerProxy(ApiInboundHandler inboundHandler) {
        this.inboundHandler = inboundHandler;
    }

    @Override
    public void onNext(Gateway.ClientResponse value) {
        if (Gateway.MsgType.Payload == value.getType()) {
            ApiResponse response = new ApiResponse(value.getTypeValue(), value.getRequestId(), value.getCode(),
                    value.getMsg(), value.getPath(), value.getPayload().toByteArray());
            this.inboundHandler.onMessage(response);
        }
    }
}
