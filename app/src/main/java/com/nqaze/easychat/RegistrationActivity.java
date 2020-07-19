package com.nqaze.easychat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cometchat.pro.core.CometChat;
import com.cometchat.pro.exceptions.CometChatException;
import com.cometchat.pro.models.User;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegistrationActivity extends AppCompatActivity {

    private TextInputEditText mobile;
    private TextInputEditText name;
    private MaterialButton createUserBtn;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        progressBar = findViewById(R.id.createUser_pb);
        mobile = findViewById(R.id.etMobile);
        name = findViewById(R.id.etName);
        createUserBtn = findViewById(R.id.create_user_btn);
        createUserBtn.setTextColor(getResources().getColor(R.color.textColorWhite));
        createUserBtn.setOnClickListener(v ->
                signUpTapped()
        );
    }


    private void signUpTapped(){
        User user = new User();
        user.setUid(mobile.getText().toString());
//        user.setUid(name.getText().toString().charAt(0) + mobile.getText().toString());
        user.setName(name.getText().toString());
        registerUser(user);
    }

    private void registerUser(User user) {
        progressBar.setVisibility(View.VISIBLE);
        CometChat.createUser(user, AppConfig.AppDetails.API_KEY, new CometChat.CallbackListener<User>() {
            @Override
            public void onSuccess(User user) {
                progressBar.setVisibility(View.GONE);
                login(user);
            }
            @Override
            public void onError(CometChatException e) {
                progressBar.setVisibility(View.GONE);
                createUserBtn.setClickable(true);
                Toast.makeText(RegistrationActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void login(User user) {
        progressBar.setVisibility(View.VISIBLE);
        CometChat.login(user.getUid(), AppConfig.AppDetails.API_KEY, new CometChat.CallbackListener<User>() {
            @Override
            public void onSuccess(User user) {
                progressBar.setVisibility(View.GONE);
                startActivity(new Intent(RegistrationActivity.this, ConversationsActivity.class));
            }
            @Override
            public void onError(CometChatException e) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(RegistrationActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
