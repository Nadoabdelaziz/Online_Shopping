package com.example.online_shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class submitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);



        final ListView CartList = (ListView) findViewById(R.id.products_cart);
        final ArrayAdapter <String> myadabpter = new ArrayAdapter<String> (getApplicationContext(),
                android.R.layout.simple_list_item_1);
        CartList.setAdapter(myadabpter);

        final ListView CartListprice = (ListView) findViewById(R.id.products_cart2);
        final ArrayAdapter <String> myadabpterprice = new ArrayAdapter<String> (getApplicationContext(),
                android.R.layout.simple_list_item_1);
        CartListprice.setAdapter(myadabpterprice);

        final ListView CartListqty = (ListView) findViewById(R.id.products_cart3);
        final ArrayAdapter <String> myadabpterqty = new ArrayAdapter<String> (getApplicationContext(),
                android.R.layout.simple_list_item_1);
        CartListqty.setAdapter(myadabpterqty);


        int qty1 = 1;
        int qty2 = 1;
        int qty3 = 1;
        int qty4 = 1;

        final EditText qty_control = (EditText) findViewById(R.id.qty_control);
//        qty_control.setText("1");




        CartListqty.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = CartListqty.getItemAtPosition(position).toString();
//                String newitem = item.replaceAll("[^0-9]","");
                qty_control.setText(item);
                myadabpterqty.remove(CartListqty.getItemAtPosition(position).toString());
//                myadabpterqty.add(qty_control.getText().toString());
//                Toast.makeText(getApplicationContext(),qty_control.getText().toString(),Toast.LENGTH_LONG).show();
//                qty1[0]++;

//                Toast.makeText(getApplicationContext(),Integer.parseInt(qty_control.toString()),Toast.LENGTH_LONG).show();
//                qty1[0] = Integer.parseInt(qty_control.toString());

            }
        });

        qty_control.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                myadabpterqty.add(qty_control.getText().toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        SharedPreferences sharedPreferences;
        sharedPreferences=getSharedPreferences("remember file",MODE_PRIVATE);
        String name = sharedPreferences.getString("username","");
        Boolean logged = sharedPreferences.getBoolean("login",false);

//        int qty1=1,qty2 = 1, qty3=1;

        MyDatabase newdb = new MyDatabase(getApplicationContext());
        Cursor cursor = newdb.getCart(name);
        if(cursor.getCount() == 0){
//            myadabpter.add(name);
            myadabpter.add("No Items Found");
        }
        else
        {
//          myadabpter.add("Current user is:    "+ name + "\n Cart user is:   " +cursor.getString(0));
            String HEADER = "Name" ;

            myadabpter.add("Name");
//            myadabpter.add("\n");
            myadabpterprice.add("Price");

            myadabpterqty.add("QTY");


//          if(cursor.getString(0).equals(name)){
                while (!cursor.isAfterLast()) {
//                    for (int i =0;i<CartList.getCount();i++){
//                        if(cursor.getString(1).equals(Cart))
//                    }
                    String Result = cursor.getString(1);
                    myadabpter.add(Result);
                    myadabpterprice.add(cursor.getString(2) + "  EGP");
                    myadabpterqty.add(String.valueOf(qty1));

                    cursor.moveToNext();
                }
//            }
//            else
//                Toast.makeText(getApplicationContext(),"no data to show",Toast.LENGTH_LONG).show();
        }


    }


}