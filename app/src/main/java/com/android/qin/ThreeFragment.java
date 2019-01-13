package com.android.qin;


import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.android.qin.one.ApkInstallPermissionActivity;
import com.android.qin.one.RequestPermissionActivity;
import com.android.qin.util.ToastUtil;
import com.hisense.hitv.account.LoginActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThreeFragment extends Fragment {

    View mContentView;

    public ThreeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mContentView == null) {
            mContentView = inflater.inflate(R.layout.fragment_three, container, false);
            mContentView.findViewById(R.id.test1).setOnClickListener(v -> {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            });
            mContentView.findViewById(R.id.test2).setOnClickListener(v -> {

                //splashactivity
                ARouter.getInstance().build("/splash/splash").navigation();
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
