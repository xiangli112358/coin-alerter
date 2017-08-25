package me.dev1001.coin.core.rules;

import me.dev1001.coin.core.Rule;
import me.dev1001.coin.entity.PriceInfo;
import me.dev1001.coin.core.PriceStore;
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
@Component("currentPriceBelow")
public class CurrentPriceBelow implements Rule {

    @Value("${rule.price.below}")
    private volatile BigDecimal below;

    @Autowired
    private PriceStore priceStore;

    private static final Logger logger = LoggerFactory.getLogger(CurrentPriceBelow.class);


    @PostConstruct
    public void init() {
        setBelow(below);
    }

    @Override
    public boolean hit() {
        PriceInfo priceInfo = priceStore.getCurrentPrice().getPriceInfo();
        if (priceInfo == null) {
            return false;
        }
        return priceInfo.getLast().compareTo(below) < 0;
    }

    @Override
    public String desc() {
        return "Current price is blow " + below;
    }

    public void setBelow(BigDecimal below) {
        checkArgument(below.compareTo(BigDecimal.ZERO) > 0, "below must greater than 0");
        logger.info("Price blow is set to " + below);
        this.below = below;
    }

    @Override
    public String toString() {
        return String.format("CurrentPriceBelow{%f}", below);
    }
}
