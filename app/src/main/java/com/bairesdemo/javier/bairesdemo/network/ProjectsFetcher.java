package com.bairesdemo.javier.bairesdemo.network;

import com.bairesdemo.javier.bairesdemo.models.Project;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Response;

/**
 * Created by javier on 9/11/17.
 */

public class ProjectsFetcher extends NetworkExchanger<GithubResponse> {

    @Subscribe
    public void fetch(FetchProjects event) {
        initHttpRequest(getApi().getProjects("tetris+language:kotlin", "stars", "desc"));
    }

    @Override
    protected void onResponseSuccessful(Response<GithubResponse> response) {
        getBus().post(new FetchSuccess(response.body().items));
    }

    @Override
    protected void onNetworkError() {
        getBus().post(new FetchFail());
    }

    @Override
    protected void onResponseUnsuccessful(Response<GithubResponse> response, int code) {
        getBus().post(new FetchFail());
    }

    public static class FetchProjects {}
    public static class FetchSuccess {

        public final List<Project> projects;

        public FetchSuccess(List<Project> items) {
            this.projects = items;
        }
    }
    public static class FetchFail {}
}
