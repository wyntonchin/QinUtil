package com.hisense.hitv.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hisense.hitv.account.R;
import com.hismart.base.BaseToolbarCompatActivity;
import com.hismart.base.router.RouterPath;

@Route(path = RouterPath.HIACCOUNT_ACTIVITY_USERINFO)
public class UserInfoActivity extends BaseToolbarCompatActivity {
    private CircleImageView img_photo;

    private RelativeLayout ll_name;
    private RelativeLayout ll_sex;
    private RelativeLayout ll_date;
    private RelativeLayout ll_email;
    private RelativeLayout ll_pwd;
    private Button btn_logout;
    private TextView tx_nickname;
    private TextView tx_sex;
    private TextView tx_birthday;
    private TextView tx_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setLeftButtonIsBack(true);
        setMiddleTitle("我的资料");
        setContentView(R.layout.activity_user_info);
        findViews();
    }

    private void findViews() {
        tx_nickname = findViewById(R.id.tx_name);
        tx_sex = findViewById(R.id.tx_sex);
        tx_birthday = findViewById(R.id.tx_date);
        tx_email = findViewById(R.id.tx_mail);

        img_photo = findViewById(R.id.img_photo);
        //img_photo.setImageBitmap();
        ll_name = findViewById(R.id.account_user_name_rl);
        ll_sex = findViewById(R.id.account_user_sex_rl);
        ll_date = findViewById(R.id.account_user_birth_rl);
        ll_email = findViewById(R.id.account_user_email_rl);
        ll_pwd = findViewById(R.id.account_user_modify_pwd);

        btn_logout = findViewById(R.id.account_btn_logout);

/*        nickName = user.getNickname();
        tx_nickname.setText(nickName);

        sex = user.getSex();
        if (user.getSex() == -1) {
            tx_sex.setText("");
        } else {
            LogUtil.d(TAG, "User:" + user.getSex());
            tx_sex.setText(user.getSex() == 0 ? this.getResources().getString(R.string.female)
                    : this.getResources().getString(R.string.male));
        }


        tx_birthday.setText(getBirthdayStr(user.getBirthday()));
        LogUtil.d(TAG, "Set birthday:" + getBirthdayStr(user.getBirthday()));

        //tx_email.setMovementMethod(new ScrollingMovementMethod());
        email = user.getEmail();
        tx_email.setText(email);
        LogUtil.d(TAG, "Set email:" + email);

        img_photo.setImageBitmap(user.getPhoto());*/
    }
}
