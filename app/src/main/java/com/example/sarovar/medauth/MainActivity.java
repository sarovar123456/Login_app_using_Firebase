package com.example.sarovar.medauth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
private EditText email,passwd;
private Button signup,login;

private ProgressDialog progressDialog;
private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(this);

        firebaseAuth=FirebaseAuth.getInstance();

        email=(EditText)findViewById(R.id.editTextEmail);
        passwd=(EditText)findViewById(R.id.editTextPassword);
        signup=(Button)findViewById(R.id.buttonSignup);
        login=(Button)findViewById(R.id.buttonLLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
               // Toast.makeText(MainActivity.this, "go to login page", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String em,pass;
                em=email.getText().toString().trim();
                pass=passwd.getText().toString().trim();

                if(TextUtils.isEmpty(em)){
                    Toast.makeText(MainActivity.this, "Enter email", Toast.LENGTH_SHORT).show();
                }

                if(TextUtils.isEmpty(pass)){
                    Toast.makeText(MainActivity.this, "Enter password", Toast.LENGTH_SHORT).show();
                }
                progressDialog.setMessage("Signing up");
                progressDialog.show();
                firebaseAuth.createUserWithEmailAndPassword(em,pass)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    progressDialog.dismiss();
                                    Toast.makeText(MainActivity.this, "Signed Up successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(MainActivity.this,LoginActivity.class));


                                    // sending a link for email verification...............................
                                    FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
                                    firebaseUser.sendEmailVerification()
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()){
                                                        Toast.makeText(MainActivity.this, "Check email verification on email.", Toast.LENGTH_SHORT).show();
                                                        FirebaseAuth.getInstance().signOut();
                                                    }
                                                }
                                            });
                                }
                                else
                                {
                                    progressDialog.dismiss();
                                    Toast.makeText(MainActivity.this, "Sign Up failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
