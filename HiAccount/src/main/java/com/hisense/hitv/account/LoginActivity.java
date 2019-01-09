package com.hisense.hitv.account;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.hismart.base.BaseToolbarCompatActivity;
import com.hismart.base.LogUtil;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends BaseToolbarCompatActivity {

    private final static String TAG = "LoginActivity";
    private EditText ed_username;
    private EditText ed_password;
    private TextView tx_findpwd;
    private TextView tx_register;

    private ImageView btn_wechat_login;
    private ImageView btn_sinablog_login;
    private Button btn_login;

    private ImageButton btn_pwd_dis;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setLeftButtonIsBack(false);
        setMiddleTitle(R.string.title_activity_login);
        setContentView(R.layout.activity_login);
        findViews();
    }

    private void findViews() {

        ed_username = findViewById(R.id.username);
        ed_password = findViewById(R.id.password);
        ed_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        tx_findpwd = findViewById(R.id.forget_password);
        tx_register = findViewById(R.id.register_now);
        tx_register.setOnClickListener(v -> {
            LogUtil.d(TAG, "Prepare to register");
            Intent it_register;
            it_register = new Intent(this.getApplicationContext(), RegisterMobileActivity.class);
            LoginActivity.this.startActivity(it_register);
        });
        btn_login = findViewById(R.id.btn_login);
        btn_pwd_dis = findViewById(R.id.btn_pwd_display);
        btn_wechat_login = findViewById(R.id.img_wechat);
        btn_sinablog_login = findViewById(R.id.img_sina_blog);

    }

    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    protected void processMessage(Message msg) {

    }

}