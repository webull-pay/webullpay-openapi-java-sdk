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
package com.webullpay.openapi.utils;

import java.nio.ByteBuffer;

public final class ByteUtils {

    private ByteUtils() {
    }

    private static final char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9', 'a','b','c','d','e','f'};

    public static String bytesToHex(final byte[] bytes) {
        char[] resultCharArray = new char[bytes.length * 2];
        int index = 0;
        for (byte b : bytes) {
            resultCharArray[index++] = hexDigits[b>>>4 & 0xf];
            resultCharArray[index++] = hexDigits[b & 0xf];
        }
        return new String(resultCharArray);
    }

    public static byte[] getBytes(final ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            return new byte[0];
        }
        final byte[] binary = new byte[byteBuffer.remaining()];
        byteBuffer.duplicate().get(binary);
        return binary;
    }
}
