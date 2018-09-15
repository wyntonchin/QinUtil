package com.android.qin.one;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.android.qin.R;
import com.android.qin.base.BaseCompatActivity;
import com.android.qin.util.LogUtil;
import com.android.qin.util.ToastUtil;

public class RequestPermissionActivity extends BaseCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_permission);

        findViewById(R.id.check_rationale).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        findViewById(R.id.check_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermissions(new String[]{
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.BLUETOOTH,
                        Manifest.permission.WRITE_CONTACTS,
                        Manifest.permission.CAMERA,
                });
            }
        });
    }

    @Override
    protected void onCheckPermissionsResult(boolean isSuccess) {
        super.onCheckPermissionsResult(isSuccess);
        ToastUtil.showLongToast("结果:" + isSuccess);
        LogUtil.i("结果:" + isSuccess);
/*        if (!isSuccess) {
            //没有打开需要的权限,则弹出对话框
            new AlertDialog.Builder(this)
                    .setTitle("帮助提醒")
                    .setMessage("应用需要的必要权限已被禁用,去设置中开启权限列表")
                    // 拒绝, 退出应用
                    .setNegativeButton(android.R.string.cancel,
                            (dialog, which) -> finish())

                    .setPositiveButton(android.R.string.ok,
                            (dialog, which) -> {
                                Uri packageURI = Uri.parse("package:" + RequestPermissionActivity.this.getPackageName());
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                                startActivity(intent);
                            })
                    .setCancelable(false)
                    .show();
        }*/
    }

    @Override
    protected void onPermissionGranted(String permission) {
        super.onPermissionGranted(permission);
        ToastUtil.showLongToast("成功:" + permission);
        LogUtil.i("成功:" + permission);
    }

    @Override
    protected void onPermissionFailed(String permission) {
        super.onPermissionFailed(permission);
        ToastUtil.showLongToast("失败:" + permission);
        LogUtil.i("失败:" + permission);
    }
}
