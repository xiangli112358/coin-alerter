package me.dev1001.coin.engine.rules;

import me.dev1001.coin.engine.Rule;
import me.dev1001.coin.entity.PriceInfo;
import me.dev1001.coin.service.PriceStoreFactory;

import java.math.BigDecimal;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author hongzong.li
 */
public class CurrentPriceBelow implements Rule {
    private final BigDecimal below;

    public CurrentPriceBelow(BigDecimal below) {
        checkArgument(below.compareTo(BigDecimal.ZERO) > 0, "below must greater than 0");
        this.below = below;
    }

    @Override
    public boolean hit() {
        PriceInfo priceInfo = PriceStoreFactory.getPriceStore().getCurrentPrice().getPriceInfo();
        return priceInfo.getLast().compareTo(below) < 0;
    }

    @Override
    public String desc() {
        return "Current price is blow " + below;
    }
}
