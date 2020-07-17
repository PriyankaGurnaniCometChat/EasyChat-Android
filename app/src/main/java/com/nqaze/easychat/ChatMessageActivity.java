package com.nqaze.easychat;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.cometchat.pro.constants.CometChatConstants;

import constant.StringContract;
import screen.messagelist.CometChatMessageScreen;

public class ChatMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_message);

        Intent intent = getIntent();
        String uid = intent.getStringExtra(StringContract.IntentStrings.UID);
        String name = intent.getStringExtra(StringContract.IntentStrings.NAME);
        String avatar = intent.getStringExtra(StringContract.IntentStrings.AVATAR);

        Bundle bundle = new Bundle();
        bundle.putString(StringContract.IntentStrings.UID,uid);
        bundle.putString(StringContract.IntentStrings.NAME, name);
        bundle.putString(StringContract.IntentStrings.AVATAR, avatar);
        bundle.putString(StringContract.IntentStrings.TYPE, CometChatConstants.RECEIVER_TYPE_USER);

        Fragment chatFragment=new CometChatMessageScreen();
        chatFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.chatFrame, chatFragment).commit();

    }
}
