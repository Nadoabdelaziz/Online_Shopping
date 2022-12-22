package com.example.online_shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class WomenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_women);

        final MyDatabase CartDB= new MyDatabase(getApplicationContext());
        int count = 0;
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), submitActivity.class);
                startActivity(intent);
                // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
            }
        });


        final TextView newText1 = (TextView) findViewById(R.id.text_cart_1);
        final TextView newText2 = (TextView) findViewById(R.id.text_cart_2);
        final TextView newText3 = (TextView) findViewById(R.id.text_cart_3);

        Cursor cursor = CartDB.getProducts();


        while(!cursor.isAfterLast()){
            if (count == 0){
                newText1.setText(cursor.getString(0));
            }
            else if (count == 1){
                newText3.setText(cursor.getString(0));
            }
            else if(count == 2){
                newText2.setText(cursor.getString(0));
            }
            count++;
            cursor.moveToNext();
        }



        newText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartDB.insertCartItem(newText1.getText().toString());
                Toast.makeText(getApplicationContext(),"zizo1",Toast.LENGTH_LONG).show();
            }
        });

        newText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartDB.insertCartItem(newText2.getText().toString());
                Toast.makeText(getApplicationContext(),"zizo2",Toast.LENGTH_LONG).show();
            }
        });
//
        newText3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartDB.insertCartItem(newText3.getText().toString());
                Toast.makeText(getApplicationContext(),"zizo3",Toast.LENGTH_LONG).show();
            }
        });



    }







}