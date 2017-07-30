package com.example.lasha.fruitbasket;

/**
 * Created by lasha on 7/17/2017.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;
import android.os.Build;

public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="users.db";
    public static final String TABLE_USERS="users";
    public static final String COLUMN_ID="_id";
    public static final String COLUMN_USERNAME="username";
    public static final String COLUMN_PASSWORD="password";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      String query="CREATE TABLE "+ TABLE_USERS+"("+
              COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"+
              COLUMN_USERNAME+" TEXT ,"+
              COLUMN_PASSWORD+" TEXT "+")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL("DROP TABLE IF EXISTS "+TABLE_USERS);
        onCreate(db);
    }
    public void addUser(Users user){
        ContentValues values=new ContentValues();
        values.put(COLUMN_USERNAME,user.get_username());
        values.put(COLUMN_PASSWORD,user.get_password());
        SQLiteDatabase db=getWritableDatabase();
        db.insert(TABLE_USERS,null,values);
        db.close();
    }
    public void deleteUser(String userName){
         SQLiteDatabase db=getWritableDatabase();
        db.execSQL("DELETE FROM "+ TABLE_USERS +" WHERE "+COLUMN_USERNAME+"=\""+userName+"\";");
    }
    public Boolean checkUser(String userName,String password){
        SQLiteDatabase db=getWritableDatabase();
        String Query = "Select * from " + TABLE_USERS + " where " + COLUMN_USERNAME+ " = " + userName + " AND " +
                COLUMN_PASSWORD+" = "+password;
        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;

    }
    public  String databaseTOString(){
        String dbString="";
        SQLiteDatabase db=getWritableDatabase();
        String query="SELECT * FROM " +TABLE_USERS+" WHERE 1";
        Cursor c =db.rawQuery(query,null);
        c.moveToFirst();

        while(!c.isAfterLast()){
            if(c.getString((c.getColumnIndex("userName")))!=null){
                dbString+=c.getString(c.getColumnIndex("userName"));
                dbString+="\n";
            }
        }
        db.close();;
        return dbString;
    }
}
