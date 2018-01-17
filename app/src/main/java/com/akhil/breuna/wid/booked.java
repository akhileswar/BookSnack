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
import java.util.Calendar;

class booked extends Mainnavigation{
    TextView t1,t2,my_bookings,user_gmaill;
    Button booksnack;
    public Firebase mref;
    public FirebaseAuth mAuth;
    public Firebase.AuthStateListener mAuthstate;

    protected DrawerLayout mdraw;
    public ActionBarDrawerToggle toggle;
public NavigationView mNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
     //   super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_booked);
    //    setNavigationViewListner();
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_booked, null, false);
        mDraw.addView(contentView, 0);
        Firebase.setAndroidContext(this);
        mAuth = FirebaseAuth.getInstance();
        mref=new Firebase("https://divvvvvvv-208a2.firebaseio.com/");

        t1=(TextView)findViewById(R.id.textView4);

        t2=(TextView)findViewById(R.id.textView7);
        my_bookings=(TextView)findViewById(R.id.textView8);
        booksnack=(Button)findViewById(R.id.button3);
/*
        mdraw=(DrawerLayout)findViewById(R.id.drawble_layout);
        toggle=new ActionBarDrawerToggle(this,mdraw,R.string.open,R.string.close);

        mdraw.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

*/
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        final String present_date = df.format(Calendar.getInstance().getTime());
FirebaseUser u=mAuth.getCurrentUser();
        final Intent i=new Intent(booked.this,Edit_Snack.class);
        mref.child("snackdetails").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    final String date = ds.child("date").getValue(String.class);
                    final String item = ds.child("item").getValue(String.class);
                    final String drink = ds.child("drink").getValue(String.class);



                    if(present_date.equals(date)){
                        t1.setText("Today("+date+") - Snack");
                        t2.setText(item+" - "+drink);
                        i.putExtra("item",item);
                        i.putExtra("drink",drink);
                        i.putExtra("date",date);
                        i.putExtra("day","Today");
                    }else{
                        t1.setText("Tmmro("+date+") - Snack");
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
        FirebaseUser userr=mAuth.getCurrentUser();
        final String user_gmail=userr.getEmail();
        mref.child("booked").child(userr.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        String gmail = ds.child("gmail").getValue(String.class);


                        if (gmail.equals(user_gmail)) {
                            //Toast.makeText(booked.this, gmail, Toast.LENGTH_SHORT).show();
                            String item = ds.child("item").getValue(String.class);
                            String num = ds.child("item_num").getValue(String.class);
                            String drink_name = ds.child("drink").getValue(String.class);
                            i.putExtra("number",num);
                            String combine=item+" - "+num;
                            if(drink_name.equals("none")){
                                my_bookings.setText(combine);
                            }else
                            my_bookings.setText(drink_name+"/"+combine);

                        }

                    }
                }else {

                  //  Toast.makeText(booked.this, "null", Toast.LENGTH_SHORT).show();
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
    /*
     @Override
     public boolean onNavigationItemSelected(@NonNull MenuItem item) {
         // Handle navigation view item clicks here.
         switch (item.getItemId()) {

             case R.id.home: {
                 startActivity(new Intent(this,booked.class));
                 break;
             }
             case R.id.previous_bookings: {
                 startActivity(new Intent(this,booked.class));
                 Toast.makeText(booked.this,"previous bookings",Toast.LENGTH_LONG).show();
                 break;
             }
             case R.id.feedback: {
                 startActivity(new Intent(this,booked.class));
                 Toast.makeText(booked.this,"feedback",Toast.LENGTH_LONG).show();
                 break;
             }
             case R.id.signout: {
                 FirebaseAuth.getInstance().signOut();
                 startActivity(new Intent(booked.this,login.class));
                 finish();
                 Toast.makeText(booked.this,"sign-out success",Toast.LENGTH_LONG).show();
                 break;
             }
         }
         //close navigation drawer
         mdraw.closeDrawer(GravityCompat.START);
         return true;
     }
     private void setNavigationViewListner() {
         NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
         navigationView.setNavigationItemSelectedListener(this);

     }

     @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
*/

}
