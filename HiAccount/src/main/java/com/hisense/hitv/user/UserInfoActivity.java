package com.hisense.hitv.user;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hisense.hitv.account.HiServiceImpl;
import com.hisense.hitv.account.R;
import com.hisense.hitv.account.TokenManager;
import com.hisense.hitv.account.pool.PriorityRunnable;
import com.hisense.hitv.account.pool.ThreadPoolProxyFactory;
import com.hisense.hitv.hicloud.bean.account.ReplyInfo;
import com.hismart.base.BaseToolbarCompatActivity;
import com.hismart.base.LogUtil;
import com.hismart.base.ToastUtil;
import com.hismart.base.dialog.CommonCornerDialog;
import com.hismart.base.router.InfoCallback;
import com.hismart.base.router.RouterPath;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

@Route(path = RouterPath.HIACCOUNT_ACTIVITY_USERINFO)
public class UserInfoActivity extends BaseToolbarCompatActivity {

    private static final String TAG = "UserInfoActivity";

    public final static int TYPE_NAME_CODE = 10;
    public final static int TYPE_EMAIL_CODE = 11;
    public final static String EDIT_TYPE = "edit_type";
    public final static String EDIT_CONTENT = "edit_content";

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
    private int gender;
/*    private long birthday;
    private String email;
    private String nickName;*/
    //private Bitmap photo;
    private boolean isPhotoChanged = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLeftButtonIsBack(true);
        setMiddleTitle("我的资料");
        setContentView(R.layout.activity_user_info);
        findViews();

    }

    private void findViews() {
        ll_name = findViewById(R.id.account_user_name_rl);
        ll_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(UserInfoActivity.this, UserEditActivity.class);
                it.putExtra(EDIT_TYPE, TYPE_NAME_CODE);
                it.putExtra(EDIT_CONTENT, UserInfoManager.getInstance().getNickname());
                startActivityForResult(it, TYPE_NAME_CODE);
            }
        });
        ll_sex = findViewById(R.id.account_user_sex_rl);
        ll_sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSexChooseDialog();
            }
        });
        ll_date = findViewById(R.id.account_user_birth_rl);
        ll_email = findViewById(R.id.account_user_email_rl);
        ll_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(UserInfoActivity.this, UserEditActivity.class);
                it.putExtra(EDIT_TYPE, TYPE_EMAIL_CODE);
                it.putExtra(EDIT_CONTENT, UserInfoManager.getInstance().getEmail());
                startActivityForResult(it, TYPE_EMAIL_CODE);
            }
        });


        ll_pwd = findViewById(R.id.account_user_modify_pwd);


        img_photo = findViewById(R.id.img_photo);
        img_photo.setImageBitmap(UserInfoManager.getInstance().getPic());
        tx_nickname = findViewById(R.id.tx_name);
        tx_nickname.setText(UserInfoManager.getInstance().getNickname());
        tx_sex = findViewById(R.id.tx_sex);

        tx_sex.setText(UserInfoManager.getInstance().getGender() == 0 ?
                this.getResources().getString(R.string.hi_account_female) : this.getResources().getString(R.string.hi_account_male));
        gender = UserInfoManager.getInstance().getGender();

        tx_birthday = findViewById(R.id.tx_date);
        tx_birthday.setText(getBirthdayStr(UserInfoManager.getInstance().getBirthday()));
        tx_email = findViewById(R.id.tx_mail);
        tx_email.setText((UserInfoManager.getInstance().getEmail()));

        btn_logout = findViewById(R.id.account_btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonCornerDialog dialog = new CommonCornerDialog(UserInfoActivity.this);
                dialog.setTitle("");
                dialog.setMessage(R.string.hi_account_logout);
                dialog.setCanceledOnTouchOutside(false);
                dialog.setOnClickBottomListener(new CommonCornerDialog.OnClickBottomListener() {
                    @Override
                    public void onPositiveClick() {
                        doLogout();
                    }

                    @Override
                    public void onNegativeClick() {

                    }
                }).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        LogUtil.i(TAG, "onActivityResult requestCode="+requestCode+";resultCode = " + resultCode);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case TYPE_NAME_CODE:
                    String nickName = data.getStringExtra(EDIT_CONTENT);
                    tx_nickname.setText(nickName);
                    break;

                case TYPE_EMAIL_CODE:
                    String email = data.getStringExtra(EDIT_CONTENT);
                    tx_email.setText(email);
                    break;
                    default:
                        break;
            }
        }
    }

    @Override
    protected void pressToolbarNavigation() {
        handleBack();
        finish();
    }

    @Override
    public void onBackPressed() {
        handleBack();
        finish();
    }

    private void showSexChooseDialog() {
        String[] sexArry = new String[]{ this.getResources().getString(R.string.hi_account_male),this.getString(R.string.hi_account_female)};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.hi_account_gender);
        builder.setSingleChoiceItems(sexArry, gender == 0 ? 1:0, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {// which是被选中的位置
                gender = which == 0 ? 1 : 0;
                if(gender == 0){
                    //女
                    tx_sex.setText(sexArry[1]);
                }else {
                    tx_sex.setText(sexArry[0]);
                }
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void handleBack(){
        if(saveUserInfoIfChanged()){
            doPostUserInfo();
        }

        if(isPhotoChanged){
            UserInfoManager.getInstance().uploadUserPhoto(new InfoCallback() {
                @Override
                public void onSuccess(Object info) {

                }

                @Override
                public void onError(int code, String message) {

                }
            });
        }
    }


    private void doPostUserInfo(){
        showProgressDialog(false);
        PriorityRunnable priorityRunnable = new PriorityRunnable(PriorityRunnable.Priority.NORMAL, new Runnable() {
            @Override
            public void run() {
                LogUtil.w(TAG, "doPostUserInfo:" + Thread.currentThread().getName());
                HashMap<String, String> map = new HashMap<>();
                map.put("nickName", UserInfoManager.getInstance().getNickname());
                map.put("mobilePhone", UserInfoManager.getInstance().getMobile());
                map.put("sex", String.valueOf(UserInfoManager.getInstance().getGender()));
                map.put("birthday", String.valueOf(UserInfoManager.getInstance().getBirthday()));
                map.put("address", UserInfoManager.getInstance().getAddress());
                map.put("email",UserInfoManager.getInstance().getEmail());

                ReplyInfo replyInfo = HiServiceImpl.obtain().updateCustomerInfo(TokenManager.getInstance().getToken(), map);
                if (replyInfo != null && replyInfo.getReply() == 0) {
                    LogUtil.e(TAG, "doPostUserInfo success replyInfo:" + replyInfo.getReply());
                    runOnUiThread(() -> ToastUtil.showShortToast("更新成功"));
                } else {
                    LogUtil.e(TAG, "doPostUserInfo fail");
                    runOnUiThread(() -> ToastUtil.showShortToast("更新失败"));
                }
                runOnUiThread(() -> dismissProgressDialog());
            }
        });
        ThreadPoolProxyFactory.getNormal().execute(priorityRunnable);

    }

    private void doLogout() {
        showProgressDialog(false);
        PriorityRunnable priorityRunnable = new PriorityRunnable(PriorityRunnable.Priority.NORMAL, new Runnable() {
            @Override
            public void run() {
                LogUtil.w(TAG, "btn_login:" + Thread.currentThread().getName());
                //认证账号
                ReplyInfo replyInfo = HiServiceImpl.obtain().logout(TokenManager.getInstance().getToken(), HiServiceImpl.obtain().getDeviceId(UserInfoActivity.this));
                if (replyInfo != null && replyInfo.getReply() == 0) {
                    LogUtil.e(TAG, "doLogout success replyInfo:" + replyInfo.getReply());
                    runOnUiThread(() -> ToastUtil.showShortToast("登出成功"));
                } else {
                    LogUtil.e(TAG, "doLogout fail");
                    runOnUiThread(() -> ToastUtil.showShortToast("登出失败"));
                }
                runOnUiThread(() -> dismissProgressDialog());
            }
        });
        ThreadPoolProxyFactory.getNormal().execute(priorityRunnable);
    }

    private boolean saveUserInfoIfChanged() {
        UserInfoManager user = UserInfoManager.getInstance();
        if (!tx_nickname.getText().toString().equalsIgnoreCase(user.getNickname())) {
            LogUtil.d(TAG, "NickName differ");
            user.setNickname(tx_nickname.getText().toString());
            return true;
        }

        if (user.getGender() != gender) {
            LogUtil.d(TAG, "Sex differ");
            user.setGender(user.getGender());
            return true;
        }

        if (user.getBirthday() != parseBirthday(tx_birthday.getText().toString())) {
            LogUtil.d(TAG, "Birthday differ");
            user.setBirthday(parseBirthday(tx_birthday.getText().toString()));
            return true;
        }

        if (!tx_email.getText().toString().equalsIgnoreCase(user.getEmail())) {
            LogUtil.d(TAG, "Email differ");
            user.setEmail(tx_email.getText().toString());
            return true;
        }
        return false;
    }

    private long parseBirthday(String time) {
        long birthday = 0;

        SimpleDateFormat sDate = new SimpleDateFormat("yyyy-MM-dd");
        sDate.setTimeZone(TimeZone.getTimeZone("GMT"));

        String[] value = time.split("-");
        try {
            Date date = sDate.parse(value[0] + "-" + value[1] + "-" + value[2]);
            birthday = date.getTime() / 1000; // used for store user
            // info locally
            LogUtil.d(TAG, "Birthday:" + birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return birthday;
    }

    private String getBirthdayStr(Long curValue) {
        int year, month, day;
        if (curValue != 0) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date(curValue * 1000));
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH) + 1;
            day = calendar.get(Calendar.DAY_OF_MONTH);
        } else {
            year = 1970;
            month = 1;
            day = 1;
        }

        return year + "-" + month + "-" + day;
    }

}
