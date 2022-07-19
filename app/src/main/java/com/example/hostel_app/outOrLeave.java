package com.example.hostel_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class outOrLeave extends AppCompatActivity {
    Button out, leave;
    Intent leavePage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_or_leave);

        out = (Button) findViewById(R.id.outing);
        leave= (Button) findViewById(R.id.leave);

        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent outPage= new Intent(getApplicationContext(), Outing.class);
                startActivity(outPage);
                finish();
            }
        });

        leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent leavePage= new Intent(getApplicationContext(), LeaveApplication.class);
                startActivity(leavePage);
                finish();
            }
        });

    }
}