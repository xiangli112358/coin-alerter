package me.dev1001.coin.entity;

import java.math.BigDecimal;

/**
 * @author hongzong.li
 */
public class PriceInfo {
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal buy;
    private BigDecimal sell;
    private BigDecimal last;
    private BigDecimal vol;
    private BigDecimal volume;

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getBuy() {
        return buy;
    }

    public void setBuy(BigDecimal buy) {
        this.buy = buy;
    }

    public BigDecimal getSell() {
        return sell;
    }

    public void setSell(BigDecimal sell) {
        this.sell = sell;
    }

    public BigDecimal getLast() {
        return last;
    }

    public void setLast(BigDecimal last) {
        this.last = last;
    }

    public BigDecimal getVol() {
        return vol;
    }

    public void setVol(BigDecimal vol) {
        this.vol = vol;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "PriceInfo{" +
            "high=" + high +
            ", low=" + low +
            ", buy=" + buy +
            ", sell=" + sell +
            ", last=" + last +
            ", vol=" + vol +
            ", volume=" + volume +
            '}';
    }
}
