package com.android.qin;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.qin.threadpool.PriorityExecutor;
import com.android.qin.threadpool.PriorityRunnable;
import com.android.qin.threadpool.PriorityRunnable.Priority;
import com.android.qin.two.HomeSelectorActivity;
import com.android.qin.util.ToastUtil;

import java.util.concurrent.ExecutorService;


/**
 * A simple {@link Fragment} subclass.
 */
public class TwoFragment extends Fragment {
    private final static String TAG = "TwoFragment";
    View mContentView;

    public TwoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mContentView == null) {
            mContentView = inflater.inflate(R.layout.fragment_two, container, false);
            mContentView.findViewById(R.id.test1).setOnClickListener(v -> {
                testThreadPool();
            });

            mContentView.findViewById(R.id.test2).setOnClickListener(v -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("23423").setMessage("2w3rrrrrrrrrasdfasfasfasfaf");
                builder.show();
            });

            mContentView.findViewById(R.id.test3).setOnClickListener(v -> {
                startActivity(new Intent(getActivity(), HomeSelectorActivity.class));
            });
        }

        // 缓存View判断是否含有parent, 如果有需要从parent删除, 否则发生已有parent的错误.
        ViewGroup parent = (ViewGroup) mContentView.getParent();
        if (parent != null) {
            parent.removeView(mContentView);
        }
        return mContentView;
    }

    void testThreadPool(){
        ExecutorService executorService = new PriorityExecutor(5, false);
        for (int i = 0; i < 10020; i++) {
            PriorityRunnable priorityRunnable = new PriorityRunnable(Priority.NORMAL, new Runnable() {
                @Override
                public void run() {
                    Log.e(TAG, Thread.currentThread().getName()+"优先级正常");
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showShortToast("Thread.currentThread().getName()+\"优先级正常\"");
                        }
                    });
                }
            });
            if (i % 3 == 1) {
                priorityRunnable = new PriorityRunnable(Priority.HIGH, new Runnable() {
                    @Override
                    public void run() {
                        Log.e(TAG, Thread.currentThread().getName()+"优先级高");
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtil.showShortToast("Thread.currentThread().getName()+\"优先级高\"");
                            }
                        });
                    }
                });
            } else if (i % 5 == 0) {
                priorityRunnable = new PriorityRunnable(Priority.LOW, new Runnable() {
                    @Override
                    public void run() {
                        Log.e(TAG, Thread.currentThread().getName()+"优先级低");
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtil.showShortToast("Thread.currentThread().getName()+\"优先级低\"");
                            }
                        });
                    }
                });
            }
            executorService.execute(priorityRunnable);
        }
    }

}
