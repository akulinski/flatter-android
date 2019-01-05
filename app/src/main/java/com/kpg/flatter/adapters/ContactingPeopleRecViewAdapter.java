package com.kpg.flatter.adapters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kpg.flatter.R;
import com.squareup.picasso.Picasso;

import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ContactingPeopleRecViewAdapter extends RecyclerView.Adapter<ContactingPeopleRecViewAdapter.ViewHolder> {

    private LinkedList<String> list;
    private Context context;

    public ContactingPeopleRecViewAdapter(Context context, LinkedList<String> list){
        this.list = list;
        this.context = context;
    }

    @Override
    public ContactingPeopleRecViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mainview_recview_contact, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactingPeopleRecViewAdapter.ViewHolder viewHolder, int i) {
        Picasso.get()
                .load(list.get(i))
                .centerCrop()
                .resize(700,700)
                .into(viewHolder.photo);

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.profilePhoto) CircleImageView photo;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,itemView);
        }

    }
}
