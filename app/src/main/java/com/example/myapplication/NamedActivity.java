package com.example.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NamedActivity extends AppCompatActivity {

    public EditText eName;
    public Button eBntName;
    DBHelper sqlHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_named);

        sqlHelper = new DBHelper(getApplicationContext());
        db = sqlHelper.getWritableDatabase();

        eName = findViewById(R.id.editTextNamed);

        eBntName = findViewById(R.id.buttonNamed);
        eBntName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = eName.getText().toString();

                if (username.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Введите имя пользователя", Toast.LENGTH_LONG).show();
                    eName.setText("");
                }
                else {
                    ContentValues cv = new ContentValues();
                    cv.put(DBHelper.NAME_US, username);

                    db.update(DBHelper.USER_NAME, cv, DBHelper.NAME_ID + "=1", null);
                    db.close();

                    Intent intent = new Intent(NamedActivity.this, MainActivity.class);
                    startActivity(intent);

                    Toast.makeText(getApplicationContext(), "Ваше имя: "+username, Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}