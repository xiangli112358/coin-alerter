package me.dev1001.coin.service;

import me.dev1001.coin.core.PriceStore;
import me.dev1001.coin.entity.PricePoint;
import me.dev1001.coin.util.DateRange;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author hongzong.li
 */
public class CacheBasedPriceStore implements PriceStore {

    private final TreeMap<Long, PricePoint> pricePoints = new TreeMap<>();

    private final int maxSize;

    public CacheBasedPriceStore(int maxSize) {
        checkArgument(maxSize > 0, "maxSize must greater than 0");
        this.maxSize = maxSize;
    }

    @Override
    public synchronized Map<Long, PricePoint> getPricesByRange(DateRange dateRange) {
        return new TreeMap<>(
                pricePoints.subMap(
                        dateRange.getFrom().getTime(), true,
                        dateRange.getTo().getTime(), true
                ));

    }

    @Override
    public synchronized boolean add(PricePoint pricePoint) {
        pricePoints.put(pricePoint.getFetchDate().getTime(), pricePoint);
        if (pricePoints.size() > maxSize) {
            pricePoints.remove(pricePoints.firstKey());
        }
        return true;
    }

    @Override
    public synchronized PricePoint getCurrentPrice() {
        if (pricePoints.isEmpty()) {
            return null;
        }
        return pricePoints.lastEntry().getValue();
    }

    @Override
    public synchronized Map<Long, PricePoint> dump() {
        return new TreeMap<>(pricePoints);
    }
}
