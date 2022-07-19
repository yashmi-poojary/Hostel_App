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

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity implements View.OnClickListener {
    Intent siIntent;
    private TextView register;
    private EditText uname, password;
    private Button Login;
    String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";
    ProgressDialog progressDialog;

    private FirebaseAuth mAuth;
    FirebaseUser mUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        register = (TextView) findViewById(R.id.registerNowBtn);
        register.setOnClickListener(this);

        uname = (EditText) findViewById(R.id.username);
        password =(EditText) findViewById(R.id.password);

        Login = (Button) findViewById(R.id.loginBtn);
        Login.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();



    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.registerNowBtn: startActivity(new Intent(Login.this,Register.class));
                break;

            case R.id.loginBtn:performLogin();
                break;
        }
    }

    private void performLogin() {

        String username = uname.getText().toString().trim();
        String passw = password.getText().toString().trim();

        if(!username.matches(emailPattern)) {
            uname.setError("Enter the correct Id");
        }
        else if(passw.isEmpty() || passw.length() < 6) {
            uname.setError("Enter correct password");
        }
        else {
            progressDialog.setMessage("Please wait");
            progressDialog.setTitle("Logging in");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }


            mAuth.signInWithEmailAndPassword(username,passw)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();
                                sendUserToNextActivity();

                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(Login.this, "Failed to Login"+task.getException(), Toast.LENGTH_LONG).show();

                            }
                        }
                    });
        }
    private void sendUserToNextActivity() {
        Intent intent = new Intent(Login.this, Home.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}

