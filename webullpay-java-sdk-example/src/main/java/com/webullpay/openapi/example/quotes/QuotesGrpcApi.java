package com.webullpay.openapi.example.quotes;

import com.webullpay.openapi.example.config.Env;
import com.webullpay.openapi.common.dict.Category;
import com.webullpay.openapi.common.dict.Timespan;
import com.webullpay.openapi.execption.ClientException;
import com.webullpay.openapi.execption.ServerException;
import com.webullpay.openapi.logger.Logger;
import com.webullpay.openapi.logger.LoggerFactory;
import com.webullpay.openapi.quotes.api.QuotesApiClient;
import com.webullpay.openapi.quotes.domain.Bar;
import com.webullpay.openapi.quotes.domain.Instrument;
import com.webullpay.openapi.quotes.domain.Quote;
import com.webullpay.openapi.quotes.domain.Snapshot;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QuotesGrpcApi {

    private static final Logger logger = LoggerFactory.getLogger(QuotesGrpcApi.class);

    public static void main(String[] args) {
        String symbol = "BTCUSD";

        try (QuotesApiClient quotesApiClient = QuotesApiClient.builder()
                .appKey(Env.APP_KEY)
                .appSecret(Env.APP_SECRET)
                .regionId(Env.REGION_ID)
                .build()) {

            // get instruments
            Set<String> symbols = new HashSet<>();
            symbols.add(symbol);

            List<Instrument> instruments = quotesApiClient.getInstruments(symbols, Category.CRYPTO.name());
            logger.info("Instruments: {}", instruments);

            // get bars
            List<Bar> bars = quotesApiClient.getBars(symbol, Category.CRYPTO.name(), Timespan.D.name(), 10);
            logger.info("Bars: {}", bars);

            // get quote
            Quote quote = quotesApiClient.getQuote(symbol, Category.CRYPTO.name());
            logger.info("Quote: {}", quote);

            // get snapshots
            List<Snapshot> snapshots = quotesApiClient.getSnapshots(symbols, Category.CRYPTO.name());
            logger.info("Snapshots: {}", snapshots);

        } catch (ClientException ex) {
            logger.error("Client error", ex);
        } catch (ServerException ex) {
            logger.error("Sever error", ex);
        } catch (Exception ex) {
            logger.error("Unknown error", ex);
        }
    }
}
