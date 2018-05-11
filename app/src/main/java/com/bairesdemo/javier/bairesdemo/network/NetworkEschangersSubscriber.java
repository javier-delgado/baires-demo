package com.bairesdemo.javier.bairesdemo.network;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by javier on 2/9/17.
 */

public final class NetworkEschangersSubscriber {
    private final static Class[] sendersAndFetchers = new Class[] {
        ProjectsFetcher.class
    };

    public static void register(){
        for (Class senderOrFetcher : sendersAndFetchers) {
            try {
                EventBus.getDefault().register(senderOrFetcher.newInstance());
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
