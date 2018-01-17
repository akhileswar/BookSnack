package com.akhil.breuna.wid;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends Activity {
    public TextView texttt;
    public Button login,signup;
    public EditText emails,passwords;
    public ProgressDialog pro;
    public ImageView img;
    public Firebase mreff;
    public FirebaseAuth mref;
    public Firebase.AuthStateListener mAuth;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        Firebase.setAndroidContext(this);
        mref = FirebaseAuth.getInstance();
        mreff=new Firebase("https://divvvvvvv-208a2.firebaseio.com/");

        texttt=(TextView)findViewById(R.id.textView3);
        emails=(EditText)findViewById(R.id.editText8);
        passwords=(EditText)findViewById(R.id.editText6);
        login=(Button)findViewById(R.id.button2);
        signup=(Button)findViewById(R.id.button6);
        pro=new ProgressDialog(this);
        img=(ImageView)findViewById(R.id.imageView2);
        //
        texttt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(login.this,"sorry,not implemented yet ", Toast.LENGTH_LONG).show();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = emails.getText().toString();
                String password = passwords.getText().toString();


                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {

                    Toast.makeText(login.this,"fill all fields.....", Toast.LENGTH_LONG).show();

                } else {

                    pro.setMessage("Signing-In....");
                    pro.show();

                    mref.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                FirebaseUser user=mref.getCurrentUser();
                                String userid=user.getUid();

                                pro.hide();
                                Toast.makeText(login.this,"Log-in success.....", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(login.this,Notbooked.class));

                                finish();

                            } else {

                                pro.hide();
                                Toast.makeText(login.this,"Enter valid Email.....", Toast.LENGTH_LONG).show();
                                return;

                            }
                        }
                    });

                }
            }
        });
//
        //
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this,Signup_page.class));
            }
        });
        //
//for oncreate method
    }
    //for end

    @Override
    protected void onStart(){

        super.onStart();
        FirebaseUser currentUser=mref.getCurrentUser();
        if(currentUser!=null){
          startActivity(new Intent(login.this,Intermediate.class));
            //

            //
        }
    }
    //

    //
}


