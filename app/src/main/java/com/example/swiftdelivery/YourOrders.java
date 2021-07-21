package com.example.swiftdelivery;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.swiftdelivery.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class YourOrders extends AppCompatActivity {

    private TextView orderStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yourorders);
        orderStatus = (TextView) (findViewById(R.id.order_generalmsg));


    }
    protected void onStart() {

        super.onStart();
        CheckOrderStatus();
    }
    private void CheckOrderStatus(){
        orderStatus = (TextView) (findViewById(R.id.order_generalmsg));

        DatabaseReference orderRef;
        orderRef = FirebaseDatabase.getInstance().getReference().child("Orders").child(Prevalent.currentOnlineUser.getUserName());

        orderRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()) {

                    String shippingState = snapshot.child("state").getValue().toString();
                    String userName = snapshot.child("name").getValue().toString();
                    String orderPrice = snapshot.child("totalAmount").getValue().toString();
                    if(shippingState.equals("shipped"))
                    {
                        orderStatus.setText("Dear "+ userName + "\n Your order is Shipped successfully\nYou will recieve it in 2-5 working days" +
                                "\nTotal Amount Payable: -Rs." + orderPrice);

                    }
                    else if(shippingState.equals("not shipped"))
                    {
                        orderStatus.setText("Shipping State = Not Shipped");


                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }



        });
    }
}