package me.dev1001.coin.engine.rules;

import me.dev1001.coin.engine.Rule;
import me.dev1001.coin.entity.PricePoint;
import me.dev1001.coin.service.PriceStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author hongzong.li
 */
@Component("currentPriceAbove")
public class CurrentPriceAbove implements Rule {
    private volatile BigDecimal above;

    @Autowired
    private PriceStore priceStore;

    public CurrentPriceAbove(BigDecimal above) {
        setAbove(above);
    }

    @Override
    public boolean hit() {
        PricePoint pricePoint = priceStore.getCurrentPrice();
        return pricePoint.getPriceInfo().getLast().compareTo(above) > 0;
    }

    @Override
    public String desc() {
        return "Current price is above " + above;
    }

    public void setAbove(BigDecimal above) {
        checkArgument(above.compareTo(BigDecimal.ZERO) > 0, "above must greater than 0");
        this.above = above;
    }
}
