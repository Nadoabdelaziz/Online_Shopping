package com.example.google_maps_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button GetStart = (Button) findViewById(R.id.button);

//        SharedPreferences sharedPreferences;
//        SharedPreferences.Editor editor;
//        sharedPreferences=getSharedPreferences("remember file",MODE_PRIVATE);
//        editor=sharedPreferences.edit();
//        editor.putBoolean("login",false);
//        editor.apply();

        GetStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openLoginActivity();
            }
        });

    }

    public void openLoginActivity() {
        Intent intent = new Intent(this, loginActivity.class);
        startActivity(intent);
    }

}