package com.android.qin;


import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.peter.notificationdemo.NotifyMainActivity;
import com.android.qin.one.ApkInstallPermissionActivity;
import com.android.qin.one.RequestPermissionActivity;
import com.android.qin.util.ToastUtil;


/**
 * A simple {@link Fragment} subclass.
 */
public class OneFragment extends Fragment {

    View mContentView;

    public OneFragment() {
        // Required empty public constructor
    }

    int i = -1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

/*        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if(getActivity() == null){
                        return;
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Button btn = mContentView.findViewById(R.id.test8);
                            btn.setText(""+i++);
                        }
                    });
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();*/

        if (mContentView == null) {
            mContentView = inflater.inflate(R.layout.fragment_one, container, false);
            mContentView.findViewById(R.id.test1).setOnClickListener(v -> {
                startActivity(new Intent(getActivity(), ApkInstallPermissionActivity.class));
            });
            mContentView.findViewById(R.id.test2).setOnClickListener(v -> {
                startActivity(new Intent(getActivity(), RequestPermissionActivity.class));
            });
            mContentView.findViewById(R.id.test3).setOnClickListener(v -> {

                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                ComponentName cn = new ComponentName("com.hismart.easylink", "com.hismart.easylink.launch.activity.StartupActivity");
                intent.setComponent(cn);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                startActivity(intent);
            });
            mContentView.findViewById(R.id.test4).setOnClickListener(v -> {
                Intent intent = new Intent();
/*                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);*/
                ComponentName cn = new ComponentName("com.hismart.easylink", "com.hismart.easylink.launch.activity.StartupActivity");
                intent.setComponent(cn);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                startActivity(intent);
            });

            mContentView.findViewById(R.id.test5).setOnClickListener(v -> {
                        PackageManager packageManager = getActivity().getPackageManager();
                        Intent intent =packageManager.getLaunchIntentForPackage("com.hismart.easylink");
                        if(intent==null){
                            ToastUtil.showShortToast("未安装");
                        }else {
                            startActivity(intent);
                        }

            });

            mContentView.findViewById(R.id.test6).setOnClickListener(v -> {
                getActivity().startActivity(new Intent(getActivity(), NotifyMainActivity.class));

            });

            mContentView.findViewById(R.id.test7).setOnClickListener(v -> {
                getActivity().startActivity(new Intent(getActivity(), HisenseMallActivity.class));

            });

            mContentView.findViewById(R.id.test8).setOnClickListener(v -> {
                //getActivity().startActivity(new Intent(getActivity(), JdMallActivity.class));

                try {
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                    Bundle bundle = new Bundle();
                    bundle.putString("searchKey","123");
                    bundle.putString("spuCode","1234");
                    intent.putExtras(bundle);
                    ComponentName cn = new ComponentName("com.hismart.easylink", "com.hismart.easylink.launch.activity.SplashActivity");
                    intent.setComponent(cn);
                    getActivity().startActivity(intent);
/*                    PackageManager packageManager = context.getPackageManager();
                    intent = new Intent();
                    intent = packageManager.getLaunchIntentForPackage("com.panxsoft.xiaojingxiuxiu");
                    if (intent == null) {
                        ToastUtil.showShortToast("没有找到着装页面");
                    } else {
                        context.startActivity(intent);
                    }*/
                } catch (Exception e) {
                    Log.e("qwd", ""+Log.getStackTraceString(e));
                    e.printStackTrace();
                    ToastUtil.showShortToast("没有找到美妆页面.");
                }
            });
        }


        // 缓存View判断是否含有parent, 如果有需要从parent删除, 否则发生已有parent的错误.
        ViewGroup parent = (ViewGroup) mContentView.getParent();
        if (parent != null) {
            parent.removeView(mContentView);
        }
        return mContentView;
    }

}
