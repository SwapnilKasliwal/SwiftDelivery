package com.example.swiftdelivery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.swiftdelivery.Prevalent.Prevalent;

public class WholesalerHomeActivity extends AppCompatActivity {
    private Button pendingOrders, addProducts,logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wholesaler_home);

        pendingOrders = (Button) findViewById(R.id.pending_orders_btn);
        addProducts = (Button) findViewById(R.id.add_new_products_btn);
        logout=(Button)findViewById(R.id.logout_btn);

        addProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(WholesalerHomeActivity.this,WholesalerAddProductActivity.class);
                startActivity(intent);
            }
        });
        pendingOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(WholesalerHomeActivity.this, WholesalerNewOrdersActivity.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(WholesalerHomeActivity.this, "logged out.", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(WholesalerHomeActivity.this, logInActivity.class);
                startActivity(intent);
            }
        });
        TextView Username=findViewById(R.id.wholesaler_name);
        Username.setText(Prevalent.currentOnlineUser.getUserName());



    }
}