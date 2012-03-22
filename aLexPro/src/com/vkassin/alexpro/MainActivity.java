package com.vkassin.alexpro;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends Activity {

	private static final String TAG = "aLexPro.MainActivity"; 
	WebView engine;
	
	public void onCreate(Bundle icicle) {

		super.onCreate(icicle);
		setContentView(R.layout.mainstart);
		
	    Button btn1 = (Button) this.findViewById(R.id.button1);

	    if (btn1!= null) {
	        btn1.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) {

	        		setContentView(R.layout.mainact);
	        		engine = (WebView) findViewById(R.id.web_engine);
	        		engine.setWebViewClient(new HelloWebViewClient());
	        		engine.getSettings().setJavaScriptEnabled(true);
	        		engine.loadUrl(Common.WEB_OPEN);

	            }
	        });
	    }

	}
    
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((engine != null) && (keyCode == KeyEvent.KEYCODE_BACK) && engine.canGoBack()) {
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
