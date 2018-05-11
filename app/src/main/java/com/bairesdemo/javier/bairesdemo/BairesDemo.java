package com.bairesdemo.javier.bairesdemo;

import android.app.Application;

import com.bairesdemo.javier.bairesdemo.network.NetworkEschangersSubscriber;
import com.bairesdemo.javier.bairesdemo.network.RestClient;

import org.greenrobot.eventbus.EventBus;

public class BairesDemo extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.builder().throwSubscriberException(false).installDefaultEventBus();
        RestClient.initializeRestClient(this);
        NetworkEschangersSubscriber.register();
    }
}
