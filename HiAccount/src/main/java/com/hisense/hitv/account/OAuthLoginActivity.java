package com.hisense.hitv.account;

import android.app.Activity;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.CookieManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.ZoomDensity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hismart.base.BaseToolbarCompatActivity;

public class OAuthLoginActivity extends BaseToolbarCompatActivity {

    private static final String TAG = "MS_HISCTL_OAUTH";
    public static final String CALLBACK_PRE = "weibo4android://OAuthActivity";
    public static final String CALLBACK_SECOND = "http://www.hismarttv.com/retoext.html";

    private static AuthListener listener;
    //private ProgressBar proBar;
    private WebView web;
    private Boolean authorized = Boolean.FALSE;
    private RelativeLayout mPanelView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String url = getIntent().getStringExtra("url");
        setLeftButtonIsBack(true);
        setMiddleTitle("新浪微博登录");
        setContentView(R.layout.activity_oauth_login);

        findViews();

        web.loadUrl(url);
    }

    private void findViews() {

        mPanelView = (RelativeLayout) findViewById(R.id.panel);
        web = (WebView) findViewById(R.id.oauth);
        WebSettings setting = web.getSettings();
        setting.setJavaScriptEnabled(true);//TODO
        setting.setBuiltInZoomControls(true);
        setting.setUseWideViewPort(true);
        web.setVerticalScrollBarEnabled(false);
        web.setHorizontalScrollBarEnabled(false);
        web.setWebViewClient(new WebClient());
        web.setWebChromeClient(new WebChrome());

    }

    public static void authorizeActivity(Activity act, String url, AuthListener l, int blogId) {
        Intent i = new Intent(act, OAuthLoginActivity.class).putExtra("url", url);
        Log.d(TAG, url);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        act.startActivity(i);
        listener = l;
    }

    private class WebClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d(TAG, "Url:" + url);
            if (url.startsWith(CALLBACK_PRE) || url.startsWith(CALLBACK_SECOND)) {
                if (listener != null) {
                    listener.onSuccess(url);
                    authorized = true;
                    listener = null;
                    finish();
                    return true;
                }
            }
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
            if (listener != null) {
                listener.onError();
            }
        }
    }

    private class WebChrome extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {

        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String url = intent.getStringExtra("url");
        web.loadUrl(url);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!authorized && listener != null) {
            listener.onCancel();
        }
        //CookieManager.getInstance().removeAllCookie();
        authorized = Boolean.FALSE;
        listener = null;
        if (web != null) {
            mPanelView.removeView(web);
            web.destroy();
            web = null;
        }
    }

    interface AuthListener {
        void onSuccess(String url);

        void onCancel();

        void onError();
    }
}
