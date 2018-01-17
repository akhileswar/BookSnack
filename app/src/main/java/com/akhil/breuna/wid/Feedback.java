package com.akhil.breuna.wid;

import android.content.Context;
import android.content.Intent;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.view.*;
import android.widget.*;


import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Feedback extends Mainnavigation{
    public EditText feed;
    public Button sent;
    public Firebase mref;
    public FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
     //   super.onCreate(savedInstanceState);
     // setContentView(R.layout.activity_feedback);
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_feedback, null, false);
        mDraw.addView(contentView, 0);


        Firebase.setAndroidContext(this);
        mAuth = FirebaseAuth.getInstance();
        mref=new Firebase("https://divvvvvvv-208a2.firebaseio.com/");

        feed=(EditText)findViewById(R.id.editText);
        sent=(Button)findViewById(R.id.button8);

      final  FirebaseUser user=mAuth.getCurrentUser();

        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
       final String date = df.format(Calendar.getInstance().getTime());

        sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String feed_back=feed.getText().toString();
                if(TextUtils.isEmpty(feed_back)) {
                    Toast.makeText(Feedback.this, "Fill Text", Toast.LENGTH_LONG).show();
                }else{

                    mref.child("feedback").child(date).child(user.getUid()).child("message").setValue(feed_back);
                    feed.setText(null);
                    Toast.makeText(Feedback.this, "Feedback sent", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
