package com.example.bahaa.chatapp.Home;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bahaa.chatapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class ChatsFragment extends Fragment {
    @BindView(R.id.fab)
    FloatingActionButton fab;

    private Unbinder unbinder;


    public ChatsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_chats, container, false);

        //inject Views via Butterknife..
        unbinder = ButterKnife.bind(this, v);

        return v;
    }

    @OnClick(R.id.fab)
    public void addChat() {
        Toast.makeText(getActivity(), "This feature will be added later",
                Toast.LENGTH_LONG).show();
    }


}
