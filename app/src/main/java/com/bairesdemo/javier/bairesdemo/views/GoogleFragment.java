package com.bairesdemo.javier.bairesdemo.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.bairesdemo.javier.bairesdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoogleFragment extends Fragment {
    @BindView(R.id.webView) WebView mWebview;

    public static GoogleFragment newInstance() {

        Bundle args = new Bundle();

        GoogleFragment fragment = new GoogleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_google, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mWebview .loadUrl("http://www.google.com");
    }
}
