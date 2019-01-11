package com.android.qin;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.qin.base.BaseCompatActivity;
import com.android.qin.util.LogUtil;
import com.hismart.base.BaseToolbarCompatActivity;

public class HisenseMallActivity extends BaseToolbarCompatActivity {
    private static final String TAG = "HisenseMallActivity";

    private static final String HISENSE_MALL_URL = "https://m.hisense.com";
    LinearLayout mWebParentLayout;
    WebView mWebView;
    ContentLoadingProgressBar mLoadingProgressBar;
    private boolean hasLoadingSuccess;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.e(TAG,"onCreate");
        setLeftButtonIsBack(true);
        setMiddleTitle("海信商城");
        setContentView(R.layout.activity_hisense_mall);
        mLoadingProgressBar = findViewById(R.id.loading_progress);
        mWebParentLayout = findViewById(R.id.web_view_parent);
        //mWebView = findViewById(R.id.web_view);
        mWebView = new WebView(this);
        mWebParentLayout.addView(mWebView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        initWebView();
        mWebView.loadUrl(HISENSE_MALL_URL);
    }


    private void initWebView() {
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if (url.contains("http") || url.contains("https")) {
                    // 返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                    view.loadUrl(url);
                    return true;
                }
                return true;
            }

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

                if (request.getUrl().toString().contains("http") || request.getUrl().toString().contains("https")) {
                    // 返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                    view.loadUrl(request.getUrl().toString());
                    return true;
                }
                return true;
            }

            /**
             * 加载错误的时候会回调，在其中可做错误处理，比如再请求加载一次，或者提示404的错误页面
             */
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                hasLoadingSuccess = false;
            }

            /**
             * 当接收到https错误时，会回调此函数，在其中可以做错误处理
             */
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.d(TAG, "onPageStarted");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mWebView.getSettings().setBlockNetworkImage(false);
                Log.d(TAG, "onPageFinished");

            }
        });
        // x5WebView.getSettings().setUserAgentString("userAgent");//根据不同用户设置
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setSavePassword(false);
        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);

        mWebView.getSettings().setDisplayZoomControls(false);
        //android webview组件包含3个隐藏的系统接口：“accessibility”和和“ccessibilityaversal”以及“searchBoxJavaBridge_”，同样会造成远程代码执行。
        mWebView.removeJavascriptInterface("accessibility");
        mWebView.removeJavascriptInterface("accessibilityTraversal");
        mWebView.removeJavascriptInterface("searchBoxJavaBridge_");
        //关闭自动播放，需要用户确认
        //三星S IV 4.2.2 不支持该方法
        try {
            mWebView.getSettings().setMediaPlaybackRequiresUserGesture(true);
        } catch (NoSuchMethodError e) {

        }

        //To allow https to redirect to http you need to set the mixed content mode to MIXED_CONTENT_ALWAYS_ALLOW
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        WebSettings webSettings = mWebView.getSettings();

        webSettings.setBlockNetworkImage(true); //将图片下载阻塞
        //webSettings.setLoadsImagesAutomatically(false);//不自动下载图片
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int mDensity = metrics.densityDpi;

        Log.d(TAG, "mDensity = "+mDensity);


        /*webSettings.setMinimumFontSize(minimumFontSize);
        webSettings.setMinimumLogicalFontSize(minimumLogicalFontSize);
        webSettings.setDefaultFontSize(defaultFontSize);
        webSettings.setDefaultFixedFontSize(defaultFixedFontSize);*/
        //webSettings.setBuiltInZoomControls(true);
        /***打开本地缓存提供JS调用**/
        webSettings.setDomStorageEnabled(true);

        // Set cache size to 10 mb by default. should be more than enough
        webSettings.setAppCacheMaxSize(1024 * 1024 * 5);
        String appCaceDir = this.getApplicationContext().getDir("cache", Context.MODE_PRIVATE).getPath();
        webSettings.setAppCachePath(appCaceDir);
        webSettings.setAppCacheEnabled(true);
        webSettings.setAllowFileAccess(true);
        //启用数据库
        webSettings.setDatabaseEnabled(true);
        //启用地理定位
        webSettings.setGeolocationEnabled(true);
        //设置定位的数据库路径
        String appDatabaseDir = this.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();
        webSettings.setGeolocationDatabasePath(appDatabaseDir);
/*        x5WebView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {

            }
        });*/
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    mLoadingProgressBar.setProgress(newProgress, true);
                } else {
                    mLoadingProgressBar.setProgress(newProgress);
                }

                if (newProgress >= 99) {
                    mLoadingProgressBar.setVisibility(View.INVISIBLE);
                    hasLoadingSuccess = true;
                } else {
                    if (!hasLoadingSuccess) {
                        mLoadingProgressBar.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    private void handleBackKeyEvent() {
        //当wmWebView不是处于第一页面时，返回上一个页面
        if (mWebView != null && mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            //当mWebView处于第一页面时,直接退出
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            //清空缓存
            mWebView.clearCache(true);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (mWebParentLayout != null) {
                    LogUtil.i(TAG, "onDestroy removeView mWebView");
                    mWebParentLayout.removeView(mWebView);
                    mWebView.removeAllViews();
                    mWebView.destroy();
                }
            } else {
                mWebView.removeAllViews();
                mWebView.destroy();
                if (mWebParentLayout != null) {
                    mWebParentLayout.removeView(mWebView);
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        handleBackKeyEvent();
    }

}
