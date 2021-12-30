package com.example.chatapp.database;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class UserDAO {

DatabaseReference databaseReference ;

    public UserDAO()
    {
        databaseReference = FirebaseDatabase.getInstance().getReference(User.class.getSimpleName());

    }
    public Task<Void> insert(User user,String uid)
    {
        return databaseReference .child(uid).setValue(user);
    }
    public Query getAll()
    {
        return databaseReference.orderByKey();
    }
}
