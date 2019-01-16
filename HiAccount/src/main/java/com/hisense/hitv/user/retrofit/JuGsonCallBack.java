package com.hisense.hitv.user.retrofit;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hismart.base.LogUtil;
import com.wj.android.http.BaseView;
import com.wj.android.http.GsonCallback;

/**
 * @author qinwendong
 * @date 2018/12/20
 * descrption:
 */
public abstract class JuGsonCallBack<T> extends GsonCallback<T> {

    private static final String TAG = "JuGsonCallBack";
    public JuGsonCallBack() {
        super();
    }
    public JuGsonCallBack(BaseView baseView) {
        super(baseView);
    }

    public JuGsonCallBack(BaseView baseView, int requestId) {
        super(baseView, requestId);
    }

    @Override
    protected String convertResponse(String response) {
        LogUtil.e(TAG,"convertResponse response:"+response);
        BaseInfo<ErrorBean> baseInfo = new Gson().fromJson(response, new TypeToken<BaseInfo<ErrorBean>>(){}.getType());
        if (!baseInfo.isSuccess()) {
            throw new ApiException("未知错误", 1111111);
        }else {
            ErrorBean errorBean =   baseInfo.getResponse();
            LogUtil.e(TAG,"convertResponse getErrorDesc:"+errorBean.getErrorDesc());
            if(errorBean.getErrorCode() != 0){
                throw new ApiException(errorBean.getErrorDesc(), errorBean.getErrorCode());
            }
        }
        return super.convertResponse(response);
    }
}
