package com.example.swiftdelivery;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.swiftdelivery.Prevalent.Prevalent;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class FeedbackActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    String[] ratings={"Not Rated","Excellent","Good","Average","Poor"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

//Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner spin = (Spinner) findViewById(R.id.simpleSpinner);
        spin.setOnItemSelectedListener(this);


//Creating the ArrayAdapter instance having the bank name list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,ratings);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
    }
    @Override
    public void onStart() {

        super.onStart();

        EditText feedbackWritten = (EditText) findViewById(R.id.feedback_written);
        final DatabaseReference ordersRef = FirebaseDatabase.getInstance().getReference().child("Feedback")
                .child(Prevalent.currentOnlineUser.getUserName());
        HashMap<String, Object> ordersMap= new HashMap<>();
        ordersMap.put("feedback written",feedbackWritten.getText().toString());
        ordersRef.updateChildren(ordersMap);
    }


    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        final DatabaseReference ordersRef = FirebaseDatabase.getInstance().getReference().child("Feedback")
                .child(Prevalent.currentOnlineUser.getUserName());
        HashMap<String, Object> ordersMap= new HashMap<>();
        ordersMap.put("feedback provided",ratings[position]);
        ordersRef.updateChildren(ordersMap);
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }
}
