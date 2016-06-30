package com.example.gerardo.sqltest.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by MasterHardisk on 30/06/16.
 */
public class SQLAdapter  {
    SQLHelper helper;

    public SQLAdapter (Context context)
    {
        helper = new SQLHelper(context);
    }

    public long insertData(String name, String password)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        //pongo los datos del usuario dentro de la variable ContentValues para despues usarlo en una query
        ContentValues contentValues =  new ContentValues();
        contentValues.put(SQLHelper.NAME, name);
        contentValues.put(SQLHelper.PASS, password);
        long id = db.insert(SQLHelper.TABLE_NAME, null, contentValues);
        return id;
    }

    public String getAllData()
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns ={SQLHelper.UID, SQLHelper.NAME, SQLHelper.PASS};
        Cursor cursor = db.query(SQLHelper.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()){
            int index1 = cursor.getColumnIndex(SQLHelper.UID);
            int index2 = cursor.getColumnIndex(SQLHelper.NAME);
            int index3 = cursor.getColumnIndex(SQLHelper.PASS);
            int cid = cursor.getInt(index1);
            String name = cursor.getString(index2);
            String password = cursor.getString(index3);
            buffer.append(cid+" "+name+" "+password+"\n");
        }
        return buffer.toString();
    }

    public int deleteUser(String name)
    {
        SQLiteDatabase db =  helper.getWritableDatabase();
        String[] whereArgs={name};
        int count =  db.delete(SQLHelper.TABLE_NAME, SQLHelper.NAME+ "=?", whereArgs);
        return count;
    }



    //creo una clase dentro de la clase para poder usar los atributos privados
    public class SQLHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "sqltest_database";
        private static final String TABLE_NAME = "SQLTABLE";
        private static final String UID = "_id";
        private static final String NAME = "Name";
        private static final String PASS = "Pâssword";
        private static final int DATABASE_VERSION = 1;
        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR(255), "+PASS+" VARCHAR (255))";
        private static final String DROP_TABLE = "DROP TABLE IF EXIST "+TABLE_NAME;

        public SQLHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            System.out.println("Constructor ha sido llamado");

        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            //Crear la base datos por primera vez
            try{
                sqLiteDatabase.execSQL(CREATE_TABLE);
                System.out.println("onCreate ha sido llamado");
            }catch (SQLException e) {
                System.out.println("error: "+e);
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            //Si hay algun cambio en la base de datos se elimina y se vuelve a crear
            try{
                sqLiteDatabase.execSQL(DROP_TABLE);
                onCreate(sqLiteDatabase);
                System.out.println("onUpgrade ha sido llamado");
            } catch (SQLException e){
                System.out.println("error: "+e);
            }


        }
    }



}
