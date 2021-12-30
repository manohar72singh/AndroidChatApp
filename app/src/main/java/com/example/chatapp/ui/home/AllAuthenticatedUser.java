package com.example.chatapp.ui.home;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chatapp.R;
import com.example.chatapp.database.User;
import com.example.chatapp.database.UserDAO;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class AllAuthenticatedUser extends Fragment {
private RecyclerView allUserRecyclerView;
private UserDAO userDAO;
private ProgressDialog progressDialog;



    public AllAuthenticatedUser() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_authenticated_user, container, false);
    }
    @Override

    public void onViewCreated(@NotNull View view, Bundle bundle)
    {
        allUserRecyclerView = view.findViewById(R.id.showAllAuthenticateUserRecyclerView);
        allUserRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        userDAO = new UserDAO();
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("PLESE WAIT");
        progressDialog.show();
        userDAO.getAll().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                ArrayList user =new ArrayList<User>();
                for (DataSnapshot data :snapshot.getChildren())
                {
                   user.add(data.getValue(User.class));
                }
                AllAuthenticatatedUserRecyclerViewAdapter allUser =new AllAuthenticatatedUserRecyclerViewAdapter(user);
                allUserRecyclerView.setAdapter(allUser);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}