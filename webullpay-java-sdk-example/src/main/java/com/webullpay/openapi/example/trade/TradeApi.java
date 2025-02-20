package com.webullpay.openapi.example.trade;

import com.webullpay.openapi.common.dict.EntrustType;
import com.webullpay.openapi.common.dict.OrderSide;
import com.webullpay.openapi.common.dict.OrderTIF;
import com.webullpay.openapi.common.dict.OrderType;
import com.webullpay.openapi.example.config.Env;
import com.webullpay.openapi.execption.ClientException;
import com.webullpay.openapi.execption.ServerException;
import com.webullpay.openapi.http.HttpApiConfig;
import com.webullpay.openapi.logger.Logger;
import com.webullpay.openapi.logger.LoggerFactory;
import com.webullpay.openapi.trade.api.TradeApiService;
import com.webullpay.openapi.trade.api.http.TradeHttpApiService;
import com.webullpay.openapi.trade.api.request.CryptoOrder;
import com.webullpay.openapi.trade.api.response.Account;
import com.webullpay.openapi.trade.api.response.AccountDetail;
import com.webullpay.openapi.trade.api.response.AccountPositions;
import com.webullpay.openapi.trade.api.response.BalanceBase;
import com.webullpay.openapi.trade.api.response.Order;
import com.webullpay.openapi.trade.api.response.OrderResponse;
import com.webullpay.openapi.trade.api.response.Orders;
import com.webullpay.openapi.trade.api.response.TradableInstruments;
import com.webullpay.openapi.utils.CollectionUtils;
import com.webullpay.openapi.utils.GUID;
import com.webullpay.openapi.utils.StringUtils;

import java.util.List;

public class TradeApi {

    private static final Logger logger = LoggerFactory.getLogger(TradeApi.class);

    public static void main(String[] args) {
        try {
            HttpApiConfig apiConfig = HttpApiConfig.builder()
                .appKey(Env.APP_KEY)
                .appSecret(Env.APP_SECRET)
                .regionId(Env.REGION_ID)
                .build();
            TradeApiService apiService = new TradeHttpApiService(apiConfig);

            // get account list
            List<Account> accounts = apiService.getAccountList("");
            logger.info("Accounts: {}", accounts);

            String accountId = null;
            if (CollectionUtils.isNotEmpty(accounts)) {
                accountId = accounts.get(0).getAccountId();
            }
            if (StringUtils.isBlank(accountId)) {
                logger.info("Account id is empty.");
                return;
            }

            // get account balance
            BalanceBase accountBalance = apiService.getAccountBalance(accountId);
            logger.info("Account balance: {}", accountBalance);

            AccountDetail accountDetail = apiService.getAccountDetail(accountId);
            logger.info("Account detail: {}", accountDetail);

            // get account positions
            AccountPositions accountPositions = apiService.getAccountPositions(accountId);
            logger.info("Account positions: {}", accountPositions);

            // place order
            String requestId = GUID.get();
            CryptoOrder cryptoOrder = new CryptoOrder();
            cryptoOrder.setRequestId(requestId);
            cryptoOrder.setInstrumentId("950160803");
            cryptoOrder.setSide(OrderSide.BUY.name());
            cryptoOrder.setTif(OrderTIF.IOC.name());
            cryptoOrder.setOrderType(OrderType.MKT.name());
            cryptoOrder.setEntrustType(EntrustType.CASH.name());
            cryptoOrder.setAmt("10");

            OrderResponse placeOrderResponse = apiService.placeOrder(accountId, cryptoOrder);
            logger.info("Place order: {}", placeOrderResponse);

            // cancel order
            OrderResponse cancelOrderResponse = apiService.cancelOrder(accountId, placeOrderResponse.getClientOrderId());
            logger.info("Cancel order: {}", cancelOrderResponse);

            // day orders
            Orders<? extends Order> dayOrders = apiService.getDayOrders(accountId, 10, "");
            logger.info("Day orders: {}", dayOrders);

            // opened orders
            Orders<? extends Order> openedOrders = apiService.getOpenedOrders(accountId, 10, "");
            logger.info("Opened orders: {}", openedOrders);

            // order detail
            Order orderDetail = apiService.getOrderDetails(accountId, placeOrderResponse.getClientOrderId());
            logger.info("Order detail: {}", orderDetail);

            // instrument info
            TradableInstruments tradableInstruments = apiService.getTradableInstruments(accountId);
            logger.info("TradableInstruments info: {}", tradableInstruments);

        } catch (ClientException ex) {
            logger.error("Client error", ex);
        } catch (ServerException ex) {
            logger.error("Sever error", ex);
        } catch (Exception ex) {
            logger.error("Unknown error", ex);
        }
    }
}
