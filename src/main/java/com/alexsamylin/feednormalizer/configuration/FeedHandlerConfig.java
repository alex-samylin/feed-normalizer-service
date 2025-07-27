package com.alexsamylin.feednormalizer.configuration;

import com.alexsamylin.feednormalizer.converter.*;
import com.alexsamylin.feednormalizer.service.FeedMessageHandler;
import com.alexsamylin.feednormalizer.model.enums.Provider;
import com.alexsamylin.feednormalizer.model.raw.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class FeedHandlerConfig {

    @Bean
    public List<FeedMessageHandler<?, ?>> feedMessageHandlers(
            ObjectMapper objectMapper,
            AlphaOddsConverter alphaOddsConverter,
            AlphaSettlementConverter alphaSettlementConverter,
            BetaOddsConverter betaOddsConverter,
            BetaSettlementConverter betaSettlementConverter
    ) {
        return List.of(
                new FeedMessageHandler<>(
                        objectMapper,
                        Provider.ALPHA,
                        "msg_type",
                        "odds_update",
                        AlphaOddsUpdateRawMessage.class,
                        alphaOddsConverter::convert
                ),
                new FeedMessageHandler<>(
                        objectMapper,
                        Provider.ALPHA,
                        "msg_type",
                        "settlement",
                        AlphaSettlementRawMessage.class,
                        alphaSettlementConverter::convert
                ),
                new FeedMessageHandler<>(
                        objectMapper,
                        Provider.BETA,
                        "type",
                        "odds",
                        BetaOddsUpdateRawMessage.class,
                        betaOddsConverter::convert
                ),
                new FeedMessageHandler<>(
                        objectMapper,
                        Provider.BETA,
                        "type",
                        "settlement",
                        BetaSettlementRawMessage.class,
                        betaSettlementConverter::convert
                )
        );
    }
}
