package com.example.swiftdelivery;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.swiftdelivery.Prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class RetailerConfirmFinalOrder extends AppCompatActivity {

    private EditText custName, custPhone, custAddress, custCity;
    private Button confirmOrderBtn;
    private String totalAmount = "";
    @Override
    protected void onCreate(Bundle savedInstances) {

        super.onCreate(savedInstances);
        setContentView(R.layout.activity_final_order);

        totalAmount = getIntent().getStringExtra("Total Price");
        custName = (EditText) findViewById(R.id.shipment_customer_name);
        custCity = (EditText) findViewById(R.id.shipment_customer_city);
        custPhone = (EditText) findViewById(R.id.shipment_customer_phone);
        custAddress = (EditText) findViewById(R.id.shipment_customer_home);

        confirmOrderBtn = (Button) findViewById(R.id.shipment_confirm_button);

        confirmOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });
    }

    private void check() {

        if (TextUtils.isEmpty(custName.getText().toString())){
            Toast.makeText(this, "Please Enter your Name", Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(custPhone.getText().toString())){
            Toast.makeText(this, "Please Enter your Phone", Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(custAddress.getText().toString())){
            Toast.makeText(this, "Please Enter your Address", Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(custCity.getText().toString())){
            Toast.makeText(this, "Please Enter your City Name", Toast.LENGTH_SHORT).show();
        }
        else{

            confirmOrder();
        }

    }

    private void confirmOrder() {
        final String saveCurrentDate, saveCurrentTime;

        Calendar callForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format((callForDate.getTime()));

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format((callForDate.getTime()));

        final DatabaseReference ordersRef = FirebaseDatabase.getInstance().getReference().child("OrdersRetailer")
                .child(Prevalent.currentOnlineUser.getUserName());
        HashMap<String, Object> ordersMap= new HashMap<>();
        ordersMap.put("totalAmount", totalAmount);
        ordersMap.put("name", custName.getText().toString());
        ordersMap.put("phone", custPhone.getText().toString());
        ordersMap.put("address", custAddress.getText().toString());
        ordersMap.put("city", custCity.getText().toString());
        ordersMap.put("date", saveCurrentDate);
        ordersMap.put("time", saveCurrentTime);
        ordersMap.put("state", "not shipped");

        ordersRef.updateChildren(ordersMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful())

                    FirebaseDatabase.getInstance().getReference().child("Cart ListRetailer")
                            .child("User View").child(Prevalent.currentOnlineUser.getUserName())
                            .removeValue()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()) {

                                        Toast.makeText(RetailerConfirmFinalOrder.this, "your order has been placed", Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(RetailerConfirmFinalOrder.this, RetailerHomeActivity.class);
                                        intent.addFlags((Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                                        startActivity(intent);
                                        finish();
                                    }
                                }

                            });
            }
        });

    }

}