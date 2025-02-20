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

public class PositionInfo {

    private String id;
    private String instrumentId;
    private String symbol;
    private String name;
    private String instrumentType;
    private String quantity;
    private String unitCost;
    private String currency;
    private String totalCost;
    private String lastPrice;
    private String marketValue;
    private String unrealizedProfitLoss;
    private String unrealizedProfitLossRate;
    private String unrealizedProfitLossBase;
    private String updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(String instrumentId) {
        this.instrumentId = instrumentId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstrumentType() {
        return instrumentType;
    }

    public void setInstrumentType(String instrumentType) {
        this.instrumentType = instrumentType;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(String unitCost) {
        this.unitCost = unitCost;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }

    public String getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(String lastPrice) {
        this.lastPrice = lastPrice;
    }

    public String getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(String marketValue) {
        this.marketValue = marketValue;
    }

    public String getUnrealizedProfitLoss() {
        return unrealizedProfitLoss;
    }

    public void setUnrealizedProfitLoss(String unrealizedProfitLoss) {
        this.unrealizedProfitLoss = unrealizedProfitLoss;
    }

    public String getUnrealizedProfitLossRate() {
        return unrealizedProfitLossRate;
    }

    public void setUnrealizedProfitLossRate(String unrealizedProfitLossRate) {
        this.unrealizedProfitLossRate = unrealizedProfitLossRate;
    }

    public String getUnrealizedProfitLossBase() {
        return unrealizedProfitLossBase;
    }

    public void setUnrealizedProfitLossBase(String unrealizedProfitLossBase) {
        this.unrealizedProfitLossBase = unrealizedProfitLossBase;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "PositionInfo{" +
            "id='" + id + '\'' +
            ", instrumentId='" + instrumentId + '\'' +
            ", symbol='" + symbol + '\'' +
            ", name='" + name + '\'' +
            ", instrumentType='" + instrumentType + '\'' +
            ", quantity='" + quantity + '\'' +
            ", unitCost='" + unitCost + '\'' +
            ", currency='" + currency + '\'' +
            ", totalCost='" + totalCost + '\'' +
            ", lastPrice='" + lastPrice + '\'' +
            ", marketValue='" + marketValue + '\'' +
            ", unrealizedProfitLoss='" + unrealizedProfitLoss + '\'' +
            ", unrealizedProfitLossRate='" + unrealizedProfitLossRate + '\'' +
            ", unrealizedProfitLossBase='" + unrealizedProfitLossBase + '\'' +
            ", updateTime='" + updateTime + '\'' +
            '}';
    }
}
