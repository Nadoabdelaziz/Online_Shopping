package com.example.online_shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.online_shopping.Model.CartModel;
import com.example.online_shopping.Model.ProductModel;

public class MenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_men);
        int count=0;

        final MyDatabase CartDB= new MyDatabase(getApplicationContext());
//        ProductModel myprod = new ProductModel(27,1,"ProductName3",275);
//
//        CartDB.insertProduct(myprod);
//        MyDatabase CartDB= new MyDatabase(getApplicationContext())
//        if (cursor.getCount() == 3)
//            Toast.makeText(this, "products exist", Toast.LENGTH_SHORT).show();



        final TextView newText1 = (TextView) findViewById(R.id.text_cart_1);
        TextView newText2 = (TextView) findViewById(R.id.text_cart_2);
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
//                CartModel newCart = new CartModel(newText1.getText().toString());
//                newCart.setCart_prod_name(newText1.getText().toString());
//                newCart.setQty(1);
//                CartDB.dbcereate();
                CartDB.insertCartItem(newText1.getText().toString());
                Toast.makeText(getApplicationContext(),"zizo",Toast.LENGTH_LONG).show();
            }
        });

//        Cursor cursor2 = CartDB.getCartItems();
//        if (cursor2.getCount() > 0)
//           Toast.makeText(this, "Cart items exist", Toast.LENGTH_SHORT).show();


//        newText2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                CartModel newCart = new CartModel();
//                newCart.setCart_prod_name(newText1.getText().toString());
//                CartDB.insertCartItem();
//            }
//        });
//
//        newText3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                CartModel newCart = new CartModel();
//                newCart.setCart_prod_name(newText1.getText().toString());
//                CartDB.insertCartItem();
//            }
//        });







    }
}