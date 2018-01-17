package com.akhil.breuna.wid;


import android.content.Intent;
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

public class navigation_headerr extends AppCompatActivity {
    TextView user_gmaill;
    public Firebase mref;
    public FirebaseAuth mAuth;
    public Firebase.AuthStateListener mAuthstate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_headerr);

        Firebase.setAndroidContext(this);
        mAuth = FirebaseAuth.getInstance();
        mref=new Firebase("https://divvvvvvv-208a2.firebaseio.com/");

    //    user_gmaill=(TextView)findViewById(R.id.textView13);
        FirebaseUser user=mAuth.getCurrentUser();

      //  user_gmaill.setText(user.getEmail());
    }
}
