package me.dev1001.coin.engine.rules;

import me.dev1001.coin.engine.Rule;
import me.dev1001.coin.entity.PricePoint;
import me.dev1001.coin.service.PriceStoreFactory;

import java.math.BigDecimal;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author hongzong.li
 */
public class CurrentPriceAbove implements Rule {
    private final BigDecimal above;

    public CurrentPriceAbove(BigDecimal above) {
        checkArgument(above.compareTo(BigDecimal.ZERO) > 0, "above must greater than 0");
        this.above = above;
    }

    @Override
    public boolean hit() {
        PricePoint pricePoint = PriceStoreFactory.getPriceStore().getCurrentPrice();
        return pricePoint.getPriceInfo().getLast().compareTo(above) > 0;
    }

    @Override
    public String desc() {
        return "Current price is above " + above;
    }
}
