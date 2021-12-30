package com.example.chatapp.ui.home;

import android.util.TimeUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapp.R;
import com.example.chatapp.firebase.Chat;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ChatRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Chat> chats;
    private String currentUser;

    public ChatRecyclerViewAdapter(ArrayList<Chat> chats,String currentUser)
    {
        this.chats=chats;
        this.currentUser=currentUser;
    }

    @Override
    public int getItemViewType(int position)
    {
        if (chats.get(position).isSenderMessage(currentUser))
            return 1;
        else
            return 2;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if (viewType==1)
        {
            view=LayoutInflater.from(parent.getContext()).inflate(R.layout.sender_recycler_view_item_layout,parent,false);
            return new SenderViewHolder(view);
        }
        else
        {
            view=LayoutInflater.from(parent.getContext()).inflate(R.layout.receiver_recycler_view_item_layout,parent,false);
            return  new ReceiverViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (chats.get(position).isSenderMessage(currentUser))
        {
            ((SenderViewHolder)holder).setChat(chats.get(position));
        }
        else
            ((ReceiverViewHolder)holder).setChat(chats.get(position));
    }

    @Override
    public int getItemCount() {
        if (chats==null)
            return 0;
        else
            return chats.size();
    }

    public class SenderViewHolder extends RecyclerView.ViewHolder {
        TextView massage,time,date;
        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);
            massage=itemView.findViewById(R.id.senderRecyclerViewLayoutSenderMassageTextView);
            date = itemView.findViewById(R.id.senderRecyclerViewLayoutSenderDateTextView);
            time = itemView.findViewById(R.id.senderRecyclerViewShowTimeTextView);
        }
        public void setChat(Chat chat)
        {
            massage.setText(chat.getMassage());
            SimpleDateFormat sdfDate = new SimpleDateFormat("dd MMMM yyyy");
            date.setText(sdfDate.format(chat.getSendingTime()));
            //date.setText(chat.getSendingTime()+"");
            SimpleDateFormat sdfTime = new SimpleDateFormat("hh:mm");
            time.setText(sdfTime.format(chat.getSendingTime()));
            //time.setText(chat.getSendingTime()+"");

        }

    }
    public class ReceiverViewHolder extends RecyclerView.ViewHolder{
        TextView massage,time,date;
        public ReceiverViewHolder(@NonNull View itemView) {
            super(itemView);
            massage=itemView.findViewById(R.id.receiverRecyclerViewLayoutShowReceiverMassageTextView);
            date = itemView.findViewById(R.id.receiverRecyclerViewLayoutShowDateTextView);
            time = itemView.findViewById(R.id.receiverRecyclerViewLayoutShowTimeTextView);
        }
        public void setChat(Chat chat)
        {
            massage.setText(chat.getMassage());
            SimpleDateFormat sdfDate = new SimpleDateFormat("dd MMMM yyyy");
            date.setText(sdfDate.format(chat.getSendingTime()));
            SimpleDateFormat sdfTime = new SimpleDateFormat("hh:mm");
            time.setText(sdfTime.format(chat.getSendingTime()));
            //date.setText(chat.getSendingTime()+"");
            //time.setText(chat.getSendingTime()+"");
        }
    }
}
