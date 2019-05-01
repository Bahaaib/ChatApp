package com.example.bahaa.chatapp.Home;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bahaa.chatapp.R;
import com.example.bahaa.chatapp.Root.GroupRecyclerAdapter;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class GroupsFragment extends Fragment {

    private final String GROUPS_DB = "groups";


    @BindView(R.id.groups_rv)
    RecyclerView recyclerView;
    //Firebase DB
    private FirebaseDatabase database;
    private DatabaseReference mRef;
    private ArrayList<String> groupsList;
    private GroupRecyclerAdapter adapter;
    private LinearLayoutManager linearLayoutManager;

    private Unbinder unbinder;


    public GroupsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_groups, container, false);

        //inject Views via Butterknife..
        unbinder = ButterKnife.bind(this, v);

        FirebaseApp.initializeApp(getActivity());
        database = FirebaseDatabase.getInstance();
        mRef = database.getReference();

        groupsList = new ArrayList<>();

        callDatabase();

        adapter = new GroupRecyclerAdapter(this.getActivity(), groupsList);

        recyclerView.setAdapter(adapter);

        linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(linearLayoutManager);


        return v;
    }

    private void callDatabase() {
        mRef.child(GROUPS_DB).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Reset List of groups
                groupsList.clear();
                fetchData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void fetchData(DataSnapshot dataSnapshot) {
        for (DataSnapshot db : dataSnapshot.getChildren()) {
            groupsList.add(db.getKey());


            Log.i("Statuss", db.getKey());
        }

        adapter.notifyDataSetChanged();


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //Free up memory from views
        unbinder.unbind();
    }
}
