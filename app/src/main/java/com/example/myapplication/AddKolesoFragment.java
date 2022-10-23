package com.example.myapplication;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputLayout;

public class AddKolesoFragment extends Fragment {

    public Button famaly;
    public Button contr_to_her;
    public Button health;
    public Button love;
    public Button educ_and_dev;
    public Button money;
    public Button career_growth;
    public EditText edittext1;
    public TextView list_result1;
    DBHelper databaseHelper;
    SQLiteDatabase db;
    TextInputLayout textInputLayout;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_koleso, container, false);

        setHasOptionsMenu(true);

        textInputLayout = view.findViewById(R.id.textInputLayout);
        edittext1 = view.findViewById(R.id.ed_EditText1);
        list_result1 = view.findViewById(R.id.tv_list_result1);

        databaseHelper = new DBHelper(getActivity().getApplicationContext());

        famaly = view.findViewById(R.id.Famaly);
        famaly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edittext1.getText().toString().isEmpty() || Integer.parseInt(edittext1.getText().toString()) > 10 || Integer.parseInt(edittext1.getText().toString()) < 1 ) {
                    textInputLayout.setErrorEnabled(true);
                    textInputLayout.setError("Заполните поле цифрой от 1 до 10");
                    edittext1.setText("");
                    return;
                }

                else {
                    textInputLayout.setErrorEnabled(false);
                    int note = Integer.parseInt(edittext1.getText().toString());
                    ContentValues cv = new ContentValues();
                    db = databaseHelper.getWritableDatabase();

                    cv.put(DBHelper.PIE_VALUE, note);
                    db.update(DBHelper.PIE, cv, DBHelper.PIE_ID + "=1", null);
                    edittext1.setText("");
                    db.close();
                }
            }
        });

        contr_to_her = view.findViewById(R.id.Contr_to_Her);
        contr_to_her.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edittext1.getText().toString().isEmpty() || Integer.parseInt(edittext1.getText().toString()) > 10 || Integer.parseInt(edittext1.getText().toString()) < 1) {
                    textInputLayout.setErrorEnabled(true);
                    textInputLayout.setError("Заполните поле цифрой от 1 до 10");
                    edittext1.setText("");
                    return;
                }

                else {
                    textInputLayout.setErrorEnabled(false);
                    int note = Integer.parseInt(edittext1.getText().toString());
                    ContentValues cv = new ContentValues();
                    db = databaseHelper.getWritableDatabase();

                    cv.put(DBHelper.PIE_VALUE, note);
                    db.update(DBHelper.PIE, cv, DBHelper.PIE_ID + "=2", null);
                    edittext1.setText("");
                    db.close();
                }
            }
        });

        health = view.findViewById(R.id.Health);
        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edittext1.getText().toString().isEmpty() || Integer.parseInt(edittext1.getText().toString()) > 10 || Integer.parseInt(edittext1.getText().toString()) < 1) {
                    textInputLayout.setErrorEnabled(true);
                    textInputLayout.setError("Заполните поле цифрой от 1 до 10");
                    edittext1.setText("");
                    return;
                }

                else {
                    textInputLayout.setErrorEnabled(false);
                    int note = Integer.parseInt(edittext1.getText().toString());
                    ContentValues cv = new ContentValues();
                    db = databaseHelper.getWritableDatabase();

                    cv.put(DBHelper.PIE_VALUE, note);
                    db.update(DBHelper.PIE, cv, DBHelper.PIE_ID + "=3", null);
                    edittext1.setText("");
                    db.close();
                }
            }
        });

        educ_and_dev = view.findViewById(R.id.Educ_and_dev);
        educ_and_dev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edittext1.getText().toString().isEmpty() || Integer.parseInt(edittext1.getText().toString()) > 10 || Integer.parseInt(edittext1.getText().toString()) < 1) {
                    textInputLayout.setErrorEnabled(true);
                    textInputLayout.setError("Заполните поле цифрой от 1 до 10");
                    edittext1.setText("");
                    return;
                }

                else {
                    textInputLayout.setErrorEnabled(false);
                    int note = Integer.parseInt(edittext1.getText().toString());
                    ContentValues cv = new ContentValues();
                    db = databaseHelper.getWritableDatabase();

                    cv.put(DBHelper.PIE_VALUE, note);
                    db.update(DBHelper.PIE, cv, DBHelper.PIE_ID + "=4", null);
                    edittext1.setText("");
                    db.close();
                }
            }
        });

        love = view.findViewById(R.id.Love);
        love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edittext1.getText().toString().isEmpty() || Integer.parseInt(edittext1.getText().toString()) > 10 || Integer.parseInt(edittext1.getText().toString()) < 1) {
                    textInputLayout.setErrorEnabled(true);
                    textInputLayout.setError("Заполните поле цифрой от 1 до 10");
                    edittext1.setText("");
                    return;
                }

                else {
                    textInputLayout.setErrorEnabled(false);
                    int note = Integer.parseInt(edittext1.getText().toString());
                    ContentValues cv = new ContentValues();
                    db = databaseHelper.getWritableDatabase();

                    cv.put(DBHelper.PIE_VALUE, note);
                    db.update(DBHelper.PIE, cv, DBHelper.PIE_ID + "=5", null);
                    edittext1.setText("");
                    db.close();
                }
            }
        });

        money = view.findViewById(R.id.Money);
        money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edittext1.getText().toString().isEmpty() || Integer.parseInt(edittext1.getText().toString()) > 10 || Integer.parseInt(edittext1.getText().toString()) < 1) {
                    textInputLayout.setErrorEnabled(true);
                    textInputLayout.setError("Заполните поле цифрой от 1 до 10");
                    edittext1.setText("");
                    return;
                }

                else {
                    textInputLayout.setErrorEnabled(false);
                    int note = Integer.parseInt(edittext1.getText().toString());
                    ContentValues cv = new ContentValues();
                    db = databaseHelper.getWritableDatabase();

                    cv.put(DBHelper.PIE_VALUE, note);
                    db.update(DBHelper.PIE, cv, DBHelper.PIE_ID + "=6", null);
                    edittext1.setText("");
                    db.close();
                }
            }
        });

        career_growth = view.findViewById(R.id.Career_growth);
        career_growth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edittext1.getText().toString().isEmpty() || Integer.parseInt(edittext1.getText().toString()) > 10 || Integer.parseInt(edittext1.getText().toString()) < 1) {
                    textInputLayout.setErrorEnabled(true);
                    textInputLayout.setError("Заполните поле цифрой от 1 до 10");
                    edittext1.setText("");
                    return;
                }

                else {
                    textInputLayout.setErrorEnabled(false);
                    int note = Integer.parseInt(edittext1.getText().toString());
                    ContentValues cv = new ContentValues();
                    db = databaseHelper.getWritableDatabase();

                    cv.put(DBHelper.PIE_VALUE, note);
                    db.update(DBHelper.PIE, cv, DBHelper.PIE_ID + "=7", null);
                    edittext1.setText("");
                    db.close();
                }
            }
        });

        return view;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.top_menu_addkoleso, menu);
        getActivity().setTitle("Добавить");
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_koleso:
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, new HomeFragment() );
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                Toast.makeText(getActivity().getApplicationContext(), "Колесо обновлено!", Toast.LENGTH_LONG).show();
                break;
        }

        return super.onOptionsItemSelected(item);

    }
}
