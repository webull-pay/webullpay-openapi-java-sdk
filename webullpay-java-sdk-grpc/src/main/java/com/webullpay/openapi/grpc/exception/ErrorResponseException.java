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
package com.webullpay.openapi.grpc.exception;

import com.webullpay.openapi.execption.ErrorCode;
import com.webullpay.openapi.execption.ServerException;

public class ErrorResponseException extends ServerException {

    private final int responseCode;

    public ErrorResponseException(int responseCode, String errorMsg, String requestId) {
        super(ErrorCode.UNKNOWN_SERVER_ERROR, errorMsg, formatDetailMessage(responseCode, errorMsg, requestId),
                requestId, null);
        this.responseCode = responseCode;
    }

    public int getResponseCode() {
        return responseCode;
    }

    private static String formatDetailMessage(int responseCode, String errorMsg, String requestId) {
        return String.format("responseCode=%d, errorMsg=%s, requestId=%s", responseCode, errorMsg, requestId);
    }
}
