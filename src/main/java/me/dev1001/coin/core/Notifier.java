package me.dev1001.coin.core;

import me.dev1001.coin.entity.Notification;

/**
 * Created by think on 8/25/2017.
 */
public interface Notifier {
    void sendNotification(Notification notification);
}
