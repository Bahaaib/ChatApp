package com.example.bahaa.chatapp.Chat;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.bahaa.chatapp.R;
import com.example.bahaa.chatapp.Root.MessageModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
