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
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.Toast;

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
	        	    Common.addfav_url = engine.getUrl();
	        		Common.tabHost.setCurrentTab(1);

	        		
//	        		RSSItem i = new RSSItem(item_type.IT_KODEKS);
//	        		i.title = engine.getTitle();
//	        		i.mplink = engine.getUrl();
//	        		Common.addToFavr(i);
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
