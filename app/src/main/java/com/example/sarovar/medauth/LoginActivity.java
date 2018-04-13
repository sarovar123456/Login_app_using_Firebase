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

public class LoginActivity extends AppCompatActivity {
    private EditText email,passwd;
    private Button signup,login;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressDialog = new ProgressDialog(this);

        firebaseAuth=FirebaseAuth.getInstance();



        email=(EditText)findViewById(R.id.editTextLemail);
        passwd=(EditText)findViewById(R.id.editTextLpassword);
        signup=(Button)findViewById(R.id.buttonLRegister);
        login=(Button)findViewById(R.id.btlogin);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(MainActivity.this, "go to login page", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String em,pass;
                em=email.getText().toString().trim();
                pass=passwd.getText().toString().trim();

                if(TextUtils.isEmpty(em)){
                    Toast.makeText(LoginActivity.this, "Enter email", Toast.LENGTH_SHORT).show();
                }

                if(TextUtils.isEmpty(pass)){
                    Toast.makeText(LoginActivity.this, "Enter password", Toast.LENGTH_SHORT).show();
                }
                progressDialog.setMessage("Logging in...");
                progressDialog.show();

                firebaseAuth.signInWithEmailAndPassword(em,pass)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(LoginActivity.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                                    finish();
                                    progressDialog.dismiss();
                                   // startActivity(new Intent(LoginActivity.this,InfoActivity.class));

                                    // first verify email then try..

                                    //...............................
                                }
                                else {
                                    progressDialog.dismiss();
                                    Toast.makeText(LoginActivity.this, "Log in Failed , Try Again", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }
}
