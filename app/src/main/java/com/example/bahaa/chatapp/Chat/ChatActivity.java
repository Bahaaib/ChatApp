package com.example.bahaa.chatapp.Chat;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;

import com.example.bahaa.chatapp.R;
import com.example.bahaa.chatapp.Root.MessageModel;
import com.example.bahaa.chatapp.Root.MessageRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    @BindView(R.id.message_box)
    EditText messageBox;
    @BindView(R.id.messages_rv)
    RecyclerView recyclerView;

    private String dbType;
    private String dbTarget;

    //Firebase DB
    private FirebaseDatabase database;
    private DatabaseReference mRef;

    //Firebase
    private FirebaseAuth mAuth;



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
        //Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        messagesList = new ArrayList<>();

        dbType = getIntent().getStringExtra("db_type");
        dbTarget = getIntent().getStringExtra("db_target");

        database = FirebaseDatabase.getInstance();
        mRef = database.getReference().child(dbType).child(dbTarget);

        callDatabase();

        adapter = new MessageRecyclerAdapter(this, messagesList);

        recyclerView.setAdapter(adapter);

        linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);



    }

    private void callDatabase() {
        mRef.addValueEventListener(new ValueEventListener() {
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
    public void sendMessage() {
        final String messageBody = messageBox.getText().toString();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        MessageModel message = new MessageModel();
        message.setMessageSenderName(currentUser.getEmail());
        message.setMessageBody(messageBody);

        mRef.push().setValue(message);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Free up memory from views
        unbinder.unbind();
    }
}
