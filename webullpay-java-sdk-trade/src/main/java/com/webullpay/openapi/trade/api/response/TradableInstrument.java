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

public class TradableInstrument extends InstrumentBasic {

    private String name;
    private String minTradeAmt;
    private String maxTradeAmt;
    private String minTradeQty;
    private String maxTradeQty;
    private String priceSteps;
    private String lotSize;
    private String tradePolicy;
    private String currency;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMinTradeAmt() {
        return minTradeAmt;
    }

    public void setMinTradeAmt(String minTradeAmt) {
        this.minTradeAmt = minTradeAmt;
    }

    public String getMaxTradeAmt() {
        return maxTradeAmt;
    }

    public void setMaxTradeAmt(String maxTradeAmt) {
        this.maxTradeAmt = maxTradeAmt;
    }

    public String getMinTradeQty() {
        return minTradeQty;
    }

    public void setMinTradeQty(String minTradeQty) {
        this.minTradeQty = minTradeQty;
    }

    public String getMaxTradeQty() {
        return maxTradeQty;
    }

    public void setMaxTradeQty(String maxTradeQty) {
        this.maxTradeQty = maxTradeQty;
    }

    public String getPriceSteps() {
        return priceSteps;
    }

    public void setPriceSteps(String priceSteps) {
        this.priceSteps = priceSteps;
    }

    public String getLotSize() {
        return lotSize;
    }

    public void setLotSize(String lotSize) {
        this.lotSize = lotSize;
    }

    public String getTradePolicy() {
        return tradePolicy;
    }

    public void setTradePolicy(String tradePolicy) {
        this.tradePolicy = tradePolicy;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "TradableInstrument{" +
            "instrumentType='" + instrumentType + '\'' +
            ", instrumentId='" + instrumentId + '\'' +
            ", symbol='" + symbol + '\'' +
            ", currency='" + currency + '\'' +
            ", tradePolicy='" + tradePolicy + '\'' +
            ", lotSize='" + lotSize + '\'' +
            ", priceSteps='" + priceSteps + '\'' +
            ", maxTradeQty='" + maxTradeQty + '\'' +
            ", minTradeQty='" + minTradeQty + '\'' +
            ", maxTradeAmt='" + maxTradeAmt + '\'' +
            ", minTradeAmt='" + minTradeAmt + '\'' +
            ", name='" + name + '\'' +
            '}';
    }
}
