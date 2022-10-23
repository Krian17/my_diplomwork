package com.example.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChatFragment extends Fragment {

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference("massages");

    DBHelper databaseHelper;
    SQLiteDatabase db;
    Cursor cursor;
    public String name;

    private FirebaseListAdapter<ChatMessage> adapter;
    private static int MAX_LENGTH = 100;
    private ImageButton sendbtn;
    EditText textfield;
    TextInputLayout inputLayout;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        databaseHelper = new DBHelper(getActivity().getApplicationContext());
        db = databaseHelper.getReadableDatabase();

        cursor = db.rawQuery("select * from " + DBHelper.USER_NAME, null);
        cursor.moveToFirst();
        name = cursor.getString(cursor.getColumnIndex(DBHelper.NAME_US));
        db.close();

        textfield = view.findViewById(R.id.messageField);
        inputLayout = view.findViewById(R.id.textInputLayout2);

        sendbtn = view.findViewById(R.id.btnSend);
        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String msg = textfield.getText().toString();

                if (msg.isEmpty() || msg.length() > MAX_LENGTH) {
                    inputLayout.setErrorEnabled(true);
                    inputLayout.setError("Введите сообщение короче 100 символов!");
                    textfield.setText("");
                }
                else {
                    inputLayout.setErrorEnabled(false);
                    databaseReference.push().setValue(new ChatMessage(msg, name));
                    textfield.setText("");
                }
            }
        });

        ListView listOfMessages = view.findViewById(R.id.listMassages);

        adapter = new FirebaseListAdapter<ChatMessage>(getActivity(), ChatMessage.class,
                R.layout.item_message, databaseReference) {

            @Override
            protected void populateView(View v, ChatMessage model, int position) {
                TextView messageText = v.findViewById(R.id.message_text);
                TextView messageUser = v.findViewById(R.id.message_user);
                TextView messageTime = v.findViewById(R.id.message_time);

                messageText.setText(model.getMessageText());
                messageUser.setText(model.getMessageUser());

                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
                        model.getMessageTime()));
            }
        };

        listOfMessages.setAdapter(adapter);

        return view;
    }
}