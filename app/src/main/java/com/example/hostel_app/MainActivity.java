package com.example.hostel_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView click;
    Intent siIntent, homeIntent;
    FirebaseAuth mAuth;
    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() == null) {
            startActivity(siIntent);
            finish();
        } else {
            startActivity(homeIntent);
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        siIntent = new Intent(getApplicationContext(), Login.class);
        homeIntent = new Intent(getApplicationContext(), Home.class);
        mAuth = FirebaseAuth.getInstance();
        /*click = (TextView) findViewById(R.id.ch);
        click.setOnClickListener(this);*/
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this,Login.class);
        startActivity(intent);
    }
}