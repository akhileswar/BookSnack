package com.akhil.breuna.wid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.*;
import android.widget.*;


import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class No_tmmro_snack extends Mainnavigation{
TextView t1;
    public Firebase mref;
    public FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_no_tmmro_snack);

        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_no_tmmro_snack, null, false);
        mDraw.addView(contentView, 0);


        Firebase.setAndroidContext(this);
        mAuth = FirebaseAuth.getInstance();
        mref=new Firebase("https://divvvvvvv-208a2.firebaseio.com/");
        t1=(TextView)findViewById(R.id.textView11);

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        final String present_date = df.format(Calendar.getInstance().getTime());

        mref.child("snackdetails").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                        final String date = ds.child("date").getValue(String.class);
                   // final String item = ds.child("item").getValue(String.class);
                    //   final String drink = ds.child("drink").getValue(String.class);

                    if(date.equals(present_date)){
t1.setText("Today");

                    }else
                        t1.setText("Tomorrow");
                }


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }
}
