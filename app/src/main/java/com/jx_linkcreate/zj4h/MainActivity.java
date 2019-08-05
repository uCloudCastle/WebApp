package com.jx_linkcreate.zj4h;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

public class MainActivity extends CheckPermissionsActivity {

    private WebView mWebView;
    private ImageView mSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setWebViewClient();
        loadPage();
    }

    private void setWebViewClient() {
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.d("ZJ4H", "onPageFinished");
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.d("ZJ4H", "onPageFinished");
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                Log.d("ZJ4H", "onReceivedError");
            }
        });
    }

    private void loadPage() {
        mWebView.loadUrl("http://z4h.wtau.top:81/h5");
    }

    private void initView() {
        mSplash = findViewById(R.id.activity_main_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSplash.setVisibility(View.GONE);
            }
        }, 2000);

        mWebView = findViewById(R.id.activity_main_webview);
        // 启用或禁用 DOM 缓存。
        mWebView.getSettings().setDomStorageEnabled(true);
        /* *
         * 当一个安全的来源（origin）试图从一个不安全的来源加载资源时配置 WebView 的行为
         * MIXED_CONTENT_ALWAYS_ALLOW：允许从任何来源加载内容，即使起源是不安全的；
         * MIXED_CONTENT_NEVER_ALLOW：不允许Https加载Http的内容，即不允许从安全的起源去加载一个不安全的资源；
         * MIXED_CONTENT_COMPATIBILITY_MODE：当涉及到混合式内容时，WebView 会尝试去兼容最新Web浏览器的风格。
         **/
        mWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        // 支持缩放
        mWebView.getSettings().setSupportZoom(false);
        // 设置 WebView 是否允许执行 JavaScript 脚本，默认false
        mWebView.getSettings().setJavaScriptEnabled(true);
        // 是否允许在 WebView 中访问内容URL(Content Url)，默认允许
        mWebView.getSettings().setAllowContentAccess(true);
        // 是否允许运行在一个URL环境（the context of a file scheme URL）中的JavaScript访问来自其他URL环境的内容，为了保证安全，应该不允许
        mWebView.getSettings().setAllowFileAccessFromFileURLs(true);
        // 应用缓存 API 是否可用，默认值false, 结合setAppCachePath(String)使用
        mWebView.getSettings().setAppCacheEnabled(true);
        // 设置缓存模式
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        // 设置应用缓存文件的路径。为了让应用缓存 API 可用，此方法必须传入一个应用可写的路径
        // mWebView.getSettings().setAppCachePath("");
        // 是否允许 WebView 度超出以概览的方式载入页面，默认false
        mWebView.getSettings().setLoadWithOverviewMode(true);
        // WebView 是否支持 HTML 的<viewport>标签或者使用wide viewport
        mWebView.getSettings().setUseWideViewPort(true);
        // 定位是否可用，默认为true。请注意，为了确保定位API在WebView的页面中可用，必须遵守如下约定:
        // (1) app必须有定位的权限，参见ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION；
        // (2) app必须提供onGeolocationPermissionsShowPrompt(String, GeolocationPermissions.Callback)回调方法的实现，
        //     在页面通过 JavaScript 定位 API 请求定位时接收通知。
        // 作为可选项，可以在数据库中存储历史位置和 Web 初始权限，参见setGeolocationDatabasePath(String).
        mWebView.getSettings().setGeolocationEnabled(true);
    }
}
