package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "userstore.db"; // название бд
    private static final int SCHEMA = 10; // версия базы данных

    // название таблицы в бд
    public static final String TABLE = "users";
    public static final String PIE = "PieChart";
    public static final String USER_NAME = "UserName";

    // названия столбцов для списка задач
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_YEAR = "year";
    // названия столбцов для колеса
    public static final String PIE_ID = "_id";
    public static final String PIE_VALUE = "value";

    // название столбца для имени пользователя
    public static final String NAME_ID = "_id";
    public static final String NAME_US = "user";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // ТАБЛИЦА ДЛЯ ЗАДАЧ
        db.execSQL("CREATE TABLE users (" + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME
                + " TEXT, " + COLUMN_YEAR + " INTEGER);");

        // ТАБЛИЦА ДЛЯ КОЛЕСА
        db.execSQL("CREATE TABLE PieChart (" + PIE_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + PIE_VALUE
                + " INTEGER);");

        // ТАБЛИЦА ДЛЯ ИМЕНИ ПОЛЬЗОВАТЕЛЯ
        db.execSQL("CREATE TABLE UserName (" + NAME_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME_US
                + " TEXT);");

        // добавление начальных данных
        db.execSQL("INSERT INTO "+ PIE +" (" + PIE_VALUE + ") VALUES (1);");
        db.execSQL("INSERT INTO "+ PIE +" (" + PIE_VALUE + ") VALUES (1);");
        db.execSQL("INSERT INTO "+ PIE +" (" + PIE_VALUE + ") VALUES (1);");
        db.execSQL("INSERT INTO "+ PIE +" (" + PIE_VALUE + ") VALUES (1);");
        db.execSQL("INSERT INTO "+ PIE +" (" + PIE_VALUE + ") VALUES (1);");
        db.execSQL("INSERT INTO "+ PIE +" (" + PIE_VALUE + ") VALUES (1);");
        db.execSQL("INSERT INTO "+ PIE +" (" + PIE_VALUE + ") VALUES (1);");

        db.execSQL("INSERT INTO "+ USER_NAME +" (" + NAME_US + ") VALUES ('');");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+PIE);
        db.execSQL("DROP TABLE IF EXISTS "+USER_NAME);
        onCreate(db);
    }
}

