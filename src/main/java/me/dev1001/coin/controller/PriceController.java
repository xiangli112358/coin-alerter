package me.dev1001.coin.controller;

import me.dev1001.coin.core.AlertEngine;
import me.dev1001.coin.core.PriceStore;
import me.dev1001.coin.entity.PricePoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by think on 8/25/2017.
 */
@RestController
@RequestMapping("/price")
public class PriceController {
    @Autowired
    private PriceStore priceStore;

    @Autowired
    private AlertEngine alertEngine;

    @RequestMapping("/current")
    public PricePoint currentPrice() {
        return priceStore.getCurrentPrice();
    }

    @RequestMapping("/dump")
    public Map<Long, PricePoint> dump() {
        return priceStore.dump();
    }
}
