package com.example.chatapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapp.R;
import com.example.chatapp.database.User;

import java.util.ArrayList;
import java.util.List;

public class AllAuthenticatatedUserRecyclerViewAdapter extends RecyclerView.Adapter<AllAuthenticatatedUserRecyclerViewAdapter.ViewHolder> {
    private ArrayList<User> users;

    public AllAuthenticatatedUserRecyclerViewAdapter(ArrayList<User> users)
    {
        this.users=users;
    }

    @NonNull
    @Override
    public AllAuthenticatatedUserRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_authenticated_user,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllAuthenticatatedUserRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.set(users.get(position));
        User user=users.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),user.getName() , Toast.LENGTH_SHORT).show();

                // SEND NAME OR ID CHATROOM FRAGMENT
                Bundle bundle = new Bundle();
                String name = user.getName();
                String  userId=user.getUuid();
                bundle.putString("cName",name);
                bundle.putString("uUid",userId);
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_allAuthenticatedUser_to_chatRoom,bundle);
            }
        });
    }


    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name,mob;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameTextView);
            mob = itemView.findViewById(R.id.mobailEditText);

        }
        public void set(User user)
        {
            name.setText(user.getName());
            mob.setText(user.getMob());
        }
    }

}
