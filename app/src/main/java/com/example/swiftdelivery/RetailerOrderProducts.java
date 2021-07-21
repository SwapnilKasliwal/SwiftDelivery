package com.example.swiftdelivery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class RetailerOrderProducts  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_order_products);

        View fruits_touch = (View) findViewById(R.id.fruits_touch);
        fruits_touch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RetailerOrderProducts.this, RetailerProductsCat.class);
                intent.putExtra("Category", "fruits");
                startActivity(intent);

            }
        });

        View vegetables_touch = (View) findViewById(R.id.vegetables_touch);
        vegetables_touch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RetailerOrderProducts.this, RetailerProductsCat.class);
                intent.putExtra("Category", "vegetables");
                startActivity(intent);

            }
        });

        View grocery_touch = (View) findViewById(R.id.grocery_touch);
        grocery_touch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RetailerOrderProducts.this, RetailerProductsCat.class);
                intent.putExtra("Category", "grocery");
                startActivity(intent);

            }
        });

        View readymade_touch = (View) findViewById(R.id.readymade_touch);
        readymade_touch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RetailerOrderProducts.this, RetailerProductsCat.class);
                intent.putExtra("Category", "packagedproducts");
                startActivity(intent);

            }
        });
    }
}
