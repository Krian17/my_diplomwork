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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ToDoListFragment extends Fragment {

    ListView userList;
    DBHelper databaseHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_todolist, container, false);

        setHasOptionsMenu(true);

        userList = view.findViewById(R.id.list);
        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AddListFragment yfc = new AddListFragment();

                Bundle bundle = new Bundle();
                bundle.putLong("id", id);

                yfc.setArguments(bundle);

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, yfc );
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        databaseHelper = new DBHelper(getActivity().getApplicationContext());

        return view;

    }
    @Override
    public void onResume() {
        super.onResume();

        db = databaseHelper.getReadableDatabase();

        userCursor =  db.rawQuery("select * from "+ DBHelper.TABLE, null);
        String[] headers = new String[] {DBHelper.COLUMN_NAME, DBHelper.COLUMN_YEAR};
        userAdapter = new SimpleCursorAdapter(getActivity().getApplicationContext(), R.layout.item_list,
                userCursor, headers, new int[]{R.id.item_list_name, R.id.item_list_date}, 0);
        userList.setAdapter(userAdapter);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        db.close();
        userCursor.close();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.top_menu_addlist, menu);
        getActivity().setTitle("Добавить");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_list:
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, new AddListFragment() );
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}