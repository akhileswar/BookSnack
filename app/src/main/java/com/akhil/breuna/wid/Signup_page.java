package com.akhil.breuna.wid;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.*;
import android.widget.*;
import android.text.*;


import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class Signup_page extends Activity {
    public Button signup_btn;
    public EditText emails;
    public EditText passwords,passwords_con,mpins;
    public FirebaseAuth mAuth;
    public Firebase mref;
    public Firebase.AuthStateListener mAuthstate;
    public ProgressDialog pro;
    public int k=0;
    public TextView about;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_signup);

        Firebase.setAndroidContext(this);
        mAuth = FirebaseAuth.getInstance();
        mref=new Firebase("https://divvvvvvv-208a2.firebaseio.com/");

        about=(TextView)findViewById(R.id.textView6);
        signup_btn = (Button) findViewById(R.id.button5);
        emails = (EditText) findViewById(R.id.editText1);
        mpins = (EditText) findViewById(R.id.editText2);
        passwords = (EditText) findViewById(R.id.editText3);
        passwords_con = (EditText) findViewById(R.id.editText4);

        pro = new ProgressDialog(this);

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Signup_page.this, "about", Toast.LENGTH_LONG).show();
            }
        });


        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final  String gmail=emails.getText().toString();
                final  String password=passwords.getText().toString();
                final  String password2=passwords_con.getText().toString();
                final String mpin=mpins.getText().toString();

                int len=password.length();

                if (TextUtils.isEmpty(gmail) || TextUtils.isEmpty(password) || TextUtils.isEmpty(password2)|| TextUtils.isEmpty(mpin)) {

                    Toast.makeText(Signup_page.this, "PLEASE FILL ALL THE FIELDS... ", Toast.LENGTH_LONG).show();

                } else {
                    if(len>=6) {
                        if (password.equals(password2)){
                            pro.setMessage("Loading....");
                            pro.show();
                            if(gmail.contains("iiitkottayam.ac.in")) {


                                mAuth.createUserWithEmailAndPassword(gmail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {

                                        if (task.isSuccessful()) {
                                            final FirebaseUser user = mAuth.getCurrentUser();
                                            pro.hide();
                                            Toast.makeText(Signup_page.this, "successfully Logged-in ", Toast.LENGTH_LONG).show();

                                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                                    .setDisplayName(mpin).build();

                                            user.updateProfile(profileUpdates)
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if (task.isSuccessful()) {
                                                                // Toast.makeText(Signup_page.this, "added", Toast.LENGTH_LONG).show();
                                                            }
                                                        }
                                                    });
                                            //
                                            Toast.makeText(Signup_page.this, "successfull sign_up", Toast.LENGTH_LONG).show();
                                            startActivity(new Intent(Signup_page.this,Notbooked.class));
                                            //

                                            finish();
                                            //create user
                                        } else {
                                            pro.hide();
                                            Toast.makeText(Signup_page.this, "Gmail Already registered", Toast.LENGTH_LONG).show();
                                        }

                                    }
                                });


                            }else {
                                pro.hide();
                                Toast.makeText(Signup_page.this, "sign-up with iiitk gmail", Toast.LENGTH_LONG).show();
                            }

                        }else{
                            Toast.makeText(Signup_page.this, "PLEASE CONFIRM PASSWORD... ", Toast.LENGTH_LONG).show();
                        }

                    }else{

                        Toast.makeText(Signup_page.this, "PASSWORD ATLEAST 6 CHARACTERS... ", Toast.LENGTH_LONG).show();
                    }
                }//first if

                // for   signup_btn.setOn
            }
        });


        //for oncreate
    }
    // for end
}
