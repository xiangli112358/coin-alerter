package me.dev1001.coin.core.rules;

import me.dev1001.coin.core.PriceStore;
import me.dev1001.coin.core.Rule;
import me.dev1001.coin.entity.PricePoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author hongzong.li
 */
@Component("currentPriceAbove")
public class CurrentPriceAbove implements Rule {

    @Value("${rule.price.above}")
    private volatile BigDecimal above;

    @Autowired
    private PriceStore priceStore;

    private static final Logger logger = LoggerFactory.getLogger(CurrentPriceAbove.class);

    @PostConstruct
    public void init(){
        setAbove(above);
    }

    @Override
    public boolean hit() {
        PricePoint pricePoint = priceStore.getCurrentPrice();
        if (pricePoint == null) {
            return false;
        }
        return pricePoint.getPriceInfo().getLast().compareTo(above) > 0;
    }

    @Override
    public String desc() {
        return "Current price is above " + above;
    }

    public void setAbove(BigDecimal above) {
        checkArgument(above.compareTo(BigDecimal.ZERO) > 0, "above must greater than 0");
        logger.info("Price above value is set to "+ above);
        this.above = above;
    }

    @Override
    public String toString() {
        return String.format("CurrentPriceAbove{%f}", above);
    }
}
