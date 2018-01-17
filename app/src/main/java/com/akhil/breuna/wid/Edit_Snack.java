package com.akhil.breuna.wid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class Edit_Snack extends Mainnavigation {
    TextView t1,t2;
    EditText num;
    Button confirm,cancel;
    public Firebase mref;
    public FirebaseAuth mAuth;
    public Firebase.AuthStateListener mAuthstate;
    public CheckBox check_box;
    String today_dates;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
     //   super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_edit__snack);
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_edit__snack, null, false);
        mDraw.addView(contentView, 0);

//

        //get the spinner from the xml.
       final Spinner dropdownn = (Spinner)findViewById(R.id.staticc_spinner);
//create a list of items for the spinner.
        String[] items = new String[]{"1", "2", "3","4","5","6"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        dropdownn.setAdapter(adapter);

        check_box = (CheckBox)findViewById(R.id.checkBox);
/*

dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
});
*/
        //

        Firebase.setAndroidContext(this);
        mAuth = FirebaseAuth.getInstance();
        mref=new Firebase("https://divvvvvvv-208a2.firebaseio.com/");

        t1=(TextView)findViewById(R.id.textView11);
        t2=(TextView)findViewById(R.id.textView12);

        confirm=(Button)findViewById(R.id.button4);
        cancel=(Button)findViewById(R.id.button7);

        Bundle bundle = getIntent().getExtras();
        final String item = bundle.getString("item");
        final String date=bundle.getString("date");
     //  final String today_dates=bundle.getString("date");
        final String numb=bundle.getString("number");
        final String drink=bundle.getString("drink");
        final String day=bundle.getString("day");


        int n=Integer.parseInt(numb);
dropdownn.setSelection(n-1);
        if(day.equals("Today")) {
            t1.setText("Today(" + date + ")-Snack");
            t2.setText(item + " - " + drink);
        }else{

            t1.setText("Tmmro(" + date + ")-Snack");
            t2.setText(item + " - " + drink);
        }
check_box.setText(drink);
int j=0;
        if(drink.equals("none")){

            j++;
        }
        if(j==0){
            check_box.setChecked(true);
        }

        FirebaseUser user=mAuth.getCurrentUser();
        final String user_gmail=user.getEmail();
        final String user_uid=user.getUid();

        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        final String dates = df.format(Calendar.getInstance().getTime());

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(check_box.isChecked()){
                    mref.child("booked").child(user_uid).child(user_uid+"id").child("drink").setValue(drink);
                    mref.child("previous").child(user_uid).child(date).child("drink").setValue(drink);
                }else{
                    mref.child("booked").child(user_uid).child(user_uid+"id").child("drink").setValue("none");
                    mref.child("previous").child(user_uid).child(date).child("drink").setValue("none");
                }

                final  String dropdown_item=dropdownn.getSelectedItem().toString();
                mref.child("booked").child(user_uid).child(user_uid+"id").child("gmail").setValue(user_gmail);
                mref.child("booked").child(user_uid).child(user_uid+"id").child("item").setValue(item);
                mref.child("booked").child(user_uid).child(user_uid+"id").child("item_num").setValue(dropdown_item);

                mref.child("previous").child(user_uid).child(date).child("date_time").setValue(date);
                mref.child("previous").child(user_uid).child(date).child("item").setValue(item);
                mref.child("previous").child(user_uid).child(date).child("item_num").setValue(dropdown_item);
                Toast.makeText(Edit_Snack.this,"order-edited successfully",Toast.LENGTH_LONG).show();
                startActivity(new Intent(Edit_Snack.this,Intermediate.class));
            }
        });

cancel.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        mref.child("booked").child(user_uid).getRef().setValue(null);
        mref.child("previous").child(user_uid).getRef().setValue(null);
        Toast.makeText(Edit_Snack.this,"order-canceled successfully",Toast.LENGTH_LONG).show();
        startActivity(new Intent(Edit_Snack.this,Intermediate.class));
    }
});



    }
}
