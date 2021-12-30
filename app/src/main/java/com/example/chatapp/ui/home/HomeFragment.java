package com.example.chatapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.chatapp.MainActivity;
import com.example.chatapp.Profile;
import com.example.chatapp.R;
import com.example.chatapp.Signup;
import com.example.chatapp.databinding.FragmentHomeBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private Button logout;
    private Button allUser;
    private FirebaseAuth mAuth;
    


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        return root;

    }
    @Override
    public void onViewCreated(View view,Bundle bundle)
    {

        mAuth = FirebaseAuth.getInstance();
        allUser = view.findViewById(R.id.allUser);
        logout = view.findViewById(R.id.btnLogut);

        FirebaseUser user = mAuth.getCurrentUser();
        if(user==null)
        {
            Intent intent = new Intent(getContext(),Profile.class);
            startActivity(intent);
        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getContext(),Profile.class);
                startActivity(intent);
                Toast.makeText(getContext(),"Logout...",Toast.LENGTH_SHORT).show();

            }

        });
        allUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_nav_home_to_allAuthenticatedUser);
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}