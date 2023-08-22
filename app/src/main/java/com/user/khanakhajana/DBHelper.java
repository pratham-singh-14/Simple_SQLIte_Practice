package com.user.khanakhajana;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.user.khanakhajana.Adapters.OrderAdapter;
import com.user.khanakhajana.Models.OrderModel;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    final static String DBNAME = "mydatabase.sqlite";
    final static int VERSION =13;


    public DBHelper(@Nullable Context context) {
        super(context, DBNAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "create table ItemOrders" +
                        "(id integer primary key  autoincrement," + "Name text,"
                        + "Phone text," + "Price int," + "image int," + "Foodname text,"+"Quantity int,"+"Description String)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("Drop table if exists ItemOrders");
        onCreate(sqLiteDatabase);

    }

    public boolean Insertorder(String name, String phone, int price, int image, String foodname,int quantity,String description) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        /*
        name=1
        phone=2
        price=3
        image=4
        quantity=5
        fooodname=6
        description=7


         */
        values.put("Name", name);
        values.put("Phone", phone);
        values.put("Price", price);
        values.put("image", image);
        values.put("Quantity", quantity);
        values.put("Foodname", foodname);
        values.put("Description",description);

        long id = database.insert("ItemOrders", null, values);
        if (id <= -1) {
            return false;
        } else {
            return true;
        }  }


    //take data and set to orderactivity
          public ArrayList<OrderModel> getOrders() {
            ArrayList<OrderModel> orders=new ArrayList<>();
            SQLiteDatabase database1=this.getWritableDatabase();
            Cursor cursor=database1.rawQuery("select id,Foodname,image,price,Quantity from ItemOrders",null);
            if(cursor.moveToFirst()){
                while (cursor.moveToNext()){
                    OrderModel model=new OrderModel();
                    model.setOrderNumber(cursor.getInt(0) +"");
                    model.setOrderName(cursor.getString(1));
                    model.setOrderImage(cursor.getInt(2));
                    model.setOrderPrice(cursor.getInt(3)+"");
                    model.setQuantity(cursor.getInt(4)+"");
                   orders.add(model);

                }

            }
            cursor.close();
            database1.close();
          return orders;
        }
    public Cursor getOrderById(int id){
        SQLiteDatabase database1=this.getWritableDatabase();
        Cursor cursor=database1.rawQuery("select * from ItemOrders where id=" +id,null);
        if(cursor  != null)
            cursor.moveToFirst();


        return cursor;

    }
    public boolean Updateorder(String name, String phone, int price, int image, String foodname,int quantity,String description,int id) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        /*
        name=1
        phone=2
        price=3
        image=4
        quantity=5
        fooodname=6
        description=7


         */
        values.put("Name", name);
        values.put("Phone", phone);
        values.put("Price", price);
        values.put("image", image);
        values.put("Quantity", quantity);
        values.put("Foodname", foodname);
        values.put("Description",description);

        long raw = database.update("ItemOrders",  values,"id="+id,null);
        if (raw <= -1) {
            return false;
        } else {
            return true;
        }  }
    public int deleteOrder(String id){
        SQLiteDatabase database=this.getWritableDatabase();
        return  database.delete("ItemOrders","id="+id,null);
    }

}