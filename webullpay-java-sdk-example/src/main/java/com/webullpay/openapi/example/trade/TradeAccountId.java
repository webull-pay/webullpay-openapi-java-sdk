package com.webullpay.openapi.example.trade;

import com.webullpay.openapi.example.config.Env;
import com.webullpay.openapi.execption.ClientException;
import com.webullpay.openapi.execption.ServerException;
import com.webullpay.openapi.http.HttpApiConfig;
import com.webullpay.openapi.logger.Logger;
import com.webullpay.openapi.logger.LoggerFactory;
import com.webullpay.openapi.trade.api.TradeApiService;
import com.webullpay.openapi.trade.api.http.TradeHttpApiService;
import com.webullpay.openapi.trade.api.response.Account;
import com.webullpay.openapi.utils.CollectionUtils;
import com.webullpay.openapi.utils.StringUtils;

import java.util.List;

public class TradeAccountId {

    private static final Logger logger = LoggerFactory.getLogger(TradeAccountId.class);

    public static void main(String[] args) {
        try {
            HttpApiConfig apiConfig = HttpApiConfig.builder()
                    .appKey(Env.APP_KEY)
                    .appSecret(Env.APP_SECRET)
                    .regionId(Env.REGION_ID)
                    .build();
            TradeApiService apiService = new TradeHttpApiService(apiConfig);

            List<Account> accounts = apiService.getAccountList("");

            if (CollectionUtils.isNotEmpty(accounts)) {
                String accountId = accounts.get(0).getAccountId();
                if (StringUtils.isNotBlank(accountId)) {
                    logger.info("Account id: {}", accountId);
                    return;
                }
            }
            logger.info("Account id is empty.");
        } catch (ClientException ex) {
            logger.error("Client error", ex);
        } catch (ServerException ex) {
            logger.error("Sever error", ex);
        } catch (Exception ex) {
            logger.error("Unknown error", ex);
        }
    }
}
