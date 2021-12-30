package com.example.chatapp.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.chatapp.R;
import com.example.chatapp.firebase.Chat;
import com.example.chatapp.firebase.ChatId;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;


public class ChatRoom extends Fragment {
    private TextView name;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private String p1ID,p2ID;
    private String chatId;
    private EditText message;
    private Button sendMessage;
    private DatabaseReference chatReference;
    private RecyclerView chatsRecyclerView;

    public ChatRoom() {
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
        return inflater.inflate(R.layout.fragment_chat_room, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle bundle=getArguments();
        chatsRecyclerView = view.findViewById(R.id.chatRoomRecyclerView);
        chatsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        String sname=bundle.getString("cName");
        p2ID=bundle.getString("uUid");
        name=view.findViewById(R.id.chatRoomshowNametextView);
        name.setText(sname);

        message=view.findViewById(R.id.newChatEditText);
        sendMessage = view.findViewById(R.id.sendMessage);

        //get chatID
        firebaseAuth=FirebaseAuth.getInstance();
        p1ID=firebaseAuth.getUid();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("chatid");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean found=false;
                for(DataSnapshot chatid: snapshot.getChildren())
                {
                    if(chatid.getValue(ChatId.class).match(p1ID,p2ID))
                    {
                        found=true;
                        chatId=chatid.getKey();
                        chatReference=FirebaseDatabase.getInstance().getReference().child("chat").child(chatId);
                        chatReference.addValueEventListener(new ValueEventListener() {

                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                ArrayList<Chat> chats = new ArrayList<Chat>();
                                for (DataSnapshot dataSnapshot:snapshot.getChildren())
                                {
                                    chats.add(dataSnapshot.getValue(Chat.class));
                                }
                                chatsRecyclerView.setAdapter(new ChatRecyclerViewAdapter(chats,p1ID));
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        //Toast.makeText(getActivity(),"Chat ID:"+chatId,Toast.LENGTH_SHORT).show();
                        break;
                    }

                }
                //if not found then add a new chat
                if(!found) {
                    long currentTime = new GregorianCalendar().getTime().getTime();
                    ChatId newChatID = new ChatId(p1ID, p2ID, currentTime);
                    databaseReference.push().setValue(newChatID);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(message.getText().toString().isEmpty())
                    return;

                //Create new chat
                sendMessage.setEnabled(false);
                Chat chat = new Chat(p1ID,p2ID,message.getText().toString(),new GregorianCalendar().getTime().getTime());
                chatReference.push().setValue(chat);
                message.setText("");
                sendMessage.setEnabled(true);
            }
        });


    }





}