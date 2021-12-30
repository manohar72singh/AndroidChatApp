package com.example.chatapp.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ChatDAO {
    DatabaseReference databaseReference;

    public ChatDAO()
    {
        databaseReference = FirebaseDatabase.getInstance().getReference(Chat.class.getSimpleName());

    }
    public Query getChat()
    {
        return databaseReference.orderByKey();
    }
}
