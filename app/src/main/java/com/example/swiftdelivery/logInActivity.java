package com.example.swiftdelivery;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.swiftdelivery.Models.User;
import com.example.swiftdelivery.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class logInActivity extends AppCompatActivity {

    private EditText InputUserName, InputEmail, InputPassword;
    private Button LoginButton;
    private ProgressDialog loadingBar;

    private String parentDbName = "User";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);


        LoginButton = (Button) findViewById(R.id.login_btn);
        InputPassword = (EditText) findViewById(R.id.login_password_input);
        InputEmail = (EditText) findViewById(R.id.login_mail_input);
        InputUserName = (EditText) findViewById(R.id.login_name_input);

        loadingBar = new ProgressDialog(this);


        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginUser();
            }
        });


    }


    private void LoginUser() {
        String Name = InputUserName.getText().toString();
        String password = InputPassword.getText().toString();
        String email = InputEmail.getText().toString();

        if (TextUtils.isEmpty(Name)) {
            Toast.makeText(this, "Please enter your User Name...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter your Email...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please write your password...", Toast.LENGTH_SHORT).show();
        } else {
            loadingBar.setTitle("Login Account");
            loadingBar.setMessage("Please wait, while we are checking the credentials.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();



            final DatabaseReference RootRef;
            RootRef = FirebaseDatabase.getInstance().getReference("User");
            RootRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.child(Name).exists()) {

                        User usersData = dataSnapshot.child(Name).getValue(User.class);

                        if (usersData.getUserName().toString().equals(Name)&&usersData.getPassword().toString().equals(password)&&usersData.getMail().toString().equals(email)) {
                                    if (usersData.getUserType().toString().equals("Consumer")) {
                                        Toast.makeText(logInActivity.this, "Welcome Customer, you are logged in Successfully...", Toast.LENGTH_SHORT).show();
                                        Prevalent.currentOnlineUser = usersData;
                                        loadingBar.dismiss();

                                        Intent intent = new Intent(logInActivity.this, HomeActivity.class);
                                        startActivity(intent);
                                    } else if (usersData.getUserType().toString().equals("Retailer")) {
                                        Toast.makeText(logInActivity.this, "logged in Successfully...", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();

                                        Intent intent = new Intent(logInActivity.this, RetailerHomeActivity.class);
                                        Prevalent.currentOnlineUser = usersData;
                                        startActivity(intent);
                                    } else if (usersData.getUserType().toString().equals("Wholesaler")) {
                                        Toast.makeText(logInActivity.this, "logged in Successfully...", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();

                                        Intent intent = new Intent(logInActivity.this, WholesalerHomeActivity.class);
                                        Prevalent.currentOnlineUser = usersData;
                                        startActivity(intent);
                                    }
                                }
                        else {
                            Toast.makeText(logInActivity.this, "Account with this " + Name + " number do not exists.", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                            }
                        }
                        }


                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }

                ;
            });
        }
    }



}