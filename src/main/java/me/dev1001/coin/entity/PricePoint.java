package me.dev1001.coin.entity;


import java.util.Date;

/**
 * @author hongzong.li
 */
public class PricePoint {
    private final PriceInfo priceInfo;
    private final Date fetchDate;

    public PricePoint(Date fetchDate, PriceInfo priceInfo) {
        this.priceInfo = priceInfo;
        this.fetchDate = fetchDate;
    }

    public PriceInfo getPriceInfo() {
        return priceInfo;
    }

    public Date getFetchDate() {
        return fetchDate;
    }
}
