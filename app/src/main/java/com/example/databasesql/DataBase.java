package com.example.databasesql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {
    public static   final String DATABASE_NAME="base.db";
    public static final String DATABASE_table="Employes";
    public  static final String col_1="ID";
    public  static final String col_2="NOM";
    public  static final String col_3="EMAIL";
    public static final  String col_4="PHONE";
    public DataBase(Context context) {
        super(context, DATABASE_NAME,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+DATABASE_table+" (" +
                col_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                col_2 + " TEXT," +
                col_3 + " TEXT," +
                col_4 + "TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+DATABASE_table);
        onCreate(db);
    }
    public boolean insertData(String nom,String mail,String phone){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cn = new ContentValues();
        cn.put(col_2,nom);
        cn.put(col_3,mail);
        cn.put(col_4,phone);
        long resultat=db.insert(DATABASE_table,null,cn);
        if (resultat==-1)
            return false;
        else
            return true;
    }
    public Cursor getAllData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor resultat=db.rawQuery("SELECT * FROM "+DATABASE_table,null);
        return resultat;
    }
    public void delete(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(DATABASE_table,col_1+" =?",new String[]{String.valueOf(id)});
        db.close();
    }
    public void update(String nom,String mail,String phone ,int id){
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues cVals=new ContentValues();
        cVals.put(col_2,nom);
        cVals.put(col_3,mail);
        cVals.put(col_4,phone);
        int count =db.update(DATABASE_table,cVals,col_1+" =?",new String[]{String.valueOf(id)});
        db.close();
    }

}
