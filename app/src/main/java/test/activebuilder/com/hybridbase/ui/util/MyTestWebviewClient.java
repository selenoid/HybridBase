package test.activebuilder.com.hybridbase.ui.util;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by eselen on 25.6.2015.
 */
public class MyTestWebviewClient extends WebViewClient {

    public static WebView view;
    public static boolean isFinishing = false;
    public Handler handler = new Handler();
    private String curURL = "";
    private Activity activity;
    private IProgressEventListener mProgressListener;


    public MyTestWebviewClient(Activity _activity) {
        activity = _activity;
    }

    public static WebView getView() {
        return view;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        MyTestWebviewClient.view = view;
        view.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onReceivedError(WebView view, int errorCode,
                                String description, String failingUrl) {
        Log.e("Error", description + "errorCode : " + errorCode);
        super.onReceivedError(view, errorCode, description, failingUrl);
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler,
                                   SslError error) {
        Log.e("Error", "SSL Error code:" + error.toString());

        //super.onReceivedSslError(view, handler, error);
        if (curURL.indexOf("https://msube.halkbank.com.tr") > -1) {
            handler.proceed();
        }
    }

    @Override
    public void onPageFinished(final WebView view, String url) {
        super.onPageFinished(view, url);

        int api = Build.VERSION.SDK_INT;
        //getView().loadUrl("javascript:callExternalMethod('setAndroidApi',[" + api + "]);");

        int viewW = view.getWidth();
        int viewH = view.getHeight();

        MyTestWebviewClient.view = view;

        view.invalidate();

        WebSettings settings = view.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);

        invalidate();
    }

    private void invalidate() {
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        //getView().loadUrl("javascript:callExternalMethod('changeWinSize',[]);");
                        view.invalidate();/*
                        //changeWinSize
                        view.invokeZoomPicker();
                        view.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);*/
                    }
                },
                500);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {

        if (url.indexOf("tel:") > -1) {
            //
            return true;
        } else if (url.startsWith("http:")) {
            curURL = url;
            view.loadUrl(url);
        } else if (url.startsWith("sms:")) {
            //
        } else if (url.startsWith("https:")) {
            curURL = url;

            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            return true;
        } else {
            try {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.halkbank.com.tr/" + url));
                activity.startActivity(browserIntent);
            } catch (Error error) {
                Log.e("BrowserError", error.getMessage());
            }
            return true;
        }

        return true;
    }

}
