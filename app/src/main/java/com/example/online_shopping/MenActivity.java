package com.example.online_shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.online_shopping.Model.CartModel;
import com.example.online_shopping.Model.ProductModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_men);
        FloatingActionButton fab = findViewById(R.id.fab);

        int count=0;

        final MyDatabase CartDB= new MyDatabase(getApplicationContext());
//        ProductModel myprod = new ProductModel(27,1,"ProductName3",275);
//
//        CartDB.insertProduct(myprod);
//        MyDatabase CartDB= new MyDatabase(getApplicationContext())
//        if (cursor.getCount() == 3)
//            Toast.makeText(this, "products exist", Toast.LENGTH_SHORT).show();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), submitActivity.class);
                startActivity(intent);                // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
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



//        ImageView jackets = (ImageView) findViewById(R.id.jacket_id);

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