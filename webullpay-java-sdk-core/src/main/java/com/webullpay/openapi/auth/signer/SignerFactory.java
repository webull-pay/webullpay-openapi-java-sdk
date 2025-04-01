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
package com.webullpay.openapi.auth.signer;

import com.webullpay.openapi.execption.ClientException;
import com.webullpay.openapi.execption.ErrorCode;

import java.util.EnumMap;
import java.util.Map;

public class SignerFactory {

    private static final SignerFactory INSTANCE = new SignerFactory();

    private final Map<SignAlgorithm, Signer> signerAlgorithmMap = new EnumMap<>(SignAlgorithm.class);

    private SignerFactory() {
        this.signerAlgorithmMap.put(SignAlgorithm.HMAC_SHA1, new HmacSHA1Signer());
        this.signerAlgorithmMap.put(SignAlgorithm.SHA256_WITH_RSA, new SHA256withRSASigner());
    }

    public static SignerFactory getInstance() {
        return SignerFactory.INSTANCE;
    }

    public Signer get(SignAlgorithm signAlgorithm) {
        if (this.signerAlgorithmMap.containsKey(signAlgorithm)) {
            return this.signerAlgorithmMap.get(signAlgorithm);
        }
        throw new ClientException(ErrorCode.INVALID_PARAMETER, "Unrecognized sign algorithm=" + signAlgorithm);
    }
}
