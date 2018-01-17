package com.akhil.breuna.wid;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
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
import com.google.firebase.auth.FirebaseUser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class previous_bookings extends Mainnavigation {
    public Firebase mref;
    public FirebaseAuth mAuth;
    public ListView list;
    int i;String today_dte;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_previous_bookings);

        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_previous_bookings, null, false);
        mDraw.addView(contentView, 0);
        list=(ListView)findViewById(R.id.listt);

        Firebase.setAndroidContext(this);
        mAuth = FirebaseAuth.getInstance();
        mref=new Firebase("https://divvvvvvv-208a2.firebaseio.com/");

        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        final String date = df.format(Calendar.getInstance().getTime());
FirebaseUser user=mAuth.getCurrentUser();

        final ArrayList<String> ar=new ArrayList<String>();
        final ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_activated_1,ar);



        i=0;
mref.child("previous").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds: dataSnapshot.getChildren()){
            i++;
            String date=ds.child("date_time").getValue().toString();
            String item=ds.child("item").getValue().toString();
            String item_num=ds.child("item_num").getValue().toString();
            String drink=ds.child("drink").getValue().toString();
if(drink.equals("none")) {

    ar.add("~" + date + "\n" + "      " + item + "  -  " + item_num);
}else{

    ar.add("~" + date + "\n" + "      " + drink+"/"+item + "  -  " + item_num);
}

        }

        if(i==0){
            ar.add("No Bookings yet");

        }

        list.setAdapter(adapter);

    }

    @Override
    public void onCancelled(FirebaseError firebaseError) {

    }
});

    }
}
