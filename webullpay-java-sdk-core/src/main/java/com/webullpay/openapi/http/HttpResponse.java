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

import com.google.gson.reflect.TypeToken;
import com.webullpay.openapi.common.Headers;
import com.webullpay.openapi.execption.ClientException;
import com.webullpay.openapi.execption.ErrorCode;
import com.webullpay.openapi.http.common.HttpStatus;
import com.webullpay.openapi.http.exception.HttpServerException;
import com.webullpay.openapi.logger.Logger;
import com.webullpay.openapi.logger.LoggerFactory;
import com.webullpay.openapi.serialize.JsonSerializer;
import okhttp3.Response;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpResponse implements Closeable {

    private static final Logger logger = LoggerFactory.getLogger(HttpResponse.class);

    private HttpServerException exception;
    private final Response response;
    private final int statusCode;
    private final String statusMessage;
    private final String requestId;
    private final HashMap<String, String> headers = new HashMap<>();
    private final InputStream body;

    public HttpResponse(Response response) {
        this.response = response;
        this.statusCode = response.code();
        this.statusMessage = response.message();
        this.body = response.body() != null ? response.body().byteStream() : null;
        Map<String, List<String>> resultHeaders = response.headers().toMultimap();
        for (Map.Entry<String, List<String>> entry : resultHeaders.entrySet()) {
            this.headers.put(entry.getKey(), String.join(";", entry.getValue()));
        }
        this.requestId = this.headers.get(Headers.REQUEST_ID);
        if (this.statusCode < HttpStatus.OK || this.statusCode >= HttpStatus.MULTIPLE_CHOICES) {
            String responseBody = this.getResponseBody();
            Map<String, Object> responseMap = new HashMap<>();
            try {
                responseMap = JsonSerializer.fromJson(responseBody, new TypeToken<Map<String, Object>>(){}.getType());
            } catch (Exception e) {
                logger.warn("Failed to parse response as json format, response:{}, requestId:{}", responseBody, this.requestId);
            }
            String errorCode = null;
            String message = null;
            if (responseMap.containsKey("error_code")) {
                errorCode = responseMap.get("error_code").toString();
            }
            if (responseMap.containsKey("message")) {
                message = responseMap.get("message").toString();
            }
            if (errorCode == null || errorCode.isEmpty()) {
                errorCode = responseMap.getOrDefault("code", ErrorCode.UNKNOWN_SERVER_ERROR).toString();
            }
            if (message == null || message.isEmpty()) {
                message = responseMap.getOrDefault("msg", "").toString();
            }
            this.exception = new HttpServerException(errorCode, message, this.statusCode, this.requestId);
        }
    }

    public boolean isSuccess() {
        return this.getException() == null;
    }

    public HttpServerException getException() {
        return this.exception;
    }

    public InputStream getResponse() {
        return this.body;
    }

    public String getResponseBody() {
        if (body == null) {
            return String.format("{\"message\":\"%s\"}", statusMessage);
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buff = new byte[4096];
        try {
            while (true) {
                final int read = body.read(buff);
                if (read == -1) {
                    break;
                }
                os.write(buff, 0, read);
            }
        } catch (Exception e) {
            throw new ClientException(e.getMessage(), e);
        }
        return os.toString();
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }

    @Override
    public void close() {
        response.close();
    }
}
