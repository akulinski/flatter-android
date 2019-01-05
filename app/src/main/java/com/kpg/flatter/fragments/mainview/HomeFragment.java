package com.kpg.flatter.fragments.mainview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kpg.flatter.R;
import com.kpg.flatter.adapters.ContactingPeopleRecViewAdapter;
import com.kpg.flatter.adapters.MatchesRecViewAdapter;
import com.kpg.flatter.mock.PhotoListMock;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {

    @BindView(R.id.people_contacting) RecyclerView peopleRecyclerView;
    @BindView(R.id.matchRecView) RecyclerView matchesRecyclerView;
    @BindView(R.id.best_flat) ImageView bestFlat;
    @BindView(R.id.textTop) TextView title;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view  = inflater.inflate(R.layout.mainview_home_fragment,container,false);
        ButterKnife.bind(this,view);
        setUpView();
        return view;

    }

    private void setUpView(){

        title.setText(getResources().getString(R.string.app_name));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        ContactingPeopleRecViewAdapter contactingPeopleRecViewAdapter = new ContactingPeopleRecViewAdapter(getContext(),PhotoListMock.createMessagesList());
        peopleRecyclerView.setLayoutManager(layoutManager);
        peopleRecyclerView.setAdapter(contactingPeopleRecViewAdapter);

        RecyclerView.LayoutManager matchesLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        MatchesRecViewAdapter matchesRecViewAdapter = new MatchesRecViewAdapter(getContext(),PhotoListMock.createMatchesList());
        matchesRecyclerView.setLayoutManager(matchesLayoutManager);
        matchesRecyclerView.setAdapter(matchesRecViewAdapter);

        Picasso.get().load(getResources().getString(R.string.fakeFlat)).fit().centerCrop().into(bestFlat);

    }

}
