package me.dev1001.coin.controller;

import me.dev1001.coin.core.AlertEngine;
import me.dev1001.coin.core.Rule;
import me.dev1001.coin.core.rules.CurrentPriceAbove;
import me.dev1001.coin.core.rules.CurrentPriceBelow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by think on 8/25/2017.
 */
@RestController
@RequestMapping("/rule")
public class RuleController {

    @Autowired
    private CurrentPriceAbove currentPriceAbove;

    @Autowired
    private CurrentPriceBelow currentPriceBelow;

    @Autowired
    private AlertEngine alertEngine;

    @RequestMapping("/all")
    public String getAllRules() {
        return alertEngine.getAllRules().toString();
    }

    @RequestMapping("/below")
    public CurrentPriceBelow getBelowRule() {
        return currentPriceBelow;
    }

    @RequestMapping("/above")
    public CurrentPriceAbove getAboveRule() {
        return  currentPriceAbove;
    }

    @RequestMapping(value = "/set/below", method = RequestMethod.POST)
    public String setBelow(@RequestParam("value")BigDecimal value) {
        currentPriceBelow.setBelow(value);
        return "Current price below rule is set to: " + currentPriceBelow;
    }

    @RequestMapping(value = "/set/above", method = RequestMethod.POST)
    public String setAbove(@RequestParam("value")BigDecimal value) {
        currentPriceAbove.setAbove(value);
        return "Current price above rule is set to: " + currentPriceAbove;
    }
}
