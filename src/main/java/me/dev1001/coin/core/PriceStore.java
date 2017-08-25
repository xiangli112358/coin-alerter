package me.dev1001.coin.core;

import me.dev1001.coin.entity.PricePoint;
import me.dev1001.coin.util.DateRange;

import java.util.Map;

/**
 * @author hongzong.li
 */
public interface PriceStore {

    Map<Long, PricePoint> getPricesByRange(DateRange dateRange);

    boolean add(PricePoint pricePoint);

    PricePoint getCurrentPrice();

    Map<Long, PricePoint> dump();
}
