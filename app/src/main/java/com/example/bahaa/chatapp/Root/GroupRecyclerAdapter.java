package com.example.bahaa.chatapp.Root;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bahaa.chatapp.Chat.ChatActivity;
import com.example.bahaa.chatapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GroupRecyclerAdapter extends RecyclerView.Adapter {

    private ArrayList<String> adapterModel;
    private Context context;

    {
        adapterModel = new ArrayList<>();
    }


    public GroupRecyclerAdapter(Context context, ArrayList<String> adapterModel) {
        this.context = context;
        this.adapterModel = adapterModel;
    }

    //Here We tell the RecyclerView what to show at each element of it..it'd be a cardView!
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.group_card, parent, false);

        return new GroupRecyclerAdapter.GroupViewHolder(view);
    }

    //Here We tell the RecyclerView what to show at each CardView..
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((GroupRecyclerAdapter.GroupViewHolder) holder).BindView(position);

    }

    @Override
    public int getItemCount() {
        return adapterModel.size();
    }


    //Here we bind all the children views of each cardView with their corresponding
    // actions to show & interact with them
    public class GroupViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.group_title)
        TextView groupTitle;
        @BindView(R.id.group_card)
        CardView groupCard;

        public GroupViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }


        //Here where all the glory being made..!
        public void BindView(final int position) {
            groupTitle.setText(adapterModel.get(position));

            groupCard.setOnClickListener(v -> {
                Intent chatIntent = new Intent(context, ChatActivity.class);
                chatIntent.putExtra("db_type", "groups");
                chatIntent.putExtra("db_target", adapterModel.get(position));
                context.startActivity(chatIntent);
            });
        }


    }
}