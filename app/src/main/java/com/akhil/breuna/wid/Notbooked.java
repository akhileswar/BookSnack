package com.akhil.breuna.wid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.*;
import android.widget.*;


import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.client.collection.LLRBNode;
import com.google.firebase.auth.FirebaseAuth;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Notbooked extends  Mainnavigation{
    TextView t1,t2;
    Button booksnack;
    public Firebase mref;
    public FirebaseAuth mAuth;
    public Firebase.AuthStateListener mAuthstate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    //super.onCreate(savedInstanceState);
 // setContentView(R.layout.notbooked);

        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.notbooked, null, false);
        mDraw.addView(contentView, 0);



        Firebase.setAndroidContext(this);
        mAuth = FirebaseAuth.getInstance();
        mref=new Firebase("https://divvvvvvv-208a2.firebaseio.com/");

        t1=(TextView)findViewById(R.id.textView4);

        t2=(TextView)findViewById(R.id.textView7);
        t1.setShadowLayer(1, 0, 0, Color.BLACK);
        booksnack=(Button)findViewById(R.id.button3);

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        final String present_date = df.format(Calendar.getInstance().getTime());
//Toast.makeText(Notbooked.this,present_date,Toast.LENGTH_LONG).show();
        final Intent i=new Intent(Notbooked.this,BookSnack.class);
        mref.child("snackdetails").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    final String date = ds.child("date").getValue(String.class);
                     final String item = ds.child("item").getValue(String.class);
                    final String drink = ds.child("drink").getValue(String.class);
                    if(present_date.equals(date)){
                        t1.setText("Today("+date+")-Snack");
                        t2.setText(item+" - "+drink);
                        i.putExtra("item",item);
                        i.putExtra("drink",drink);
                        i.putExtra("date",date);
                        i.putExtra("day","Today");
                    }else{
                        t1.setText("Tmmro("+date+")-Snack");
                        t2.setText(item+" - "+drink);
                        i.putExtra("item",item);
                        i.putExtra("drink",drink);
                        i.putExtra("date",date);
                        i.putExtra("day","Tmmro");
                    }
                }


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        booksnack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);
            }
        });

        //
    }
}
