package com.example.online_shopping;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.online_shopping.Model.CartModel;
import com.example.online_shopping.Model.CategoryModel;
import com.example.online_shopping.Model.CustomerModel;
import com.example.online_shopping.Model.ProductModel;

public class MyDatabase extends SQLiteOpenHelper {

    final static String dataName = "Mydatabase";
    SQLiteDatabase database;

    public MyDatabase(@Nullable Context context) {
        super(context, dataName, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table  customer (id integer primary key  autoincrement , name text not null, email text not null," +
                "password text not null, gender text not null, birthdate text , jop text )");

        db.execSQL("create table category (id integer primary key  autoincrement , name text not null )");

        db.execSQL("create table product (id integer primary key autoincrement, name text not null ,image blob ," +
                "price real not null , quantity integer not null , cate_id integer not null ," +
                "foreign key (cate_id)references category (id))");

        db.execSQL("create table cart_items (id integer primary key autoincrement , productname text," +
                "productid integer,productprice integer)");

        db.execSQL("create table user_cart (id integer primary key autoincrement , username text , productname text," +
                "productid integer,productprice integer, prodcutqty integer)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists customer");
        db.execSQL("drop table if exists category");
        db.execSQL("drop table if exists product");
        onCreate(db);

    }

    public void DeleteCartItems(){
        database = getWritableDatabase();
        database.delete("cart_items","id > 0",null);
        database.close();
    }

    // new delete Cart
    public void DeleteCart(){
        database = getWritableDatabase();
        database.delete("user_cart","id > 0",null);
        database.close();
    }



    public void insertCustomer(CustomerModel cust) {
        database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", cust.getCustName());
        values.put("email", cust.getMail());
        values.put("password", cust.getPassword());
        values.put("birthdate", cust.getCustBirthDate());
        values.put("gender", cust.getGender());
        values.put("jop", cust.getCustJop());

        database.insert("customer", null, values);
        database.close();

    }

    // cart insert
    public void insertCartItem(String Name) {
        database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("productname", Name);
//        values.put("quantity", cart.getCart_quantity());
//        values.put("price", cart.getCart_price());

        database.insert("cart_items", null, values);
        database.close();

    }

    // new cart insert
    public void insertIntoCart(String Name,String username,String price) {
        database = getWritableDatabase();
        ContentValues values = new ContentValues();
        String[] args = {Name};

//        Cursor cursor = database.rawQuery("select price from product where name =? ", args);
        values.put("username", username);
        values.put("productname", Name);
        Integer newpreice = Integer.parseInt(price);
        values.put("productprice",newpreice);
//       values.put("productprice",cursor.getString(0));
//       values.put("quantity", cart.getCart_quantity());
//       values.put("price", cart.getCart_price());

        database.insert("user_cart", null, values);
        database.close();

    }


    public Cursor userLogin(String username, String pass) {
        database = getReadableDatabase();
        String[] args = {username, pass};
        //database.query("customer","id",raw,null,null,null,null,null);
        Cursor cursor = database.rawQuery("select id from customer where name =? and  password =? ", args);

        if (cursor != null)
            cursor.moveToFirst();

        database.close();
        return cursor;

    }

    public void newdb(){
        database.execSQL("create table user_cart (id integer primary key autoincrement , username text , productname text," +
                "productid integer,productprice integer, prodcutqty integer)");
//        database.execSQL("ALTER TABLE  cart_items   ADD COLUMN username text ");
//        database.execSQL("ALTER TABLE  cart_items   ADD COLUMN prodcutqty integer ");
//        onCreate(database);
    }

    public String getPassword(String mail) {
        database = getReadableDatabase();
        String[] args = {mail};
        Cursor cursor = database.rawQuery("select password from customer where email =?", args);
        if (cursor.getCount()>0) {

            cursor.moveToFirst();
            database.close();
            return cursor.getString(0);
        }
        database.close();

        cursor.close();
        return null;
    }

    public void insertProduct(ProductModel product){
        database=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",product.getProName());
        values.put("image",product.getProImage());
        values.put("price",product.getPrice());
        values.put("quantity",product.getPro_quantity());
        values.put("cate_id",product.getCatId());

        database.insert("product",null,values);
        database.close();
    }

    public Cursor getProducts(){
        database=getReadableDatabase();
        String[]fields={"name","image","price","quantity","cate_id"};
       Cursor cursor= database.query("product",fields,null,null,null,null,null);

       if (cursor!=null)
           cursor.moveToFirst();

      // database.close();

       return cursor;


    }


    public Cursor getCartItems(){
        database=getReadableDatabase();
        String[]fields={"productname"};
        Cursor cursor= database.query("cart_items",fields,null,null,null,null,null);

        if (cursor!=null)
            cursor.moveToFirst();

        // database.close();

        return cursor;
    }

    // new Get Cart
    public Cursor getCart(String name){
//        database=getReadableDatabase();
//        String[]fields={"username","productname","productprice"};
//        Cursor cursor= database.query("user_cart",fields,"username ="+name,null,null,null,null);
//
//        if (cursor!=null)
//            cursor.moveToFirst();
//
//        // database.close();
//
//        return cursor;
        database=getReadableDatabase();
        String []args={name};
        String[]fields={"username","productname","productprice"};
        Cursor cursor=  database.rawQuery("select Distinct username , productname , productprice from user_cart where username =? ",args);
        if (cursor!=null)
            cursor.moveToFirst();

        return cursor;
    }

    public void insertCategory(CategoryModel cate){
        database=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",cate.getCat_name());
        database.insert("category",null,values);
        database.close();
    }
    public Cursor getCategory(){
        database=getReadableDatabase();
        String []fields={"id","name"};
       Cursor cursor= database.query("category",fields,null,null,null,null,null);

       if (cursor.getCount()>0)
            cursor.moveToFirst();

       database.close();

       return cursor;
    }
    public Cursor getProductbyCategor(String cate){
        database=getReadableDatabase();
        String []args={cate};
      Cursor cursor=  database.rawQuery("select * from product where cate_id =? ",args);
        if (cursor!=null)
            cursor.moveToFirst();

        return cursor;

    }

    public Cursor getProductbyId(String id){
        database=getReadableDatabase();
        String []args={id};
        Cursor cursor=  database.rawQuery("select * from product where id =? ",args);
        if (cursor!=null)
            cursor.moveToFirst();

        return cursor;

    }

    // get Product Price
    public Cursor getProductPrice(String name){
        database=getReadableDatabase();
        String []args={name};
        Cursor cursor=  database.rawQuery("select price from product where name =? ",args);
        if (cursor!=null)
            cursor.moveToFirst();

        return cursor;
    }

    public String getCatId(String name ){
        database=getReadableDatabase();
        String[]args={name};
        Cursor cursor=database.rawQuery("select id from category where name =?",args);

        if (cursor.getCount()>0) {

            cursor.moveToFirst();
            database.close();
            return cursor.getString(0);
        }
        database.close();

        cursor.close();
        return null;

    }
}
