package com.vkassin.alexpro;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
//123
public class MainActivity extends Activity {

	private static final String TAG = "aLexPro.MainActivity"; 
	WebView engine;
	
	public void onCreate(Bundle icicle) {

		super.onCreate(icicle);
		setContentView(R.layout.mainact);
		 
		engine = (WebView) findViewById(R.id.web_engine);
		engine.setWebViewClient(new HelloWebViewClient());
		engine.getSettings().setJavaScriptEnabled(true);
		engine.loadUrl(Common.WEB_OPEN);
	}
    
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && engine.canGoBack()) {
            engine.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
	
	private class HelloWebViewClient extends WebViewClient {
	    @Override
	    public boolean shouldOverrideUrlLoading(WebView view, String url) {
	        view.loadUrl(url);
	        return true;
	    }

	}
}
