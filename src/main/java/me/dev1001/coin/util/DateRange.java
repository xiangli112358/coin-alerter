package me.dev1001.coin.util;

import java.util.Date;

/**
 * @author hongzong.li
 */
public class DateRange {
    private final long fromMillis;
    private final long toMillis;

    public DateRange(Date from, Date to) {
        this.fromMillis = from.getTime();
        this.toMillis = to.getTime();
        if (this.toMillis < this.fromMillis) {
            throw new IllegalArgumentException("Invalid date range");
        }
    }

    public Date getFrom() {
        return new Date(fromMillis);
    }

    public Date getTo() {
        return new Date(toMillis);
    }

    public boolean inRange(Date date) {
        long millis = date.getTime();
        return millis >= fromMillis && millis <= toMillis;
    }
}
