package com.vkassin.alexpro;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;

public class CabinetActivity extends Activity {

	private static final String TAG = "aLexPro.CabinetActivity"; 
	private WebView engine;
	private ProgressBar pb;
	
	public void onCreate(Bundle icicle) {

		super.onCreate(icicle);
		setContentView(R.layout.cabin);
		 
		pb = (ProgressBar)findViewById(R.id.ProgressBar05);
		engine = (WebView) findViewById(R.id.web_engine_cab);
		engine.getSettings().setJavaScriptEnabled(true);
		engine.getSettings().setBuiltInZoomControls(true);

	    engine.setWebViewClient(new WebViewClient() {
	    	
	    	public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

	    		pb.setVisibility(View.GONE);
	    	}
	    	
		    @Override
		    public boolean shouldOverrideUrlLoading(WebView view, String url) {
		        view.loadUrl(url);
		        return true;
		    }
	    });
	    
	    engine.setWebChromeClient(new WebChromeClient() {
			
			public void onProgressChanged(WebView view, int progress) {
				
				if(progress < 100)
					pb.setVisibility(View.VISIBLE);
				else
					pb.setVisibility(View.GONE);
					
//				activity.setProgress(progress * 1000);
//				Log.w(TAG, ""+progress);
			}
            
		});
	    
	    Button btn3 = (Button) this.findViewById(R.id.button3);
		
	    if (btn3!= null) {
//    	btn2.setWidth(10);
        btn3.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) {

//	            	Log.w(TAG, "button3 clicked");
//	                engine.clearView();
//	        		engine.setVisibility(View.VISIBLE);
//	        		engine.loadUrl(Common.WEB_OPEN);

	            	Common.fromCab = true;
	            	Common.curl = Common.WEB_OPEN;
	            	Common.paid = Common.paid_type.PT_OPEN;
	        		Common.tabHost.setCurrentTab(0);
	            }
	        });
	    }

	    Button btn4 = (Button) this.findViewById(R.id.button4);
		
	    if (btn4!= null) {
//    	btn2.setWidth(10);
        btn4.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) {

//	            	Log.w(TAG, "button4 clicked");
//	                engine.clearView();
//	        		engine.setVisibility(View.VISIBLE);
//	        		engine.loadUrl(Common.WEB_ONLINE);

	            	Common.fromCab = true;
	            	Common.curl = Common.WEB_ONLINE;
	            	Common.paid = Common.paid_type.PT_ONLINE;
	        		Common.tabHost.setCurrentTab(0);

	            }
	        });
	    }
	}
	
	protected void onResume() {
		
		super.onResume();
	
		engine.setVisibility(View.GONE);

	}
}
