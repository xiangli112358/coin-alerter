package me.dev1001.coin.controller;

import me.dev1001.coin.core.AlertEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by think on 8/25/2017.
 */
@RestController
@RequestMapping("/alert")
public class AlertController {
    @Autowired
    private AlertEngine alertEngine;

    @RequestMapping("/on")
    public String turnOn() {
        alertEngine.turnOn();
        return "Alert engine turned on";
    }

    @RequestMapping("/off")
    public String turnOff() {
        alertEngine.turnOff();
        return "Alert engine turned off";
    }
}
