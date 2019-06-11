package com.kpg.flatter.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kpg.flatter.R;
import com.squareup.picasso.Picasso;

import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MatchesRecViewAdapter extends RecyclerView.Adapter<MatchesRecViewAdapter.ViewHolder> {

    private LinkedList<String> list;
    private Context context;

    public MatchesRecViewAdapter(Context context, LinkedList<String> list){
        this.list = list;
        this.context = context;
    }

    @Override
    public MatchesRecViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mainview_recview_match, viewGroup, false);
        return new MatchesRecViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MatchesRecViewAdapter.ViewHolder viewHolder, int i) {
        Picasso.get()
                .load(list.get(i))
                .centerCrop()
                .fit()
                .into(viewHolder.photo);

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.match)
        ImageView photo;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,itemView);
        }

    }
}
