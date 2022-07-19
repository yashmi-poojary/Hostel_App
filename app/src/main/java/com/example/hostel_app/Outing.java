package com.example.hostel_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Outing extends AppCompatActivity {

    private EditText date, studentName, studentUsn, outReason;
    private Button submit;

    // Firebase Database.
    FirebaseDatabase firebaseDatabase;
    // creating a variable for our Database, reference for firebase.
    DatabaseReference databaseReference;

    OutingInfo outingInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        date = findViewById(R.id.date);
        studentName = findViewById(R.id.name);
        studentUsn = findViewById(R.id.usn);
        outReason= findViewById(R.id.reason);

        // below line is used to get the
        // instance of our FIrebase database.
        firebaseDatabase = FirebaseDatabase.getInstance();

        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference("OutingInfo");

        // initializing our object
        // class variable.
        outingInfo = new OutingInfo();

        submit = findViewById(R.id.submit);

        // adding on click listener for our button.
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // getting text from our edittext fields.
                String day= date.getText().toString();
                String name = studentName.getText().toString();
                String usn = studentUsn.getText().toString();
                String reason = outReason.getText().toString();

                // below line is for checking weather the
                // edittext fields are empty or not.
                if (TextUtils.isEmpty(day) && TextUtils.isEmpty(name) && TextUtils.isEmpty(usn) && TextUtils.isEmpty(reason)) {
                    // if the text fields are empty
                    // then show the below message.
                    Toast.makeText(Outing.this, "Please add all data.", Toast.LENGTH_SHORT).show();
                } else {
                    // else call the method to add
                    // data to our database.
                    addDatatoFirebase(day, name, usn, reason);
                }
            }
        });
    }

    private void addDatatoFirebase(String day, String name, String usn, String reason) {
        // below 3 lines of code is used to set
        // data in our object class.
        outingInfo.setDate(day);
        outingInfo.setStuName(name);
        outingInfo.setStuUSN(usn);
        outingInfo.setReason(reason);
        databaseReference.push().setValue(outingInfo).addOnCompleteListener((OnCompleteListener) task -> {
            if (task.isSuccessful()) {
                Toast.makeText(Outing.this, "data added", Toast.LENGTH_SHORT).show();
            } else {
                Log.i("hostel", "error: " + task.getException().toString());
            }
        });
        // we are use add value event listener method
        // which is called with database reference.
    }
}