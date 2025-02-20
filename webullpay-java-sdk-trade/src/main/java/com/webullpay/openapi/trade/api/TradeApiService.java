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
package com.webullpay.openapi.trade.api;

import com.webullpay.openapi.trade.api.response.Account;
import com.webullpay.openapi.trade.api.response.AccountDetail;
import com.webullpay.openapi.trade.api.response.BalanceBase;
import com.webullpay.openapi.trade.api.response.OrderResponse;
import com.webullpay.openapi.trade.api.response.Orders;
import com.webullpay.openapi.trade.api.response.TradableInstruments;
import com.webullpay.openapi.trade.api.request.CryptoOrder;
import com.webullpay.openapi.trade.api.response.AccountPositions;
import com.webullpay.openapi.trade.api.response.Order;

import java.util.List;

public interface TradeApiService {

    List<Account> getAccountList(String subscriptionId);

    AccountDetail getAccountDetail(String accountId);

    <T extends BalanceBase> T getAccountBalance(String accountId);

    AccountPositions getAccountPositions(String accountId);

    OrderResponse placeOrder(String accountId, CryptoOrder cryptoOrder);

    OrderResponse cancelOrder(String accountId, String clientOrderId);

    <T extends Order> Orders<T> getDayOrders(String accountId, Integer pageSize, String lastClientOrderId);

    <T extends Order> Orders<T> getOpenedOrders(String accountId, Integer pageSize, String lastClientOrderId);

    <T extends Order> T getOrderDetails(String accountId, String clientOrderId);

    TradableInstruments getTradableInstruments(String accountId);

}
