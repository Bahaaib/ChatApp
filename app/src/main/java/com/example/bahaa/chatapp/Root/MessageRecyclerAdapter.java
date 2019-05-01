package com.example.bahaa.chatapp.Root;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bahaa.chatapp.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class MessageRecyclerAdapter extends RecyclerView.Adapter {

    private ArrayList<MessageModel> adapterModel;
    private Context context;

    {
        adapterModel = new ArrayList<>();
    }


    public MessageRecyclerAdapter(Context context, ArrayList<MessageModel> adapterModel) {
        this.context = context;
        this.adapterModel = adapterModel;
    }

    //Here We tell the RecyclerView what to show at each element of it..it'd be a cardView!
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.group_card, parent, false);

        return new MessageRecyclerAdapter.MessageViewHolder(view);
    }

    //Here We tell the RecyclerView what to show at each CardView..
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MessageRecyclerAdapter.MessageViewHolder) holder).BindView(position);

    }

    @Override
    public int getItemCount() {
        return adapterModel.size();
    }


    //Here we bind all the children views of each cardView with their corresponding
    // actions to show & interact with them
    public class MessageViewHolder extends RecyclerView.ViewHolder {


        public MessageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }


        //Here where all the glory being made..!
        public void BindView(final int position) {

        }


    }
}
