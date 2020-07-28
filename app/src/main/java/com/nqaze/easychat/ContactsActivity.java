package com.nqaze.easychat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.cometchat.pro.constants.CometChatConstants;
import com.cometchat.pro.models.User;

import constant.StringContract;
import listeners.OnItemClickListener;
import screen.CometChatUserListScreen;
import screen.messagelist.CometChatMessageListActivity;

public class ContactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        CometChatUserListScreen.setItemClickListener(new OnItemClickListener<User>() {
            @Override
            public void OnItemClick(User var, int position) {
                Intent intent = new Intent(ContactsActivity.this, CometChatMessageListActivity.class);
                intent.putExtra(StringContract.IntentStrings.UID, var.getUid());
                intent.putExtra(StringContract.IntentStrings.NAME, var.getName());
                intent.putExtra(StringContract.IntentStrings.AVATAR, var.getAvatar());
                intent.putExtra(StringContract.IntentStrings.TYPE, CometChatConstants.RECEIVER_TYPE_USER);
                startActivity(intent);
            }

            @Override
            public void OnItemLongClick(User var, int position) {
                super.OnItemLongClick(var, position);
            }
        });
    }
}
