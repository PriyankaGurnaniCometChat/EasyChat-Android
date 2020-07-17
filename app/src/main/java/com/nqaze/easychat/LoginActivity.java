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

    private TextInputLayout inputUid,inputName;
    private TextInputEditText mobile;
    private TextInputEditText name;
    private MaterialButton createUserBtn;
    private ProgressBar progressBar;
    private TextView title;
    private TextView des1,des2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputUid = findViewById(R.id.inputNumber);
        inputName = findViewById(R.id.inputName);
        progressBar = findViewById(R.id.createUser_pb);

        mobile = findViewById(R.id.etMobile);
        name = findViewById(R.id.etName);
        createUserBtn = findViewById(R.id.create_user_btn);
        createUserBtn.setTextColor(getResources().getColor(R.color.textColorWhite));

        createUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInTapped();
            }
        });
    }


    private void signInTapped(){


        User user = new User();
        user.setUid(name.getText().toString().charAt(0) + mobile.getText().toString());
        user.setName(name.getText().toString());

//        registerUser(user);
        login(user);
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
                Toast.makeText(LoginActivity.this,"Failed to create user",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void login(User user) {
        progressBar.setVisibility(View.VISIBLE);
        CometChat.login(user.getUid(), AppConfig.AppDetails.API_KEY, new CometChat.CallbackListener<User>() {
            @Override
            public void onSuccess(User user) {
                progressBar.setVisibility(View.GONE);
                Log.d("LoginActivity", "Login Successful");
                startActivity(new Intent(LoginActivity.this, ConversationsActivity.class));
            }

            @Override
            public void onError(CometChatException e) {
                progressBar.setVisibility(View.GONE);
                Log.d("LoginActivity", "Login Error");
            }
        });
    }
}
