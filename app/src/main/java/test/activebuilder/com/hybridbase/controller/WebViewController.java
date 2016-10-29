package test.activebuilder.com.hybridbase.controller;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;

import java.util.ArrayList;

import test.activebuilder.com.hybridbase.R;
import test.activebuilder.com.hybridbase.ui.util.MyTestWebviewClient;

import static test.activebuilder.com.hybridbase.R.*;

/**
 * Created by eselen on 26.10.2016.
 */
public class WebViewController extends Activity {

    private WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        myWebView = (WebView) findViewById(id.webView);


        myWebView.getSettings().supportZoom();
        myWebView.setVerticalScrollBarEnabled(true);

        MyTestWebviewClient myWebViewClient = new MyTestWebviewClient(this);

        myWebView.setWebViewClient(myWebViewClient);
        myWebView.getSettings().setJavaScriptEnabled(true);

        String imageUrl;
        //myWebView.loadUrl("http://http://emreweb.activebuilder.local/mxi/");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}