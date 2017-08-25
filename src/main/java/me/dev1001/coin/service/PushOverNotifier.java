package me.dev1001.coin.service;

import me.dev1001.coin.core.Notifier;
import me.dev1001.coin.entity.Notification;
import net.pushover.client.PushoverClient;
import net.pushover.client.PushoverException;
import net.pushover.client.PushoverMessage;
import net.pushover.client.PushoverRestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by think on 8/25/2017.
 */
public class PushOverNotifier implements Notifier {
    private final PushoverClient client = new PushoverRestClient();

    private static final Logger logger = LoggerFactory.getLogger(PushOverNotifier.class);

    private final String userKey;

    private final String appId;

    public PushOverNotifier(String userKey, String appId){
        this.userKey = userKey;
        this.appId = appId;
    }

    @Override
    public void sendNotification(Notification notification) {
        PushoverMessage message = new PushoverMessage.Builder()
                .setApiToken(appId)
                .setUserId(userKey)
                .setTitle(notification.getTitle())
                .setMessage(notification.getMessage())
                .build();
        try {
            logger.info("Sending notification: {}", notification);
            client.pushMessage(message);
        } catch (PushoverException e) {
            logger.error("Send notification error, message: {}", notification, e);
        }
    }
}
