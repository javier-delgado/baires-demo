package com.bairesdemo.javier.bairesdemo.views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bairesdemo.javier.bairesdemo.R;
import com.bairesdemo.javier.bairesdemo.adapters.ProjectsAdapter;
import com.bairesdemo.javier.bairesdemo.models.Project;
import com.bairesdemo.javier.bairesdemo.network.ProjectsFetcher;
import com.bairesdemo.javier.bairesdemo.utils.ItemClickSupport;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GithubFragment  extends Fragment {
    @BindView(R.id.recProjects)  RecyclerView recProjects;

    public static GithubFragment newInstance() {
        Bundle args = new Bundle();

        GithubFragment fragment = new GithubFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_github, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
        EventBus.getDefault().post(new ProjectsFetcher.FetchProjects());
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onProjectsFetch(ProjectsFetcher.FetchSuccess event) {
        Log.d("debug","fetched!");
        setupList(event.projects);
    }

    private void setupList(final List<Project> projects) {
        recProjects.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recProjects.setAdapter(new ProjectsAdapter(projects));
        ItemClickSupport.addTo(recProjects).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                String url = projects.get(position).getHtmlUrl();
                Intent i = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(url));
                startActivity(i);
            }
        });
    }


}
