package me.dev1001.coin.service;

import me.dev1001.coin.entity.PriceInfo;
import me.dev1001.coin.util.HttpClientHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hongzong.li
 */
public class DefaultPriceFetcher implements PriceFetcher {
    private static final String PRICE_API_URL = "https://www.jubi.com/api/v1/ticker";

    private static final Log logger = LogFactory.getLog(DefaultPriceFetcher.class);

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
