package me.dev1001.coin.core;

import me.dev1001.coin.entity.Notification;
import me.dev1001.coin.entity.PriceInfo;
import me.dev1001.coin.entity.PricePoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by think on 8/25/2017.
 */
@Component
public class AlertEngine {

    private static final ScheduledExecutorService checkAlertScheduler = Executors.newSingleThreadScheduledExecutor();
    private static final ScheduledExecutorService fetchPriceScheduler = Executors.newSingleThreadScheduledExecutor();

    @Autowired
    private PriceFetcher priceFetcher;

    @Autowired
    private PriceStore priceStore;

    @Autowired
    private Notifier notifier;

    @Autowired
    private List<Rule> rules;

    @Value("${period.alert}")
    private volatile int alertPeriod;

    @Value("${period.fetch}")
    private volatile int fetchPeriod;

    private volatile boolean on = true;

    private static final Logger logger = LoggerFactory.getLogger(AlertEngine.class);

    @PostConstruct
    public void init() {
        setAlertPeriod(alertPeriod);
        setFetchPeriod(alertPeriod);

        logger.info("Loading alert rules...");
        rules.forEach(rule -> logger.info("Alert rule " + rule + " loaded"));

        checkAlertScheduler.scheduleAtFixedRate(this::checkAlerts, alertPeriod, alertPeriod, TimeUnit.SECONDS);
        fetchPriceScheduler.scheduleAtFixedRate(this::fetchPrice, fetchPeriod, fetchPeriod, TimeUnit.SECONDS);
    }

    private void checkAlerts() {
        if (on) {
            List<Rule> hitRules = rules.stream().filter(Rule::hit).collect(Collectors.toList());
            logger.info("Check alerts hit rules: {}", hitRules);

            hitRules.stream().map(this::buildNotification)
                    .collect(Collectors.toList())
                    .forEach(notification -> notifier.sendNotification(notification));
        }
    }

    private void fetchPrice() {
        PriceInfo actPrice = priceFetcher.fetchPrice("act");
        priceStore.add(new PricePoint(new Date(), actPrice));
    }

    private Notification buildNotification(Rule rule) {
        Notification notification = new Notification();
        notification.setTitle("Coin price alert");
        notification.setMessage(rule.desc());
        return notification;
    }

    @PreDestroy
    public void shutdown() {
        checkAlertScheduler.shutdown();
        fetchPriceScheduler.shutdown();
    }

    public void setAlertPeriod(int period) {
        checkArgument(alertPeriod > 0, "alert period must greater than 0");
        this.alertPeriod = period;
    }

    public void setFetchPeriod(int period) {
        checkArgument(fetchPeriod > 0, "fetch period must greater than 0");
        this.fetchPeriod = period;
    }

    public int getAlertPeriod() {
        return alertPeriod;
    }

    public int getFetchPeriod() {
        return fetchPeriod;
    }

    public void turnOff() {
        this.on = false;
    }

    public void turnOn() {
        this.on = true;
    }

    public List<Rule> getAllRules() {
        return rules;
    }
}
