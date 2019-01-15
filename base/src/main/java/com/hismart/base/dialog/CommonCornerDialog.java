package com.hismart.base.dialog;

/**
 * @author qinwendong
 * @date 2018/12/5
 * descrption:
 */
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hismart.base.R;


/**
 * 圆角dialog
 */

public class CommonCornerDialog extends Dialog {

    /**
     * 显示的标题
     */
    private TextView titleTv ;

    /**
     * 显示的消息
     */
    private TextView messageTv ;

    /**
     * 确认和取消按钮
     */
    private Button negtiveBn ,positiveBn;

    /**
     * 按钮之间的分割线
     */
    private View columnLineView ;
    private Context mContext;
    public CommonCornerDialog(Context context) {
        super(context, R.style.BaseCustomDialogStyle);
        mContext = context;
    }

    /**
     * 都是内容数据
     */
    private String message;
    private String title;
    private String positive,negtive ;
    private int imageResId = -1 ;

    /**
     * 底部是否只有一个按钮
     */
    private boolean isSingle = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_dialog_custom_corner);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);
        //初始化界面控件
        initView();
        //初始化界面数据
        refreshView();
        //初始化界面控件的事件
        initEvent();
    }

    /**
     * 初始化界面的确定和取消监听器
     */
    private void initEvent() {
        //设置确定按钮被点击后，向外界提供监听
        positiveBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( onClickBottomListener!= null) {
                    onClickBottomListener.onPositiveClick();
                    dismiss();
                }
            }
        });
        //设置取消按钮被点击后，向外界提供监听
        negtiveBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( onClickBottomListener!= null) {
                    onClickBottomListener.onNegativeClick();
                    dismiss();
                }
            }
        });
    }

    /**
     * 初始化界面控件的显示数据
     */
    private void refreshView() {
        //如果用户自定了title和message
        if (!TextUtils.isEmpty(title)) {
            titleTv.setText(title);
            titleTv.setVisibility(View.VISIBLE);
        }else {
            titleTv.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(message)) {
            messageTv.setText(message);
        }
        //如果设置按钮的文字
        if (!TextUtils.isEmpty(positive)) {
            positiveBn.setText(positive);
        }else {
            positiveBn.setText("确定");
        }
        if (!TextUtils.isEmpty(negtive)) {
            negtiveBn.setText(negtive);
        }else {
            negtiveBn.setText("取消");
        }
        /**
         * 只显示一个按钮的时候隐藏取消按钮，回掉只执行确定的事件
         */
        if (isSingle){
            columnLineView.setVisibility(View.GONE);
            negtiveBn.setVisibility(View.GONE);
        }else {
            negtiveBn.setVisibility(View.VISIBLE);
            columnLineView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void show() {
        super.show();
        refreshView();
    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        negtiveBn = (Button) findViewById(R.id.negative);
        positiveBn = (Button) findViewById(R.id.positive);
        titleTv = (TextView) findViewById(R.id.title);
        messageTv = (TextView) findViewById(R.id.message);
        columnLineView = findViewById(R.id.column_line);
    }

    /**
     * 设置确定取消按钮的回调
     */
    public OnClickBottomListener onClickBottomListener;
    public CommonCornerDialog setOnClickBottomListener(OnClickBottomListener onClickBottomListener) {
        this.onClickBottomListener = onClickBottomListener;
        return this;
    }
    public interface OnClickBottomListener{
        /**
         * 点击确定按钮事件
         */
        public void onPositiveClick();
        /**
         * 点击取消按钮事件
         */
        public void onNegativeClick();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(@StringRes int messageId) {
        setMessage(mContext.getText(messageId).toString());
    }

    public CommonCornerDialog setMessage(String message) {
        this.message = message;
        return this ;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(@StringRes int titleId) {
        setTitle(mContext.getText(titleId).toString());
    }

    @Override
    public void setTitle(@Nullable CharSequence title) {
        setTitle(title.toString());
    }

    public CommonCornerDialog setTitle(String title) {
        this.title = title;
        return this ;
    }

    public String getPositive() {
        return positive;
    }

    public CommonCornerDialog setPositive(@StringRes int textId) {
        return setPositive(mContext.getText(textId).toString());
    }

    public CommonCornerDialog setPositive(String positive) {
        this.positive = positive;
        return this ;
    }

    public String getNegative() {
        return negtive;
    }

    public CommonCornerDialog setNegative(@StringRes int textId) {
        return setNegative(mContext.getText(textId).toString());
    }

    public CommonCornerDialog setNegative(String negtive) {
        this.negtive = negtive;
        return this ;
    }

    public boolean isSingle() {
        return isSingle;
    }

    public CommonCornerDialog setSingle(boolean single) {
        isSingle = single;
        return this ;
    }

    public CommonCornerDialog setImageResId(int imageResId) {
        this.imageResId = imageResId;
        return this ;
    }

}
