package com.example.online_shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class submitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);



        ListView CartList = (ListView) findViewById(R.id.products_cart);
        ArrayAdapter <String> myadabpter = new ArrayAdapter<String> (getApplicationContext(),
                android.R.layout.simple_list_item_1);
        CartList.setAdapter(myadabpter);


        SharedPreferences sharedPreferences;
        sharedPreferences=getSharedPreferences("remember file",MODE_PRIVATE);
        String name = sharedPreferences.getString("username","");
        Boolean logged = sharedPreferences.getBoolean("login",false);

        MyDatabase newdb = new MyDatabase(getApplicationContext());
        Cursor cursor = newdb.getCart(name);
        if(cursor.getCount() == 0){
//            myadabpter.add(name);
            myadabpter.add("No Items Found");
        }
        else
        {
//            myadabpter.add("Current user is:    "+ name + "\n Cart user is:   " +cursor.getString(0));
            String HEADER = "Name" +"                                                                | "+ "Price";
            myadabpter.add(HEADER);
            myadabpter.add("\n");

//            if(cursor.getString(0).equals(name)){
                while (!cursor.isAfterLast()) {
                    String Result = cursor.getString(1) + "                                        | " + cursor.getString(2) + "  EGP";
                    myadabpter.add(Result);

                    cursor.moveToNext();
                }
//            }
//            else
                Toast.makeText(getApplicationContext(),"no data to show",Toast.LENGTH_LONG).show();
        }


    }


}