package com.example.swiftdelivery;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.swiftdelivery.Models.User;
import com.example.swiftdelivery.databinding.ActivitySIgnUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    ActivitySIgnUpBinding binding;
    private FirebaseAuth auth;
    FirebaseDatabase database;
    ProgressDialog progressDialog;
    private Button accountalready;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySIgnUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Spinner mySpinner =(Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> myAdapter =new ArrayAdapter<String>(SignUpActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.UserType));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);


        Button getLocation;
        getLocation=findViewById(R.id.btngetLocation);
        getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToGetLocation=new Intent(SignUpActivity.this, MapsActivity.class);
                startActivity(intentToGetLocation);
            }
        });

        auth =FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        progressDialog=new ProgressDialog(SignUpActivity.this);
        progressDialog.setTitle("Creating Acount");
        progressDialog.setMessage("Creating Account");

        binding.signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                String userSelected=mySpinner.getSelectedItem().toString();
                auth.createUserWithEmailAndPassword
                        (binding.etEmailAddressSignUp.getText().toString(),binding.etPasswordSignUp.getText().toString()).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();
                                if(task.isSuccessful()){
                                    User user=new User(binding.etUserNameSignUp.getText().toString(),binding.etEmailAddressSignUp.getText().toString(),
                                            binding.etPasswordSignUp.getText().toString(),userSelected);
                                    String uid= task.getResult().getUser().getUid();
                                    String id= user.getUserName();
                                    database.getReference().child("User").child(id).setValue(user);
                                    Toast.makeText(SignUpActivity.this,"User Created", Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(SignUpActivity.this, logInActivity.class);
                                    startActivity(intent);
                                }
                                else{

                                    Toast.makeText(SignUpActivity.this, "Failed to create account", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


            }

        });

        accountalready=findViewById(R.id.tvToLogInPage);
        accountalready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignUpActivity.this, logInActivity.class);
                startActivity(intent);
            }
        });




    }
}