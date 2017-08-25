package me.dev1001.coin.config;

import me.dev1001.coin.core.Notifier;
import me.dev1001.coin.core.PriceFetcher;
import me.dev1001.coin.core.PriceStore;
import me.dev1001.coin.service.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by think on 8/25/2017.
 */
@Configuration
public class AlertServerConfig {
    private static final int MAX_STORE_SIZE = 2 * 60 * 24 * 7;

    @Value("${store.maxSize}")
    private int maxStoreSize;

    @Value("${pushover.userKey}")
    private  String pushOverUserKey;

    @Value("${pushover.appId}")
    private  String pushOverAppId;

    @Bean
    public PriceStore priceStore() {
        return new CacheBasedPriceStore(MAX_STORE_SIZE);
    }

    @Bean
    public PriceFetcher priceFetcher() {
        return new DefaultPriceFetcher();
    }

    @Bean
    public Notifier notifier() {
        return new PushOverNotifier(pushOverUserKey, pushOverAppId);
    }

}
