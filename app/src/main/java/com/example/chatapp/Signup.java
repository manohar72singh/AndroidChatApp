package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chatapp.database.User;
import com.example.chatapp.database.UserDAO;
import com.example.chatapp.ui.home.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {
    private EditText email;
    private EditText password,name,mob;
    private Button signup;
    private FirebaseAuth mAuth;
    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        email = findViewById(R.id.emailEditText);
        password = findViewById(R.id.passwordEditText);
        signup = findViewById(R.id.signupButton);
        name = findViewById(R.id.nameTextView);
        mob = findViewById(R.id.mobailEditText);
        userDAO = new UserDAO();
        mAuth = FirebaseAuth.getInstance();
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup.setEnabled(false);
                if(TextUtils.isEmpty(email.getText().toString()))
                {
                    email.setError("Enetr Email");
                }

                else
                    if (TextUtils.isEmpty(password.getText().toString()))
                {
                    password.setError("Enter password");
                }
                else
                    {
                        mAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful())
                                {
                                    userDAO.insert(new User(name.getText().toString(),mAuth.getUid(),email.getText().toString(),mob.getText().toString()),mAuth.getUid()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful())
                                            {
                                                Toast.makeText(getApplicationContext(),"Data Saved ",Toast.LENGTH_SHORT).show();
                                            }
                                            else
                                            {
                                                Toast.makeText(getApplicationContext(),task.getException().getMessage() ,Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                    //NOTIFICITION
                                   NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),MainActivity.CHANNEL_ID)
                                            .setSmallIcon(R.drawable.ic_launcher_foreground)
                                            .setContentTitle("Massenger")
                                            .setContentText("You have Register Successfully")
                                            .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                                    NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
                                    notificationManagerCompat.notify(1,builder.build());

                                    Intent intent = new Intent(Signup.this, MainActivity.class);
                                    startActivity(intent);
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                    signup.setEnabled(true);
                                }

                            }
                        });
                    }
            }
        });
    }

}