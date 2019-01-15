package com.hisense.hitv.user;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.hisense.hitv.account.R;
import com.hismart.base.BaseToolbarCompatActivity;
import com.hismart.base.ToastUtil;

import java.util.regex.Pattern;


public class UserEditActivity extends BaseToolbarCompatActivity {
    private static final String TAG = "UserEditActivity";


    private int mType = -1;
    private String mContent;

    private EditText ed_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLeftButtonIsBack(true);
        setContentView(R.layout.activity_user_edit);
        mType = getIntent().getIntExtra(UserInfoActivity.EDIT_TYPE, UserInfoActivity.TYPE_NAME_CODE);
        mContent = getIntent().getStringExtra(UserInfoActivity.EDIT_CONTENT);
        initContentView();
    }

    private void initContentView() {
        switch (mType) {
            case UserInfoActivity.TYPE_NAME_CODE:
                Log.d(TAG, "Load email page");
                ed_content = findViewById(R.id.tx_content);
                ed_content.setText(mContent);
                ed_content.setHint(R.string.hi_account_user_nick_name);
                setMiddleTitle("设置用户名");
                setRightText("保存");
                setRightTextOnClickListener(new ToolBarTextClickListener() {
                    @Override
                    public void onClick() {
                        String nickName = ed_content.getText().toString();
                        if (nickName.length() < 2) {
                            ToastUtil.showShortToast("最少2位字符");
                        } else {
                            if (validateNameLocally(nickName)) {
                                Intent intent = new Intent();
                                intent.putExtra(UserInfoActivity.EDIT_CONTENT, ed_content.getText().toString());
                                setResult(RESULT_OK, intent);
                                finish();
                            } else {
                                ToastUtil.showShortToast(R.string.hi_account_nick_name_policy);
                            }
                        }
                        finish();
                    }
                });
                break;
            case UserInfoActivity.TYPE_EMAIL_CODE:
                Log.d(TAG, "Load name page");
                ed_content = findViewById(R.id.tx_content);
                ed_content.setText(mContent);
                ed_content.setHint(R.string.hi_account_user_email);
                setMiddleTitle("设置邮箱");
                setRightText("保存");
                setRightTextOnClickListener(new ToolBarTextClickListener() {
                    @Override
                    public void onClick() {
                        Intent intent = new Intent();
                        intent.putExtra(UserInfoActivity.EDIT_CONTENT, ed_content.getText().toString());
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
                break;
            default:
                finish();
                break;
        }
    }


    private boolean validateNameLocally(String nickName) {

        Pattern patnNumber = Pattern.compile("[0-9]*");
        Pattern patnLetter = Pattern.compile("[a-zA-Z]");
        Pattern patnChinese = Pattern.compile("[\u4e00-\u9fa5]"); //Chinese

        for (int i = 0; i < nickName.length(); i++) {
            int j = i + 1;
            String nc = nickName.substring(i, j);
            Log.d(TAG, "Check character:" + nc);
            if (patnChinese.matcher(nc).matches() || patnLetter.matcher(nc).matches() ||
                    patnNumber.matcher(nc).matches()) {
                continue;
            } else {
                return false;
            }
        }

        return true;
    }
}
