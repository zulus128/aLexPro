package com.vkassin.alexpro;

import com.vkassin.alexpro.Common.item_type;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TabHost;
import android.widget.Toast;
import android.util.Log;
import android.webkit.WebStorage;

public class MainActivity extends Activity {

	private static final String TAG = "aLexPro.MainActivity"; 
	WebView engine;
	private ProgressBar pb;
	private Settings sss;
	
	private void go() {

		setContentView(R.layout.mainact);
		
	    pb = (ProgressBar)findViewById(R.id.ProgressBar00);
		engine = (WebView) findViewById(R.id.web_engine);
		engine.getSettings().setBuiltInZoomControls(false);
//		engine.getSettings().setDisplayZoomControls(false);
//		engine.setWebViewClient(new HelloWebViewClient());
		engine.getSettings().setJavaScriptEnabled(true);
		engine.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
//		engine.getSettings().setDomStorageEnabled(true);
//		engine.getSettings().setAppCacheMaxSize(1024*1024*8);
//		String appCachePath = "/data/data/com.vkassin.alexpro/cache";//getApplicationContext().getCacheDir().getAbsolutePath();
//		engine.getSettings().setAppCachePath(appCachePath);
//		engine.getSettings().setAllowFileAccess(true);
//		engine.getSettings().setAppCacheEnabled(true);
//		engine.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);

		
//		engine.loadUrl((Common.paid == Common.paid_type.PT_ONLINE)?Common.WEB_ONLINE:Common.WEB_OPEN);
		engine.loadUrl(Common.curl);
//		pb.setVisibility(View.VISIBLE);
//		final Activity activity = MainActivity.this;
	    engine.setWebChromeClient(new WebChromeClient() {
			
			public void onProgressChanged(WebView view, int progress) {
				
				if(progress < 100)
					pb.setVisibility(View.VISIBLE);
				else
					pb.setVisibility(View.GONE);
					
//				activity.setProgress(progress * 1000);
//				Log.w(TAG, ""+progress);
			}
            
//			@Override
//            public void onReachedMaxAppCacheSize(long spaceNeeded, long totalUsedQuota,
//                         WebStorage.QuotaUpdater quotaUpdater)
//            {
//                  quotaUpdater.updateQuota(spaceNeeded * 2);
//            }
		});
	    
	    engine.setWebViewClient(new WebViewClient() {
	    	
	    	public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

	    		pb.setVisibility(View.GONE);
	    	}
	    	
		    @Override
		    public boolean shouldOverrideUrlLoading(WebView view, String url) {
		    	Common.curl = url;
		        view.loadUrl(url);
		        return true;
		    }
	    });
		
	}

	public void onResume() {
		
		Log.w(TAG, "onResume");
		super.onResume();
		if(!Common.fromCab)
			return;
		Common.fromCab = false;
		go();
	}
	
	public void onCreate(Bundle icicle) {

		super.onCreate(icicle);
		
	    
//		sss = Common.loadSettingsFromFile();
//		if(sss != null) {
//			
//			Common.paid = sss.paid;
////			go();
////			return;
//		}
//		else
//		sss = new Settings();

//		Log.w(TAG, "paid1 = "+Common.paid1);
		if((Common.paid != null ) && (Common.paid != Common.paid_type.PT_NONE)) {
			go();
			return;
		}
		
		setContentView(R.layout.mainstart);
		Button btn1 = (Button) this.findViewById(R.id.button1);
		
	    if (btn1!= null) {
	    	
//	    	btn1.setWidth(20);
	        btn1.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) {
	            	Log.w(TAG, "button1 clicked");
//	            	sss.paid = Common.paid_type.PT_OPEN;
	            	Common.paid = Common.paid_type.PT_OPEN;
	            	Common.curl = Common.WEB_OPEN;
//	            	Common.paid1 = sss.paid;
//	            	Common.saveSettingsToFile(sss);
	            	go();
	            }
	        });
	    }

	    Button btn2 = (Button) this.findViewById(R.id.button2);
		
	    if (btn2!= null) {
//    	btn2.setWidth(10);
        btn2.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) {
	            	Log.w(TAG, "button2 clicked");
//	            	sss.paid = Common.paid_type.PT_ONLINE;
	            	Common.paid = Common.paid_type.PT_ONLINE;
	            	Common.curl = Common.WEB_ONLINE;
//	            	Common.paid1 = sss.paid;
//	            	Common.saveSettingsToFile(sss);
	            	go();
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
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.mainmenu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.favitem: {
	        	if(engine.getUrl().indexOf(Common.TEST_STRING1) != -1) {
	        	
	        	    Common.addfav_flag = true;
	        	    Common.addfav_url = /*"http://www.google.com";*/engine.getUrl().replaceFirst(Common.TEST_STRING1, Common.TEST_STRING2);

//	        	    RSSItem i = new RSSItem(item_type.IT_KODEKS);
//	        		i.title = engine.getTitle();
//	        		i.mplink = Common.addfav_url;
//	        		Common.addToFavr(i);
	        		
	        	    Common.title = engine.getTitle();
	        	    
	        		Common.tabHost.setCurrentTab(1);
	        		
	        	}
	        	else {
	        		
	    			Toast.makeText(Common.app_ctx, "В загруженные можно добавлять только документы!", Toast.LENGTH_SHORT).show();

	        	}
                break;
	        }
	    }
	    return true;
	}    		
}
