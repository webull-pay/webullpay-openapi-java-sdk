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
package com.webullpay.openapi.trade.api.response;

public class SimpleOrder extends OrderItem implements Order {

    private String accountId;
    private String clientOrderId;

    @Override
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @Override
    public String getClientOrderId() {
        return clientOrderId;
    }

    public void setClientOrderId(String clientOrderId) {
        this.clientOrderId = clientOrderId;
    }

    @Override
    public String toString() {
        return "SimpleOrder{" +
            "accountId='" + accountId + '\'' +
            ", clientOrderId='" + clientOrderId + '\'' +
            ", category='" + category + '\'' +
            ", limitPrice='" + limitPrice + '\'' +
            ", stopPrice='" + stopPrice + '\'' +
            ", orderStatus='" + orderStatus + '\'' +
            ", orderType='" + orderType + '\'' +
            ", placeQty='" + placeQty + '\'' +
            ", placeAmt='" + placeAmt + '\'' +
            ", filledPrice='" + filledPrice + '\'' +
            ", filledQty='" + filledQty + '\'' +
            ", filledAmt='" + filledAmt + '\'' +
            ", side='" + side + '\'' +
            ", tif='" + tif + '\'' +
            ", entrustType='" + entrustType + '\'' +
            ", instrumentId='" + instrumentId + '\'' +
            ", currency='" + currency + '\'' +
            ", symbol='" + symbol + '\'' +
            ", shortName='" + shortName + '\'' +
            '}';
    }
}
