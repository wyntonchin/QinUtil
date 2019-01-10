package com.hisense.hitv.account;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.hisense.hitv.account.LoginActivity;
import com.hisense.hitv.account.R;
import com.hismart.base.BaseToolbarCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
public class RegisterMobileActivity extends BaseToolbarCompatActivity {

    private final static String TAG = "MS_HISCTRL_REG";
    private EditText ed_phone;

    private TextView tx_privacy;
    private TextView tx_aggreement;
    private ImageView img_name_canuse;

    private Button btn_next;
    private ImageButton btn_contract;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setLeftButtonIsBack(true);
        setContentView(R.layout.activity_register_mobile_num);

        findViews();
        setViewClickListeners();
    }

    @Override
    protected void pressToolbarNavigation() {
        Intent it = new Intent(this.getApplicationContext(), LoginActivity.class);
        it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        this.startActivity(it);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        //Utils.Log.d(TAG, "OnResume");
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void finish() {
        super.finish();
    }

    private void findViews() {

        ed_phone = (EditText) findViewById(R.id.phone_number);

        img_name_canuse = (ImageView) findViewById(R.id.img_check_name);
        img_name_canuse.setVisibility(View.INVISIBLE);

        btn_next = (Button) findViewById(R.id.btn_next);
        btn_contract = (ImageButton) findViewById(R.id.btn_contract);

        ed_phone.setFocusable(true);
        tx_privacy = (TextView) findViewById(R.id.tx_privacy);
        tx_aggreement = (TextView) findViewById(R.id.agreement);
    }

    private void setViewClickListeners() {
        ed_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private boolean validatePhoneLocally(String phone) {
        String regEx = "^1[0-9]{10}$";

        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }


}
