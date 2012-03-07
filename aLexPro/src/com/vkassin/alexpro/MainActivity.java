package com.vkassin.alexpro;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {

	private static final String TAG = "aLexPro.MainActivity"; 
	
	public void onCreate(Bundle icicle) {

		super.onCreate(icicle);
		setContentView(R.layout.mainact);
		 
		WebView engine = (WebView) findViewById(R.id.web_engine);
		engine.setWebViewClient(new HelloWebViewClient());
		engine.getSettings().setJavaScriptEnabled(true);
		engine.loadUrl(Common.WEB_OPEN);
	}
	
	private class HelloWebViewClient extends WebViewClient {
	    @Override
	    public boolean shouldOverrideUrlLoading(WebView view, String url) {
	        view.loadUrl(url);
	        return true;
	    }
	}
}
