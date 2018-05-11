package com.bairesdemo.javier.bairesdemo.network;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by javier on 6/9/17.
 */

public abstract class NetworkExchanger<T> {
    protected abstract void onResponseSuccessful(Response<T> response);
    protected abstract void onNetworkError();
    protected abstract void onResponseUnsuccessful(Response<T> response, int code);

    protected void initHttpRequest(Call<T> call) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful()) {
                    onResponseSuccessful(response);
                } else {
                    onResponseUnsuccessful(response, response.code());
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                t.printStackTrace();
                onNetworkError();
            }
        });
    }


    protected EventBus getBus() {
        return EventBus.getDefault();
    }

    protected Api getApi() {
        return RestClient.getInstance();
    }

}
