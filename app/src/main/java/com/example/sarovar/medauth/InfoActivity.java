package com.example.sarovar.medauth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InfoActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private EditText name,sid,email,phone,aphone;
    private Button save,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        firebaseAuth=FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser()==null){
            // first login
        }

        databaseReference = FirebaseDatabase.getInstance().getReference();

        name = (EditText)findViewById(R.id.editTextSname);
        sid = (EditText)findViewById(R.id.editTextSid);
        email = (EditText)findViewById(R.id.editTextEmail);
        phone = (EditText)findViewById(R.id.editTextSphone);
        aphone = (EditText)findViewById(R.id.editTextAPhone);

        save = (Button)findViewById(R.id.buttonSave);
        back = (Button)findViewById(R.id.buttonBack);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(InfoActivity.this, "You will go to option page", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NAME,ID,EMAIL,PHONE,APHONE;
                NAME = name.getText().toString().trim();
                ID = sid.getText().toString().trim();
                EMAIL = email.getText().toString().trim();
                PHONE = phone.getText().toString().trim();
                APHONE = aphone.getText().toString().trim();

                if(TextUtils.isEmpty(NAME)){
                    Toast.makeText(InfoActivity.this, "Enter Name ", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(ID)){
                    Toast.makeText(InfoActivity.this, "Enter Id ", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(EMAIL)){
                    Toast.makeText(InfoActivity.this, "Enter email ", Toast.LENGTH_SHORT).show();
                }

                if(TextUtils.isEmpty(PHONE)){
                    Toast.makeText(InfoActivity.this, "Enter Phone No.", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(APHONE)){
                    Toast.makeText(InfoActivity.this, "Enter Alternate Phone No. ", Toast.LENGTH_SHORT).show();
                }

                UserInfo userInfo = new UserInfo(NAME,ID,EMAIL,PHONE,APHONE);

                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

                databaseReference.child(firebaseUser.getUid()).setValue(userInfo);

                Toast.makeText(InfoActivity.this, "Information Saved", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
