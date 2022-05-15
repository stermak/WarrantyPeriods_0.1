package com.example.kursach;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class LogActivity extends AppCompatActivity {

    public EditText input_email2, input_password2;
    public Button signInBtn2;
    public TextView signUpBtn2;
    private FirebaseAuth mAuth;
    private FirebaseDatabase db;
    private DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        input_email2 = findViewById(R.id.input_email2);
        input_password2 = findViewById(R.id.input_pass2);
        signInBtn2 = findViewById(R.id.signInBtn2);
        signUpBtn2 = findViewById(R.id.signUpBtn2);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");

        signInBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(input_email2.getText().toString()) && !TextUtils.isEmpty(input_password2.getText().toString())) {
                    mAuth.signInWithEmailAndPassword(input_email2.getText().toString(), input_password2.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intentLog = new Intent(LogActivity.this, MainActivity.class);
                                startActivity(intentLog);
                            } else {
                                Toast.makeText(LogActivity.this, "Почта или пароль введены неверно", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(LogActivity.this, "Заполните все поля", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signUpBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogActivity.this, RegActivity.class);
                startActivity(intent);
            }
        });
    }
}