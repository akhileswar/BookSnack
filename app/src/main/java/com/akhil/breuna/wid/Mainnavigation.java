package com.akhil.breuna.wid;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.*;
import android.widget.*;


import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.client.core.view.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
public class Mainnavigation extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{
public TextView t1;
    public Firebase mref;
    public FirebaseAuth mAuth;

    protected DrawerLayout mDraw;
    public ActionBarDrawerToggle toggle;
    public NavigationView nView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainnavigation);
        setNavigationViewListner();
        Firebase.setAndroidContext(this);
        mref=new Firebase("https://divvvvvvv-208a2.firebaseio.com/");
        mAuth=FirebaseAuth.getInstance();

        mDraw=(DrawerLayout)findViewById(R.id.main_drawble_layout);
        toggle=new ActionBarDrawerToggle(this,mDraw,R.string.open,R.string.close);

        mDraw.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FirebaseUser user=mAuth.getCurrentUser();

     //
        NavigationView ano_draw=(NavigationView)findViewById(R.id.main_navigationView);
        View header=ano_draw.getHeaderView(0);

        t1=(TextView)header.findViewById(R.id.textView13);
        t1.setText(user.getEmail());

//
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {

            case R.id.home: {
                startActivity(new Intent(this,Intermediate.class));
                break;
            }
            case R.id.previous_bookings: {
                startActivity(new Intent(this,previous_bookings.class));
               // Toast.makeText(this,"previous bookings",Toast.LENGTH_LONG).show();
                break;
            }

            case R.id.feedback: {
                startActivity(new Intent(this,Feedback.class));
             //   Toast.makeText(this,"feedback",Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.about: {
                startActivity(new Intent(this,About.class));
       //         Toast.makeText(this,"feedback",Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.signout: {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this,login.class));
                finish();
                Toast.makeText(this,"sign-out success",Toast.LENGTH_LONG).show();
                break;
            }
        }
        //close navigation drawer
        mDraw.closeDrawer(GravityCompat.START);
        return true;
    }
    private void setNavigationViewListner() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.main_navigationView);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
