package me.dev1001.coin.service;

import me.dev1001.coin.entity.Notification;
import org.springframework.stereotype.Service;

/**
 * Created by think on 8/25/2017.
 */
@Service
public class PushOverNotifyService implements NotifyService {
    private PushoverClient client = new PushoverRestClient();

    @Override
    public void sendNotification(Notification notification) {
    }
}
