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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LeaveApplication extends AppCompatActivity {
    private EditText studentName, studentUsn, stRoom, Reason, dateOut, dateIn, advName, wardName;
    public Button submit1;

    // Firebase Database.
    FirebaseDatabase firebaseDatabase;
    // creating a variable for our Database, reference for firebase.
    DatabaseReference databaseReference;

    LeaveInfo leaveInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        studentName = findViewById(R.id.name);
        studentUsn = findViewById(R.id.usn);
        Reason= findViewById(R.id.reason);
        stRoom= findViewById(R.id.room);
        Reason= findViewById(R.id.reason);
        dateOut= findViewById(R.id.dateout);
        dateIn= findViewById(R.id.datein);
        advName= findViewById(R.id.adname);
        wardName= findViewById(R.id.warden);

        // below line is used to get the
        // instance of our FIrebase database.
        firebaseDatabase = FirebaseDatabase.getInstance();

        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference("leaveInfo");

        // initializing our object
        // class variable.
        //leaveInfo = new LeaveInfo();

        submit1 = findViewById(R.id.submit1);

        // adding on click listener for our button
    }
    public void submitButton(View view) {
        String name = studentName.getText().toString();
        String usn = studentUsn.getText().toString();
        String reason = Reason.getText().toString();
        String roomNo = stRoom.getText().toString();
        String dayIn = dateIn.getText().toString();
        String dayOut = dateOut.getText().toString();
        String advisor = advName.getText().toString();
        String warden = wardName.getText().toString();

        // below line is for checking weather the
        // edittext fields are empty or not.
        if (TextUtils.isEmpty(roomNo) && TextUtils.isEmpty(name) && TextUtils.isEmpty(usn) && TextUtils.isEmpty(reason) && TextUtils.isEmpty(dayIn) && TextUtils.isEmpty(dayOut) && TextUtils.isEmpty(advisor) && TextUtils.isEmpty(warden)) {
            // if the text fields are empty
            // then show the below message.
            Toast.makeText(LeaveApplication.this, "Please add all data.", Toast.LENGTH_SHORT).show();
        } else {
            // else call the method to add
            // data to our database.
            leaveInfo = new LeaveInfo(name, usn, roomNo,reason, dayIn, dayOut, advisor,warden);
            addDatatoFirebase(leaveInfo);
        }
    }
    private void addDatatoFirebase(LeaveInfo leaveInfo) {
        // below 3 lines of code is used to set
        // data in our object class.
        databaseReference.push().setValue(leaveInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(LeaveApplication.this, "data added", Toast.LENGTH_SHORT).show();
                } else {
                    Log.i("hostel", "error: " + task.getException().toString());
                }
            }
        });
        // we are use add value event listener method
        // which is called with database reference.
    }

}