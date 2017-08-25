package me.dev1001.coin.engine.rules;

import me.dev1001.coin.engine.Rule;
import me.dev1001.coin.entity.PriceInfo;
import me.dev1001.coin.service.PriceStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author hongzong.li
 */
@Component("currentPriceBelow")
public class CurrentPriceBelow implements Rule {
    private volatile BigDecimal below;

    @Autowired
    private PriceStore priceStore;

    public CurrentPriceBelow(BigDecimal below) {
        setBelow(below);
    }

    @Override
    public boolean hit() {
        PriceInfo priceInfo = priceStore.getCurrentPrice().getPriceInfo();
        return priceInfo.getLast().compareTo(below) < 0;
    }

    @Override
    public String desc() {
        return "Current price is blow " + below;
    }

    public void setBelow(BigDecimal below) {
        checkArgument(below.compareTo(BigDecimal.ZERO) > 0, "below must greater than 0");
        this.below = below;    }
}
