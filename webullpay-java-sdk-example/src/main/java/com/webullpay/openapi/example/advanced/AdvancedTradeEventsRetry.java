package com.webullpay.openapi.example.advanced;

import com.webullpay.openapi.example.config.Env;
import com.webullpay.openapi.execption.ClientException;
import com.webullpay.openapi.execption.ServerException;
import com.webullpay.openapi.logger.Logger;
import com.webullpay.openapi.logger.LoggerFactory;
import com.webullpay.openapi.retry.backoff.ExponentialBackoffStrategy;
import com.webullpay.openapi.trade.events.subscribe.EventClient;

public class AdvancedTradeEventsRetry {

    private static final Logger logger = LoggerFactory.getLogger(AdvancedTradeEventsRetry.class);

    public static void main(String[] args) {
        try (EventClient client = EventClient.builder()
                .appKey(Env.APP_KEY)
                .appSecret(Env.APP_SECRET)
                .regionId(Env.REGION_ID)
                // Retry setting
                .reconnectBy(new ExponentialBackoffStrategy())

                .build()) {

            // your code...

        } catch (ClientException ex) {
            logger.error("Client error", ex);
        } catch (ServerException ex) {
            logger.error("Sever error", ex);
        } catch (Exception ex) {
            logger.error("Unknown error", ex);
        }
    }
}
