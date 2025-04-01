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

import java.util.List;

public class AccountPositions {

    private String accountId;
    private String totalMarketValue;
    private String investAmt;
    private String unrealizedProfitLoss;
    private String unrealizedProfitLossRate;

    private List<PositionInfo> positions;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getTotalMarketValue() {
        return totalMarketValue;
    }

    public void setTotalMarketValue(String totalMarketValue) {
        this.totalMarketValue = totalMarketValue;
    }

    public String getInvestAmt() {
        return investAmt;
    }

    public void setInvestAmt(String investAmt) {
        this.investAmt = investAmt;
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

    public List<PositionInfo> getPositions() {
        return positions;
    }

    public void setPositions(List<PositionInfo> positions) {
        this.positions = positions;
    }

    @Override
    public String toString() {
        return "AccountPositions{" +
            "accountId='" + accountId + '\'' +
            ", totalMarketValue='" + totalMarketValue + '\'' +
            ", investAmt='" + investAmt + '\'' +
            ", unrealizedProfitLoss='" + unrealizedProfitLoss + '\'' +
            ", unrealizedProfitLossRate='" + unrealizedProfitLossRate + '\'' +
            ", positions=" + positions +
            '}';
    }
}
