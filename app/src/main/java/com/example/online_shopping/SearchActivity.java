package com.example.online_shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.online_shopping.Model.ProductModel;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        final EditText product_name = (EditText) findViewById(R.id.product);
        ListView products_list = (ListView)(findViewById(R.id.products_list));

        final ArrayAdapter<String> myadabpter = new ArrayAdapter<String> (getApplicationContext(),
                android.R.layout.simple_list_item_1);
        products_list.setAdapter(myadabpter);


        product_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                myadabpter.clear();
                MyDatabase DB = new MyDatabase(getApplicationContext());
                Cursor cursor = DB.getProductByName(product_name.getText().toString());
                if (cursor.getCount() > 0) {
                    myadabpter.add("ID    " +   "     CATEGORY    "   + " -  " + "  NAME  " + "                  PRICE      ");
                    if (cursor != null && cursor.moveToFirst()) {
                        do {
                            if (cursor.getString(5).equals("1")) {
                                myadabpter.add(cursor.getString(0) + "    -  MENS WEAR -     " + "  " +  cursor.getString(1) + "  "+ cursor.getString(3)+" EGP");
                            }
                            else
                                myadabpter.add(cursor.getString(0) + "- WOMENS WEAR  -     " + "  " +  cursor.getString(1) + "  "+ cursor.getString(3)+" EGP");

                        }
                        while (cursor.moveToNext());
                    }
                    cursor.close();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}