package com.akhil.breuna.wid;


import android.app.ProgressDialog;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Intermediate extends AppCompatActivity {
public ProgressDialog p;
    public Firebase mref;
    public FirebaseAuth mAuth;
    public Firebase.AuthStateListener mAuthstate;
    int i;int l;int book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_mediate);


    }



    @Override
    protected void onStart(){

        super.onStart();
p=new ProgressDialog(this);
        p.setMessage("Loading");
        p.show();
        Firebase.setAndroidContext(this);
        mAuth = FirebaseAuth.getInstance();
        mref=new Firebase("https://divvvvvvv-208a2.firebaseio.com/");
       final FirebaseUser user=mAuth.getCurrentUser();
        final  String user_email=user.getEmail();
        i=0;
//
        try{
        String string1 = "17:00:00";
        Date time1 = new SimpleDateFormat("HH:mm:ss").parse(string1);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(time1);

        String string2 = "23:00:00";
        Date time2 = new SimpleDateFormat("HH:mm:ss").parse(string2);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(time2);
        calendar2.add(Calendar.DATE, 1);

            String string3 = "17:30:00";
            Date time3 = new SimpleDateFormat("HH:mm:ss").parse(string2);
            Calendar calendar3 = Calendar.getInstance();
            calendar3.setTime(time2);
            calendar3.add(Calendar.DATE, 1);

            String string4 = "23:59:00";
            Date time4 = new SimpleDateFormat("HH:mm:ss").parse(string2);
            Calendar calendar4 = Calendar.getInstance();
            calendar4.setTime(time2);
            calendar4.add(Calendar.DATE, 1);

            DateFormat df = new SimpleDateFormat("HH:mm:ss");
           String present_date = df.format(Calendar.getInstance().getTime());
           // Toast.makeText(Intermediate.this, present_date, Toast.LENGTH_SHORT).show();
          //  String present_date="14:30:00";
        Date d = new SimpleDateFormat("HH:mm:ss").parse(present_date);
        Calendar calendar5 = Calendar.getInstance();
        calendar5.setTime(d);
        calendar5.add(Calendar.DATE, 1);

        Date x = calendar5.getTime();
        if ((x.after(calendar1.getTime()) && x.before(calendar2.getTime()))) {
            //checkes whether the current time is between 14:49:00 and 20:11:13.
            //check ifsnack exist or not
            l=0;
            mref.child("snackdetails").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot ds : dataSnapshot.getChildren()){
                    //    final String date = ds.child("date").getValue(String.class);
                        final String item = ds.child("item").getValue(String.class);
                     //
                        //  final String drink = ds.child("drink").getValue(String.class);

if(item.equals("none")){
l++;
startActivity(new Intent(Intermediate.this,No_tmmro_snack.class));

}else{
//////
    mref.child("booked").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if(dataSnapshot.exists()) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String gmail = ds.child("gmail").getValue(String.class);
                    String item = ds.child("item").getValue(String.class);
                    String num = ds.child("item_num").getValue(String.class);
                    //  Toast.makeText(Intermediate.this, gmail, Toast.LENGTH_SHORT).show();
                    if (gmail.equals(user_email)) {
                        i++;

                    }
                    if (i == 0) {
                        p.hide();
                        startActivity(new Intent(Intermediate.this, Notbooked.class));
                    } else {
                        p.hide();
                        startActivity(new Intent(Intermediate.this, booked.class));

                    }

                }
            }else {
                p.hide();
                //   Toast.makeText(Intermediate.this, "null", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Intermediate.this, Notbooked.class));
            }

        }

        @Override
        public void onCancelled(FirebaseError firebaseError) {

        }
    });
    //////
}//else

                    }//for


                }//ref

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            //
/*
            mref.child("booked").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            String gmail = ds.child("gmail").getValue(String.class);
                            String item = ds.child("item").getValue(String.class);
                            String num = ds.child("item_num").getValue(String.class);
                            //  Toast.makeText(Intermediate.this, gmail, Toast.LENGTH_SHORT).show();
                            if (gmail.equals(user_email)) {
                                i++;

                            }
                            if (i == 0) {
                                p.hide();
                                startActivity(new Intent(Intermediate.this, Notbooked.class));
                            } else {
                                p.hide();
                                startActivity(new Intent(Intermediate.this, booked.class));

                            }

                        }
                    }else {
                        p.hide();
                        //   Toast.makeText(Intermediate.this, "null", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Intermediate.this, Notbooked.class));
                    }

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
//
*/
        }else{

            book=0;
            mref.child("booked").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){
                        for(DataSnapshot ds: dataSnapshot.getChildren()){
                            book++;

                        }

                        if(book<2){startActivity(new Intent(Intermediate.this,Less_book.class));}else{startActivity(new Intent(Intermediate.this,Time_out.class));}

                    }else
                        startActivity(new Intent(Intermediate.this,Less_book.class));


                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

           // startActivity(new Intent(this,Time_out.class));
        }


        } catch (ParseException e) {
        e.printStackTrace();

    }//try
        //
        /*
        mref.child("booked").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        String gmail = ds.child("gmail").getValue(String.class);
                        String item = ds.child("item").getValue(String.class);
                        String num = ds.child("item_num").getValue(String.class);
                      //  Toast.makeText(Intermediate.this, gmail, Toast.LENGTH_SHORT).show();
                        if (gmail.equals(user_email)) {
                            i++;

                        }
                        if (i == 0) {
                            p.hide();
                            startActivity(new Intent(Intermediate.this, Notbooked.class));
                        } else {
                            p.hide();
                            startActivity(new Intent(Intermediate.this, booked.class));

                        }

                    }
                }else {
                    p.hide();
                 //   Toast.makeText(Intermediate.this, "null", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Intermediate.this, Notbooked.class));
                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
//
        */
    }
}
