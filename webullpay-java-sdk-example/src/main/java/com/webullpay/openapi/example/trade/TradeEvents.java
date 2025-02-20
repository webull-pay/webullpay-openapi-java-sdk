package com.webullpay.openapi.example.trade;

import com.google.common.reflect.TypeToken;
import com.webullpay.openapi.example.config.Env;
import com.webullpay.openapi.execption.ClientException;
import com.webullpay.openapi.execption.ServerException;
import com.webullpay.openapi.logger.Logger;
import com.webullpay.openapi.logger.LoggerFactory;
import com.webullpay.openapi.serialize.JsonSerializer;
import com.webullpay.openapi.trade.events.subscribe.EventClient;
import com.webullpay.openapi.trade.events.subscribe.Subscription;
import com.webullpay.openapi.trade.events.subscribe.message.EventType;
import com.webullpay.openapi.trade.events.subscribe.message.SubscribeRequest;
import com.webullpay.openapi.trade.events.subscribe.message.SubscribeResponse;

import java.util.Map;

public class TradeEvents {

    private static final Logger logger = LoggerFactory.getLogger(TradeEvents.class);

    public static void main(String[] args) {
        try (EventClient client = EventClient.builder()
                .appKey(Env.APP_KEY)
                .appSecret(Env.APP_SECRET)
                .regionId(Env.REGION_ID)
                .onMessage(TradeEvents::handleEventMessage)
                .build()) {

            SubscribeRequest request = new SubscribeRequest("<your_account_id>");

            Subscription subscription = client.subscribe(request);
            subscription.blockingAwait();

        } catch (ClientException ex) {
            logger.error("Client error", ex);
        } catch (ServerException ex) {
            logger.error("Sever error", ex);
        } catch (Exception ex) {
            logger.error("Unknown error", ex);
        }
    }

    private static void handleEventMessage(SubscribeResponse response) {
        if (SubscribeResponse.CONTENT_TYPE_JSON.equals(response.getContentType())) {
            Map<String, String> payload = JsonSerializer.fromJson(response.getPayload(),
                    new TypeToken<Map<String, String>>(){}.getType());
            if (EventType.Order.getCode() == response.getEventType()) {
                logger.info("--------", response.getRequestId());
                logger.info(payload.get("account_id"));
                logger.info(payload.get("client_order_id"));
                logger.info(payload.get("order_status"));
            }
        }
    }
}
