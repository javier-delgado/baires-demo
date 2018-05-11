package com.bairesdemo.javier.bairesdemo.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bairesdemo.javier.bairesdemo.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ButtonsFragment extends Fragment {
    public static ButtonsFragment newInstance() {
        Bundle args = new Bundle();

        ButtonsFragment fragment = new ButtonsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_buttons, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @OnClick(R.id.btnToast)
    public void onToast() {
        Toast.makeText(getContext(), "Toast", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btnAlert)
    public void onAlert() {
        new MaterialDialog.Builder(getContext())
                .content("Alert")
                .positiveText("OK")
                .show();
    }
}
