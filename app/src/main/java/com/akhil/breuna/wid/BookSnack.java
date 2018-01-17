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
import com.google.firebase.auth.FirebaseUser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BookSnack extends Mainnavigation {
    TextView t1,t2;
    EditText num;
    Button confirm;
    public Firebase mref;
    public FirebaseAuth mAuth;
    public Firebase.AuthStateListener mAuthstate;
    public CheckBox chk_box;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
      //  super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_book_snack);
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_book_snack, null, false);
        mDraw.addView(contentView, 0);

        Firebase.setAndroidContext(this);
        mAuth = FirebaseAuth.getInstance();
        mref=new Firebase("https://divvvvvvv-208a2.firebaseio.com/");

        t1=(TextView)findViewById(R.id.textView11);
        t2=(TextView)findViewById(R.id.textView12);
        confirm=(Button)findViewById(R.id.button4);

        Bundle bundle = getIntent().getExtras();
        final String item = bundle.getString("item");
        final String date=bundle.getString("date");
      //  final String today_date=bundle.getString("date");
        final String drink=bundle.getString("drink");
        final String day=bundle.getString("day");
        //
        chk_box = (CheckBox)findViewById(R.id.checkBox);
        //get the spinner from the xml.
        final Spinner dropdown = (Spinner)findViewById(R.id.static_spinner);
//create a list of items for the spinner.
        String[] items = new String[]{"1", "2", "3","4","5","6"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
        //

        if(day.equals("Today")) {
            t1.setText("Today(" + date + ")-Snack");
            t2.setText(item + " - " + drink);
        }else{

            t1.setText("Tmmro(" + date + ")-Snack");
            t2.setText(item + " - " + drink);
        }
        chk_box.setText(drink);
        FirebaseUser user=mAuth.getCurrentUser();
        final String user_gmail=user.getEmail();
        final String user_uid=user.getUid();

        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        final String dates = df.format(Calendar.getInstance().getTime());

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  String number=num.getText().toString();
                if(chk_box.isChecked()){
                    mref.child("booked").child(user_uid).child(user_uid+"id").child("drink").setValue(drink);
                    mref.child("previous").child(user_uid).child(date).child("drink").setValue(drink);
                }else{
                    mref.child("booked").child(user_uid).child(user_uid+"id").child("drink").setValue("none");
                    mref.child("previous").child(user_uid).child(date).child("drink").setValue("none");
                }

                final  String dropdown_item=dropdown.getSelectedItem().toString();
                mref.child("booked").child(user_uid).child(user_uid+"id").child("gmail").setValue(user_gmail);
                mref.child("booked").child(user_uid).child(user_uid+"id").child("item").setValue(item);
                mref.child("booked").child(user_uid).child(user_uid+"id").child("item_num").setValue(dropdown_item);


                mref.child("previous").child(user_uid).child(date).child("date_item").setValue(date);
                mref.child("previous").child(user_uid).child(date).child("item").setValue(item);
                mref.child("previous").child(user_uid).child(date).child("item_num").setValue(dropdown_item);

                Toast.makeText(BookSnack.this,"order-confirmed",Toast.LENGTH_LONG).show();
                startActivity(new Intent(BookSnack.this,Intermediate.class));
            }
        });





    }
}
