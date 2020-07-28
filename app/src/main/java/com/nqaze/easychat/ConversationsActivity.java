package com.nqaze.easychat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.cometchat.pro.constants.CometChatConstants;
import com.cometchat.pro.models.Conversation;
import com.cometchat.pro.models.Group;
import com.cometchat.pro.models.User;

import constant.StringContract;
import listeners.OnItemClickListener;
import screen.CometChatConversationListScreen;
import screen.messagelist.CometChatMessageListActivity;

public class ConversationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversations);

        CometChatConversationListScreen.setItemClickListener(new OnItemClickListener<Conversation>() {
            @Override
            public void OnItemClick(Conversation var, int position) {
                User user = (User)var.getConversationWith();
                Intent intent = new Intent(ConversationsActivity.this, CometChatMessageListActivity.class);
                intent.putExtra(StringContract.IntentStrings.UID, user.getUid());
                intent.putExtra(StringContract.IntentStrings.NAME, user.getName());
                intent.putExtra(StringContract.IntentStrings.AVATAR, user.getAvatar());
                intent.putExtra(StringContract.IntentStrings.TYPE, CometChatConstants.RECEIVER_TYPE_USER);
                startActivity(intent);
            }

            @Override
            public void OnItemLongClick(Conversation var, int position) {
                super.OnItemLongClick(var, position);
            }
        });
    }

    public void newChatTapped(View view) {
        startActivity(new Intent(ConversationsActivity.this, ContactsActivity.class));
    }
}
