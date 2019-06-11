package com.kpg.flatter.fragments.mainview;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kpg.flatter.R;
import com.kpg.flatter.adapters.ContactingPeopleRecViewAdapter;
import com.kpg.flatter.adapters.MatchesRecViewAdapter;
import com.kpg.flatter.mock.PhotoListMock;
import com.squareup.picasso.Picasso;

import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {

    @BindView(R.id.textTop) TextView title;
    @BindView(R.id.best_flat)
    ImageView bestFlat;
    @BindView(R.id.match_first) ImageView matchFirst;
    @BindView(R.id.match_second) ImageView matchSecond;
    @BindView(R.id.bottomButton)
    Button addOfferButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view  = inflater.inflate(R.layout.mainview_home_fragment,container,false);
        ButterKnife.bind(this,view);
        setUpView();
        return view;

    }

    private void setUpView(){

        title.setText(R.string.home);
        addOfferButton.setText("Add offer");
        Picasso.get().load(getResources().getString(R.string.fakeFlat)).fit().centerCrop().into(bestFlat);
        Picasso.get().load(getResources().getString(R.string.fakeFlat2)).fit().centerCrop().into(matchFirst);
        Picasso.get().load(getResources().getString(R.string.fakeFlat3)).fit().centerCrop().into(matchSecond);

    }

}
