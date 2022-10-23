package com.example.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    DBHelper databaseHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    private TextView chart;
    public static PieChart pieChart;
    public static PieDataSet set;
    public static PieData data;
    public static ArrayList<PieEntry> entries;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        setHasOptionsMenu(true);

        databaseHelper = new DBHelper(getActivity().getApplicationContext());

        pieChart = view.findViewById(R.id.chart);

        entries = new ArrayList<>();

        db = databaseHelper.getReadableDatabase();

        userCursor = db.rawQuery("select * from " + DBHelper.PIE, null);
        userCursor.moveToFirst();
        int pie_value1 = userCursor.getInt(userCursor.getColumnIndex(DBHelper.PIE_VALUE));

        userCursor.moveToNext();
        int pie_value2 = userCursor.getInt(userCursor.getColumnIndex(DBHelper.PIE_VALUE));

        userCursor.moveToNext();
        int pie_value3 = userCursor.getInt(userCursor.getColumnIndex(DBHelper.PIE_VALUE));

        userCursor.moveToNext();
        int pie_value4 = userCursor.getInt(userCursor.getColumnIndex(DBHelper.PIE_VALUE));

        userCursor.moveToNext();
        int pie_value5 = userCursor.getInt(userCursor.getColumnIndex(DBHelper.PIE_VALUE));

        userCursor.moveToNext();
        int pie_value6 = userCursor.getInt(userCursor.getColumnIndex(DBHelper.PIE_VALUE));

        userCursor.moveToNext();
        int pie_value7 = userCursor.getInt(userCursor.getColumnIndex(DBHelper.PIE_VALUE));

        userCursor.close();

        entries.add(new PieEntry(pie_value1, "Семья"));
        entries.add(new PieEntry(pie_value2, "Вклад в наследие"));
        entries.add(new PieEntry(pie_value3, "Здоровье"));
        entries.add(new PieEntry(pie_value4, "Обучение и развитие"));
        entries.add(new PieEntry(pie_value5, "Любовь"));
        entries.add(new PieEntry(pie_value6, "Деньги"));
        entries.add(new PieEntry(pie_value7, "Карьерный рост"));

        set = new PieDataSet(entries, " ");
        data = new PieData(set);

        pieChart.setData(data);
        pieChart.invalidate();
        pieChart.animateX(700);

        set.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart.getDescription().setText(" ");

        return view;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.top_menu_home, menu);
        getActivity().setTitle("Добавить");
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.red_koleso:
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, new AddKolesoFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
