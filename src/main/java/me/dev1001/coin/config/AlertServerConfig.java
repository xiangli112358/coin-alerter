package me.dev1001.coin.config;

import me.dev1001.coin.service.CacheBasedPriceStore;
import me.dev1001.coin.service.DefaultPriceFetcher;
import me.dev1001.coin.service.PriceFetcher;
import me.dev1001.coin.service.PriceStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by think on 8/25/2017.
 */
@Configuration
public class AlertServerConfig {
    private static final int MAX_STORE_SIZE = 2 * 60 * 24 * 7;

    @Bean
    public PriceStore priceStore() {
        return new CacheBasedPriceStore(MAX_STORE_SIZE);
    }

    @Bean
    public PriceFetcher priceFetcher() {
        return new DefaultPriceFetcher();
    }

}
