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

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText mobile;
    private MaterialButton loginBtn;
    private MaterialButton signupBtn;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressBar = findViewById(R.id.createUser_pb);
        mobile = findViewById(R.id.etMobile);
        signupBtn = findViewById(R.id.create_user_btn);
        loginBtn = findViewById(R.id.login_user_btn);
        loginBtn.setTextColor(getResources().getColor(R.color.textColorWhite));
        loginBtn.setOnClickListener(v ->
                signInTapped()
        );
        signupBtn.setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class))
        );
    }

    private void signInTapped(){
        User user = new User();
        user.setUid(mobile.getText().toString());
        login(user);
    }

    private void login(User user) {
        progressBar.setVisibility(View.VISIBLE);
        CometChat.login(user.getUid(), AppConfig.AppDetails.API_KEY, new CometChat.CallbackListener<User>() {
            @Override
            public void onSuccess(User user) {
                progressBar.setVisibility(View.GONE);
                startActivity(new Intent(LoginActivity.this, ConversationsActivity.class));
            }
            @Override
            public void onError(CometChatException e) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(LoginActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


}
