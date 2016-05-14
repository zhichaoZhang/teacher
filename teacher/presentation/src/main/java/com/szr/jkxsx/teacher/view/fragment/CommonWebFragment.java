package com.szr.jkxsx.teacher.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.szr.jkxsx.teacher.R;
import com.szr.jkxsx.teacher.app.BaseApplication;
import com.szr.jkxsx.teacher.app.BaseFragment;
import com.szr.jkxsx.teacher.utils.InputTypeUtil;
import com.szr.jkxsx.teacher.view.activity.SingleFragmentActivity;
import com.szr.jkxsx.teacher.view.widget.EmptyView;

import butterknife.InjectView;
import butterknife.OnClick;
import timber.log.Timber;

/**
 * 通用的网页显示页面
 * <p/>
 * Created by zcZhang on 15/4/30.
 */
public class CommonWebFragment extends BaseFragment {
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.tv_close)
    TextView tvClose;
    @InjectView(R.id.wv_common)
    WebView webView;
    @InjectView(R.id.empty_view)
    EmptyView emptyView;
    //传递过来的url参数使用的key值
    public static final String KEY_ARGU_URL = "url";
    //传递过来的url值
    private String strUrl = "";
    private boolean isCloseAllActivity;
    private ArrayMap<String, String> header = new ArrayMap<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            strUrl = bundle.getString(KEY_ARGU_URL);
            Timber.i("-------onCreate---strUrl:" + strUrl);
            isCloseAllActivity = bundle.getBoolean("is_close_all_activity");
            Timber.i("-------onCreate---isCloseAllActivity:" + isCloseAllActivity);
        } else {
            onMessage("参数错误，请重试！");
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!isCloseAllActivity) {
            tvClose.setText("");
            tvClose.setPadding((int) getResources().getDimension(R.dimen.spacing_s15), 0, 0, 0);
            tvClose.setCompoundDrawablesWithIntrinsicBounds(R.drawable.btn_back, 0, 0, 0);
        }
        initWebView();
        webView.loadUrl(strUrl, header);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_common_web, null);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        InputTypeUtil.closeSoftKeyBoard(getActivity(), webView);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    /**
     * 获取一个网页实例
     *
     * @param url                网页链接
     * @param isCloseAllActivity 点击返回上一页还是关闭所有activity
     * @return
     */
    public static CommonWebFragment newInstance(String url, boolean isCloseAllActivity) {
        CommonWebFragment commonWebFragment = new CommonWebFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_ARGU_URL, url);
        bundle.putBoolean("is_close_all_activity", isCloseAllActivity);
        commonWebFragment.setArguments(bundle);
        return commonWebFragment;
    }

    /**
     * 对wenview进行设置
     */
    private void initWebView() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDefaultTextEncodingName("utf-8");
        webView.getSettings().setBuiltInZoomControls(false);
        webView.getSettings().setSupportZoom(false);
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.setDownloadListener(new MyWebViewDownLoadListener());
        header.put("NEARUID", "" + BaseApplication.getInstance().getAppConfigDataEngine().getUserId());
        webView.setWebViewClient(new MyWebClient());

        setCookies(strUrl, getActivity());
    }

    @OnClick(R.id.tv_close)
    void clickClose() {
        Timber.i("----点击关闭-------");
        webView.requestFocus();
        InputTypeUtil.closeSoftKeyBoard(getActivity(), webView);
        if (getActivity() instanceof SingleFragmentActivity.ChildFragmentManager) {
            ((SingleFragmentActivity.ChildFragmentManager) getActivity()).closeActivity();
        }
    }


    class MyWebClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            webView.loadUrl(url, header);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            Timber.i("onPageStarted------>" + url);
            showLoading("正在加载...", true);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            Timber.i("onPageFinished------>" + url);
            if (view != null) {
                String pageTitle = view.getTitle();
                if (pageTitle != null && !pageTitle.startsWith("http") && !pageTitle.startsWith("https")) {
                    tvTitle.setText(pageTitle);
                }
            }
            hideLoading();
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            Timber.i("onReceivedError------>" + failingUrl);
            emptyView.setVisibility(View.VISIBLE);
            tvTitle.setVisibility(View.GONE);
        }
    }

    private static void setCookies(String url, Context context) {
        if (url == null || url.equals("")) {
            return;
        }
        if (context == null) {
            return;
        }
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        String cookieString = "near_uid=" + BaseApplication.getInstance().getAppConfigDataEngine().getUserId();
        String pre = url.replace("http://", "");
        String domain = pre;
        if (pre.indexOf("/") != -1) {
            domain = pre.substring(0, pre.indexOf("/"));
        }
        cookieManager.setCookie(domain, cookieString);
        Timber.i("cookieManager.getCookie(com.szr.jkxsx.domain)------->" + cookieManager.getCookie(domain));
        CookieSyncManager.getInstance().sync();
    }

    private class MyWebViewDownLoadListener implements DownloadListener {

        @Override
        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype,
                                    long contentLength) {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }

    }
}
