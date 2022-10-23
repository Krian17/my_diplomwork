package com.example.myapplication;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.material.textfield.TextInputLayout;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddListFragment extends Fragment {

    EditText nameBox;
    TextView yearBox;
    TextView date;
    Button delButton;
    Button saveButton;
    DBHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    long userId=0;
    TextInputLayout textInputLayout1;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_list, container, false);

        date = view.findViewById(R.id.date);

        CalendarView calendarView = view.findViewById(R.id.cv_date);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                int mYear = year;
                int mMonth = month;
                int mDay = dayOfMonth;
                String selectedDay = new StringBuilder().append(mDay)
                        .append(".").append(mMonth+1)
                        .append(".").append(mYear)
                        .append(" ").toString();
                SimpleDateFormat sdf = new SimpleDateFormat("d.M.yyyy");
                String currentDateandTime = sdf.format(new Date());
                date.setText("c " + currentDateandTime +" по "+selectedDay);
            }
        });

        nameBox = view.findViewById(R.id.name);
        yearBox = view.findViewById(R.id.year);

        textInputLayout1 = view.findViewById(R.id.textInputLayout1);
        delButton = view.findViewById(R.id.deleteButton);

        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.delete(DBHelper.TABLE, "_id = ?", new String[]{String.valueOf(userId)});
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, new ToDoListFragment() );
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        saveButton = view.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nameBox.getText().toString().isEmpty() || date.getText().toString().isEmpty() ) {
                    textInputLayout1.setErrorEnabled(true);
                    textInputLayout1.setError("Заполните поле и выберите дату!");
                    yearBox.setError("Выберите дату!");
                }
                else {
                    ContentValues cv = new ContentValues();
                    cv.put(DBHelper.COLUMN_NAME, nameBox.getText().toString());
                    cv.put(DBHelper.COLUMN_YEAR, date.getText().toString());

                    if (userId > 0) {
                        db.update(DBHelper.TABLE, cv, DBHelper.COLUMN_ID + "=" + userId, null);
                    }
                    else {
                        db.insert(DBHelper.TABLE, null, cv);
                    }
                    db.close();

                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.nav_host_fragment, new ToDoListFragment());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            }
        });

        sqlHelper = new DBHelper(getActivity().getApplicationContext());
        db = sqlHelper.getWritableDatabase();

        Bundle bundle = getArguments();
            if (bundle != null) {
                userId = bundle.getLong("id");
            }

        if (userId > 0) {
            userCursor = db.rawQuery("select * from " + DBHelper.TABLE + " where " +
                    DBHelper.COLUMN_ID + "=?", new String[]{String.valueOf(userId)});
            userCursor.moveToFirst();

            nameBox.setText(userCursor.getString(1));
            date.setText(userCursor.getString(2));

            userCursor.close();
        }
        else {
            delButton.setVisibility(View.GONE);
        }

        return view;
    }

}
