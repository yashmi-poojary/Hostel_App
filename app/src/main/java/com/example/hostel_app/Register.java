package com.example.hostel_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Register extends AppCompatActivity {

    private TextView already;
    private EditText email, password, confirmpass;
    private Button register;
    String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";
    ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        already = (TextView) findViewById(R.id.registered);
        already.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });

        register = (Button) findViewById(R.id.registerbtn);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.pass);
        confirmpass = (EditText) findViewById(R.id.conpass);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
    }

    private void register() {
        String e_mail = email.getText().toString().trim();
        String pass = password.getText().toString().trim();
        String conpass = confirmpass.getText().toString().trim();

        if (e_mail.isEmpty()) {
            email.setError("E-mail is required");
            email.requestFocus();
        } else if (!e_mail.matches(emailPattern)) {
            email.setError("Please Provide valid email");
            email.requestFocus();
        } else if (pass.isEmpty()) {
            password.setError("Password Required!");
            password.requestFocus();
        } else if (pass.length() < 6) {
            password.setError("Min password length should be 6 characters");
            password.requestFocus();
        } else if (!pass.equals(conpass)) {
            Toast.makeText(Register.this, "Password Not Matching", Toast.LENGTH_SHORT).show();
        } else {
            progressDialog.setMessage("Please wait");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(e_mail, pass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                sendUserToNextActivity();
                                Toast.makeText(Register.this, "Registered Successfully!", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(Register.this, "Failed to register", Toast.LENGTH_LONG).show();

                            }
                        }
                    });
        }
    }


    private void sendUserToNextActivity() {
        Intent intent = new Intent(Register.this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}