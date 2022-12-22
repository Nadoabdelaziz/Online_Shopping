package com.example.online_shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class submitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);



        ListView CartList = (ListView) findViewById(R.id.products_cart);
        ArrayAdapter <String> myadabpter = new ArrayAdapter<String> (getApplicationContext(),
                android.R.layout.simple_list_item_1);
        CartList.setAdapter(myadabpter);

        MyDatabase newdb = new MyDatabase(getApplicationContext());
        Cursor cursor = newdb.getCart();
        if(cursor.getCount() == 0){
            myadabpter.add("No Items Found");
        }
        else
        {
            String HEADER = "Name" +"                                                                | "+ "Price";
            myadabpter.add(HEADER);
            myadabpter.add("\n");


            while (!cursor.isAfterLast()) {
            String Result = cursor.getString(1) +"                                        | "+ cursor.getString(2)+"  EGP";
            myadabpter.add(Result);
            
            cursor.moveToNext();
        }
        }


    }


}