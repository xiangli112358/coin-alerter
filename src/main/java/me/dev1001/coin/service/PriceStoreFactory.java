package me.dev1001.coin.service;

/**
 * @author hongzong.li
 */
public class PriceStoreFactory {
    private static final int MAX_SIZE = 20160;

    private static final PriceStore store = new CacheBasedPriceStore(MAX_SIZE);

    public static PriceStore getPriceStore() {
        return store;
    }
}
