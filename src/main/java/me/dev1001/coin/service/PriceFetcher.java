package me.dev1001.coin.service;

import me.dev1001.coin.entity.PriceInfo;

/**
 * @author hongzong.li
 */
public interface PriceFetcher {
    PriceInfo fetchPrice(String coin);
}
