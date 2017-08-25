package me.dev1001.coin.service;

import me.dev1001.coin.entity.PriceInfo;
import me.dev1001.coin.util.HttpClientHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hongzong.li
 */
@Service
public class DefaultPriceFetcher implements PriceFetcher {
    private static final String PRICE_API_URL = "https://www.jubi.com/api/v1/ticker";

    private static final Logger logger = LoggerFactory.getLogger(DefaultPriceFetcher.class);

    @Override
    public PriceInfo fetchPrice(String coin) {
        Map<String, String> params = new HashMap<>();
        params.put("coin", coin);
        try {
            return HttpClientHelper.postForObject(PRICE_API_URL, params, PriceInfo.class);
        } catch (Exception e) {
            logger.error("Fail to fetch price", e);
            return null;
        }
    }
}
