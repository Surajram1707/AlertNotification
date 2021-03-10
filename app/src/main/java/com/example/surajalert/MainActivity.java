package com.example.surajalert;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    static MainActivity inst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public static MainActivity Instance(){
        return inst;
    }
    @Override
    public  void onStart(){
        super.onStart();
        inst=this;
    }
}