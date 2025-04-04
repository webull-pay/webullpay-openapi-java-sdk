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
package com.webullpay.openapi.http.okhttp.interceptors;

import okhttp3.Interceptor;
import okhttp3.Response;

import java.io.IOException;

public class SocksProxyAuthInterceptor implements Interceptor {

    private final String user;
    private final String password;

    public SocksProxyAuthInterceptor(String user, String password) {
        this.user = user;
        this.password = password;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        ThreadLocalProxyAuthenticator.getInstance().setCredentials(user, password);
        try {
            return chain.proceed(chain.request());
        } finally {
            ThreadLocalProxyAuthenticator.clearCredentials();
        }
    }
}
