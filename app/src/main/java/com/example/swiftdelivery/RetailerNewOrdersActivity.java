package com.example.swiftdelivery;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swiftdelivery.Models.RetailerOrders;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RetailerNewOrdersActivity extends AppCompatActivity {

    private RecyclerView ordersList;
    private DatabaseReference ordersRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_new_orders);

        ordersRef= FirebaseDatabase.getInstance().getReference().child("Orders");

        ordersList=findViewById(R.id.orders_list);
        ordersList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions <RetailerOrders> options=
                new FirebaseRecyclerOptions.Builder<RetailerOrders>()
                .setQuery(ordersRef,RetailerOrders.class)
                .build();

        FirebaseRecyclerAdapter<RetailerOrders,RetailerOrdersViewHolder>adapter=
                new FirebaseRecyclerAdapter<RetailerOrders, RetailerOrdersViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull RetailerOrdersViewHolder holder, int position, @NonNull RetailerOrders model) {
                        holder.userName.setText("Name: "+ model.getName());
                        holder.userPhone.setText("Phone: "+ model.getPhone());
                        holder.userTotalPrice.setText("Total: "+ model.getTotalAmount());
                        holder.userDateTime.setText("Order at: "+ model.getDate()+" "+model.getTime());
                        holder.userShippingAddress.setText("Shipping Address: "+ model.getAddress()+","+model.getCity());

                        holder.ShowOrdersBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                String uID=getRef(position).getKey();
                                System.out.println(uID);
                                Intent intent =new Intent(RetailerNewOrdersActivity.this,RetailerUserProductsActivity.class);
                                intent.putExtra("uid",uID);
                                startActivity(intent);
                            }
                        });

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                CharSequence options[] =new CharSequence[]
                                        {
                                                "Yes, Delivered",
                                                "Yes, Shipped",
                                                "No"
                                        };
                                AlertDialog.Builder builder= new AlertDialog.Builder(RetailerNewOrdersActivity.this);
                                builder.setTitle("Have the products been shipped?");
                                builder.setItems(options, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int i) {
                                        if(i ==0)
                                        {
                                            String uID=getRef(position).getKey();
                                            RemoveOrder(uID);
                                        }
                                        if(i ==1)
                                        {
                                            String uID=getRef(position).getKey();
                                            ShipOrder(uID);
                                        }
                                        else
                                        {
                                            finish();
                                        }
                                    }
                                });
                                builder.show();
                            }
                        });
                    }

                    @NonNull
                    @Override
                    public RetailerOrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orders_layout,parent,false);
                        return new RetailerOrdersViewHolder(view);
                    }
                };
         ordersList.setAdapter(adapter);
        adapter.startListening();
    }


    public static class RetailerOrdersViewHolder extends RecyclerView.ViewHolder
    {
        public TextView userName, userPhone,userTotalPrice, userDateTime,userShippingAddress;
        public Button ShowOrdersBtn;

        public RetailerOrdersViewHolder(@NonNull View itemView)
        {
            super(itemView);
            userName=itemView.findViewById(R.id.order_user_name);
            userPhone=itemView.findViewById(R.id.order_phone);
            userTotalPrice=itemView.findViewById(R.id.order_total_price);
            userDateTime=itemView.findViewById(R.id.order_date_time);
            userShippingAddress=itemView.findViewById(R.id.order_address);
            ShowOrdersBtn=itemView.findViewById(R.id.show_all_products_btn);

        }
    }


    private void RemoveOrder(String uID) {
        ordersRef.child(uID).removeValue();
    }
    private void ShipOrder(String uID) {ordersRef.child(uID).child("state").setValue("shipped"); }
}