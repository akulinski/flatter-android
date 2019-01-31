package com.kpg.flatter.fragments.mainview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kpg.flatter.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchFragment extends Fragment {

    @BindView(R.id.textTop) TextView title;
    @BindView(R.id.bottomButton) Button showMoreButton;

    @BindView(R.id.best_flat_first) ImageView firstFlat;
    @BindView(R.id.best_flat_second) ImageView secondFlat;
    @BindView(R.id.best_flat_third) ImageView thirdFlat;

    @BindView(R.id.street_first) TextView firstStreet;
    @BindView(R.id.street_second) TextView secondStreet;
    @BindView(R.id.street_third) TextView thirdStreet;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.mainview_search_fragment,container,false);
        ButterKnife.bind(this,view);
        title.setText("Top apartments for you");
        showMoreButton.setText("Show more");
        mockView();
        return view;
    }

    private void mockView(){
        Picasso.get().load(getResources().getString(R.string.fakeFlat)).fit().centerCrop().into(firstFlat);
        Picasso.get().load(getResources().getString(R.string.fakeFlat2)).fit().centerCrop().into(secondFlat);
        Picasso.get().load(getResources().getString(R.string.fakeFlat3)).fit().centerCrop().into(thirdFlat);


    }

}
