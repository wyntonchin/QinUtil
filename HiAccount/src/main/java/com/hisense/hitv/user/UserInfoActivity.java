package com.hisense.hitv.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hisense.hitv.account.R;
import com.hismart.base.router.RouterPath;

@Route(path = RouterPath.HIACCOUNT_ACTIVITY_USERINFO)
public class UserInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
    }
}
