package com.example.swiftdelivery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class RetailerAddProductActivity extends AppCompatActivity {

    private ImageView fruits,vegetables;
    private ImageView grocery,packagedproducts;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_add_product);

        fruits=(ImageView)findViewById(R.id.fruits);
        vegetables=(ImageView)findViewById(R.id.vegetables);
        grocery=(ImageView)findViewById(R.id.grocery);
        packagedproducts=(ImageView)findViewById(R.id.packegedproducts);
        back=(Button)findViewById(R.id.back_btn);

        fruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RetailerAddProductActivity.this, RetailerAddNewProductActivity.class);
                intent.putExtra("category","fruits");
                startActivity(intent);
            }
        });
        vegetables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RetailerAddProductActivity.this, RetailerAddNewProductActivity.class);
                intent.putExtra("category","vegetables");
                startActivity(intent);
            }
        });
        grocery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RetailerAddProductActivity.this, RetailerAddNewProductActivity.class);
                intent.putExtra("category","grocery");
                startActivity(intent);
            }
        });
        packagedproducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RetailerAddProductActivity.this, RetailerAddNewProductActivity.class);
                intent.putExtra("category","packagedproducts");
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RetailerAddProductActivity.this, RetailerHomeActivity.class);
                startActivity(intent);
            }
        });


    }
}