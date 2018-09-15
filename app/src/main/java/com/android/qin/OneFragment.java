package com.android.qin;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.qin.one.ApkInstallPermissionActivity;
import com.android.qin.one.RequestPermissionActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class OneFragment extends Fragment {

    View mContentView;

    public OneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mContentView == null) {
            mContentView = inflater.inflate(R.layout.fragment_one, container, false);
            mContentView.findViewById(R.id.test1).setOnClickListener(v -> {
                startActivity(new Intent(getActivity(), ApkInstallPermissionActivity.class));
            });
            mContentView.findViewById(R.id.test2).setOnClickListener(v -> {
                startActivity(new Intent(getActivity(), RequestPermissionActivity.class));
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
