package com.example.swiftdelivery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.swiftdelivery.Prevalent.Prevalent;

public class RetailerHomeActivity extends AppCompatActivity {

    private Button orderProducts, pendingOrders, addProducts,logout, cartButton, feedbackButton,yourOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_home);

        orderProducts = (Button) findViewById(R.id.order_new_products);

        pendingOrders = (Button) findViewById(R.id.pending_orders_btn);
        addProducts = (Button) findViewById(R.id.add_new_products_btn);
        logout=(Button)findViewById(R.id.logout_btn);

        cartButton = (Button) findViewById(R.id.cart_button);
        feedbackButton = (Button) findViewById(R.id.feedback);
        yourOrders=(Button)findViewById(R.id.your_orders);

        orderProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RetailerHomeActivity.this, RetailerOrderProducts.class);
                startActivity(intent);
            }
        });

        addProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RetailerHomeActivity.this,RetailerAddProductActivity.class);
                startActivity(intent);
            }
        });
        pendingOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RetailerHomeActivity.this, RetailerNewOrdersActivity.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RetailerHomeActivity.this, logInActivity.class);
                startActivity(intent);
            }
        });
        feedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RetailerHomeActivity.this, FeedbackActivity.class);
                startActivity(intent);
            }
        });
        yourOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RetailerHomeActivity.this, RetailerYourOrders.class);
                startActivity(intent);
            }
        });
        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RetailerHomeActivity.this, RetailerCartActivity.class);
                startActivity(intent);
            }
        });



    }
}