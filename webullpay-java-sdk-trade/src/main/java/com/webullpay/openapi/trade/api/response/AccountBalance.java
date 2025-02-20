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

public class AccountBalance implements BalanceBase {

    private String accountId;
    private String currency;
    private String totalAmount;
    private String cashBalance;
    private String purchasingAmount;
    private String availableAmount;
    private String totalMarketValue;
    private String totalValue;

    @Override
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCashBalance() {
        return cashBalance;
    }

    public void setCashBalance(String cashBalance) {
        this.cashBalance = cashBalance;
    }

    public String getPurchasingAmount() {
        return purchasingAmount;
    }

    public void setPurchasingAmount(String purchasingAmount) {
        this.purchasingAmount = purchasingAmount;
    }

    public String getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(String availableAmount) {
        this.availableAmount = availableAmount;
    }

    @Override
    public String getTotalMarketValue() {
        return totalMarketValue;
    }

    public void setTotalMarketValue(String totalMarketValue) {
        this.totalMarketValue = totalMarketValue;
    }

    public String getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(String totalValue) {
        this.totalValue = totalValue;
    }

    @Override
    public String toString() {
        return "AccountBalance{" +
            "accountId='" + accountId + '\'' +
            ", currency='" + currency + '\'' +
            ", totalAmount='" + totalAmount + '\'' +
            ", cashBalance='" + cashBalance + '\'' +
            ", purchasingAmount='" + purchasingAmount + '\'' +
            ", availableAmount='" + availableAmount + '\'' +
            ", totalMarketValue='" + totalMarketValue + '\'' +
            ", totalValue='" + totalValue + '\'' +
            '}';
    }
}
