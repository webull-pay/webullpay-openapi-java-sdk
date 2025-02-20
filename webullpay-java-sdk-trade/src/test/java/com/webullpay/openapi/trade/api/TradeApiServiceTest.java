package com.webullpay.openapi.trade.api;

import com.webullpay.openapi.execption.ClientException;
import com.webullpay.openapi.http.HttpApiConfig;
import com.webullpay.openapi.trade.api.http.TradeHttpApiService;
import org.junit.Assert;
import org.junit.Test;

public class TradeApiServiceTest {

    @Test
    public void whenRegionUnsetThenBuildServiceFailed() {
        HttpApiConfig apiConfig = HttpApiConfig.builder()
                .appKey("appKey")
                .appSecret("appSecret")
                .endpoint("127.0.0.1")
                .build();
        Assert.assertThrows(ClientException.class, () -> new TradeHttpApiService(apiConfig));
    }
}
