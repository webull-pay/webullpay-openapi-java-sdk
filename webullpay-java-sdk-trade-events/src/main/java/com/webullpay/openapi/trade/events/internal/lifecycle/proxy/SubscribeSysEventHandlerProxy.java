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
package com.webullpay.openapi.trade.events.internal.lifecycle.proxy;

import com.webullpay.openapi.grpc.lifecycle.SubStreamObserver;
import com.webullpay.openapi.trade.events.subscribe.lifecycle.SubscribeSysEventHandler;
import com.webullpay.openapi.trade.events.subscribe.message.EventType;
import com.webullpay.openapi.trade.events.subscribe.message.SubscribeResponse;

public class SubscribeSysEventHandlerProxy implements SubStreamObserver<SubscribeResponse> {

    private final SubscribeSysEventHandler sysEventHandler;

    public SubscribeSysEventHandlerProxy(SubscribeSysEventHandler sysEventHandler) {
        this.sysEventHandler = sysEventHandler;
    }

    @Override
    public void onNext(SubscribeResponse response) {
        if (EventType.Ping.getCode() == response.getEventType()) {
            this.sysEventHandler.onPing(response);
        } else if (EventType.AuthError.getCode() == response.getEventType()) {
            this.sysEventHandler.onAuthError(response);
        } else if (EventType.NumOfConnExceed.getCode() == response.getEventType()) {
            this.sysEventHandler.onNumOfConnectionExceed(response);
        } else if (EventType.SubscribeExpired.getCode() == response.getEventType()) {
            this.sysEventHandler.onSubscribeExpired(response);
        }
    }
}
