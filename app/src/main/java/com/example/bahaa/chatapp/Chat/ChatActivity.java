package com.example.bahaa.chatapp.Chat;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.bahaa.chatapp.R;
import com.example.bahaa.chatapp.Root.MessageModel;
import com.example.bahaa.chatapp.Root.MessageRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ChatActivity extends AppCompatActivity {

    @BindView(R.id.send_fab)
    FloatingActionButton sendFab;

    private String dbType;
    private String dbTarget;

    //Firebase DB
    private FirebaseDatabase database;
    private DatabaseReference mRef;
    private DatabaseReference dbWriterRef;

    @BindView(R.id.messages_rv)
    RecyclerView recyclerView;

    private ArrayList<MessageModel> messagesList;
    private MessageRecyclerAdapter adapter;
    private LinearLayoutManager linearLayoutManager;

    //Butterknife
    private Unbinder unbinder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        //inject Views via Butterknife..
        unbinder = ButterKnife.bind(this);

        database = FirebaseDatabase.getInstance();
        mRef = database.getReference();

        callDatabase();

        adapter = new MessageRecyclerAdapter(this, messagesList);

        recyclerView.setAdapter(adapter);

        linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);



    }

    private void callDatabase() {
        mRef.child(GROUPS_DB).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Reset List of groups
                messagesList.clear();
                fetchData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void fetchData(DataSnapshot dataSnapshot) {
        for (DataSnapshot db : dataSnapshot.getChildren()) {
            MessageModel message = db.getValue(MessageModel.class);
            messagesList.add(message);


            Log.i("Statuss", message.getMessageSenderName());
        }

        adapter.notifyDataSetChanged();


    }


    @OnClick(R.id.send_fab)
    public void sendTestMessage() {
        MessageModel message = new MessageModel();

        message.setMessageSenderName("Ammar");
        message.setMessageBody("This is my first message");

        Toast.makeText(getApplicationContext(), "Message added",
                Toast.LENGTH_LONG).show();

        mRef.push().setValue(message);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Free up memory from views
        unbinder.unbind();
    }
}
