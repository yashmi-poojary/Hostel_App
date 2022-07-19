package com.example.hostel_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MenuFragment extends Fragment {

    private TextView textView;
    public MenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_menu, container, false);
        textView = v.findViewById(R.id.textView);
        String data = getArguments().getString("key");
        textView.setText(data);
        return v;
    }
}